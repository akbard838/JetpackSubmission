package com.dicoding.jetpacksubmission.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.dicoding.jetpacksubmission.base.BaseApiResponse
import com.dicoding.jetpacksubmission.base.BaseResource
import com.dicoding.jetpacksubmission.utils.AppExecutors
import com.dicoding.jetpacksubmission.utils.enum.StatusResponse

abstract class NetworkBoundResource<ResultType, RequestType>(private val mExecutors: AppExecutors) {

    private val result = MediatorLiveData<BaseResource<ResultType>>()

    init {
        result.value = BaseResource.loading(null)

        @Suppress("LeakingThis")
        val dbSource = loadFromDB()

        result.addSource(dbSource) { data ->
            result.removeSource(dbSource)
            if (shouldFetch(data)) {
                fetchFromNetwork(dbSource)
            } else {
                result.addSource(dbSource) { newData ->
                    result.value = BaseResource.success(newData)
                }
            }
        }
    }

    protected open fun onFetchFailed() {}

    protected abstract fun loadFromDB(): LiveData<ResultType>

    protected abstract fun shouldFetch(data: ResultType?): Boolean

    protected abstract fun createCall(): LiveData<BaseApiResponse<RequestType>>?

    protected abstract fun saveCallResult(data: RequestType)

    private fun fetchFromNetwork(dbSource: LiveData<ResultType>) {

        val apiResponse = createCall()

        result.addSource(dbSource) { newData ->
            result.value = BaseResource.loading(newData)
        }

        apiResponse?.let {
            result.addSource(it) { response ->
                result.removeSource(it)
                result.removeSource(dbSource)
                when (response.status) {
                    StatusResponse.SUCCESS ->
                        mExecutors.diskIO().execute {
                            saveCallResult(response.body)
                            mExecutors.mainThread().execute {
                                result.addSource(loadFromDB()) { newData ->
                                    result.value = BaseResource.success(newData)
                                }
                            }
                        }
                    StatusResponse.EMPTY -> mExecutors.mainThread().execute {
                        result.addSource(loadFromDB()) { newData ->
                            result.value = BaseResource.success(newData)
                        }
                    }
                    StatusResponse.ERROR -> {
                        onFetchFailed()
                        result.addSource(dbSource) { newData ->
                            result.value = BaseResource.error(response.message, newData)
                        }
                    }
                }
            }
        }
    }

    fun asLiveData(): LiveData<BaseResource<ResultType>> = result
}