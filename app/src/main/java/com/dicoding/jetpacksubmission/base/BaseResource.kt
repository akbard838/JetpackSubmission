package com.dicoding.jetpacksubmission.base

import com.dicoding.jetpacksubmission.utils.enum.Status

data class BaseResource<T>(val status: Status, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T?): BaseResource<T> = BaseResource(Status.SUCCESS, data, null)

        fun <T> error(msg: String?, data: T?): BaseResource<T> = BaseResource(Status.ERROR, data, msg)

        fun <T> loading(data: T?): BaseResource<T> = BaseResource(Status.LOADING, data, null)
    }
}