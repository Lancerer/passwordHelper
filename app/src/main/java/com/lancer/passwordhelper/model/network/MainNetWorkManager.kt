package com.lancer.passwordhelper.model.network

import com.lancer.passwordhelper.api.CommonApiService

/**
 * @author lancer
 * @des 管理网络请求的类
 * @Date 2020/7/2 13:16
 */
class MainNetWorkManager {

    private val commonApiService =
        HttpControl.getInstance(HttpControl.BASE_URL).create(CommonApiService::class.java)



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