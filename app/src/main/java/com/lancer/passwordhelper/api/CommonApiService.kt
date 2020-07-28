package com.lancer.passwordhelper.api

import retrofit2.Call
import retrofit2.http.*

/**
 *
 *created by Lancer on 2020/7/18
 *desc
 */
interface CommonApiService {

    /**
     * 登录请求
     */
    @FormUrlEncoded
    @POST("user/login")
    fun login(@Field("username") username: String, @Field("password") password: String): Call<BaseResponse<LoginBean>>

    /**
     * 注册请求
     */
    @FormUrlEncoded
    @POST("user/register")
    fun register(
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("repassword") rePassword: String
    ): Call<BaseResponse<LoginBean>>

    //TODO cookie持久化验证
    /**
     * 退出请求
     */
    @GET("user/logout/json")
    fun loginOut(): Call<BaseResponse<String>>

}