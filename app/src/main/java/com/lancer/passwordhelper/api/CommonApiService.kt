package com.lancer.passwordhelper.api

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

/**
 *
 *created by Lancer on 2020/7/18
 *desc
 */
interface CommonApiService {

    /**
     * 登录请求
     */
    @POST("user/login")
    fun login(@Body username: String, @Body password: String): Call<BaseResponse<LoginBean>>

    /**
     * 注册请求
     */
    @POST("user/register")
    fun register(
        @Body username: String,
        @Body password: String,
        @Body rePassword: String
    ): Call<BaseResponse<LoginBean>>

    //TODO cookie持久化验证
    /**
     * 退出请求
     */
    @GET("user/logout/json")
    fun loginOut(): Call<BaseResponse<String>>

}