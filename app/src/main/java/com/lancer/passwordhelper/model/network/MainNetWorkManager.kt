package com.lancer.passwordhelper.model.network

import com.lancer.passwordhelper.api.CommonApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

/**
 * @author lancer
 * @des 管理网络请求的类
 * @Date 2020/7/2 13:16
 */
class MainNetWorkManager {

    private val commonApiService =
        HttpControl.getInstance(HttpControl.BASE_URL).create(CommonApiService::class.java)


    suspend fun fetchLogin(username: String, password: String) =
        commonApiService.login(username, password).await()

    suspend fun fetchRegister(username: String, password: String, rePassword: String) =
        commonApiService.register(username, password, rePassword).await()

    suspend fun fetchLoginOut() = commonApiService.loginOut().await()

    private suspend fun <T> Call<T>.await(): T {
        return suspendCoroutine { continuation ->
            enqueue(object : Callback<T> {
                override fun onFailure(call: Call<T>, t: Throwable) {
                    continuation.resumeWithException(t)
                }

                override fun onResponse(call: Call<T>, response: Response<T>) {
                    val body = response.body()
                    if (body != null) continuation.resume(body)
                    else continuation.resumeWithException(RuntimeException("response body is null"))
                }
            })
        }
    }


    companion object {

        private var network: MainNetWorkManager? = null

        fun getInstance(): MainNetWorkManager {
            if (network == null) {
                synchronized(MainNetWorkManager::class.java) {
                    if (network == null) {
                        network = MainNetWorkManager()
                    }
                }
            }
            return network!!
        }
    }

}