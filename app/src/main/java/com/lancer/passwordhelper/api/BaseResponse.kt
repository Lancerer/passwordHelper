package com.lancer.passwordhelper.api

/**
 * @author lancer
 * @des
 * @Date 2020/7/28 16:01
 */
data class BaseResponse<T>(val errorMsg: String, val errorCode: Int, val data: T) {
}