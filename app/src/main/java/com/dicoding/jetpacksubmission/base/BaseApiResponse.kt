package com.dicoding.jetpacksubmission.base

import com.dicoding.jetpacksubmission.utils.enum.StatusResponse

class BaseApiResponse<T>(val status: StatusResponse, val body: T, val message: String?) {
    companion object {
        fun <T> success(body: T): BaseApiResponse<T> = BaseApiResponse(StatusResponse.SUCCESS, body, null)

        fun <T> error(msg: String, body: T): BaseApiResponse<T> = BaseApiResponse(StatusResponse.ERROR, body, msg)
    }
}
