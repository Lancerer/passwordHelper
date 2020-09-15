package com.lancer.passwordhelper.api

/**
 * @author lancer
 * @des
 * @Date 2020/9/8 10:20
 */
 class Resource<T>(val status: Status, val data: T, val message: String?) {
    companion object {
        fun <T> success(data: T) = Resource(Status.SUCCESS, data, null)
        fun <T> error(msg: String?, data: T?) = Resource(Status.ERROR, data, msg)
        fun <T> loading(data: T?) = Resource(Status.LOADING, data, null)
    }
}