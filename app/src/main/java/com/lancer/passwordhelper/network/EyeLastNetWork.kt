package com.lancer.passwordhelper.network

import com.lancer.passwordhelper.api.CommonApiService

/**
 * @author lancer
 * @des 管理网络请求的类
 * @Date 2020/7/2 13:16
 */
class EyeLastNetWork {

    private val commonApiService =
        HttpControl.getInstance(HttpControl.BASE_URL).create(CommonApiService::class.java)



    companion object {

        private var network: EyeLastNetWork? = null

        fun getInstance(): EyeLastNetWork {
            if (network == null) {
                synchronized(EyeLastNetWork::class.java) {
                    if (network == null) {
                        network = EyeLastNetWork()
                    }
                }
            }
            return network!!
        }
    }

}