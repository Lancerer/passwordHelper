package com.lancer.passwordhelper.model

import android.annotation.SuppressLint
import com.lancer.passwordhelper.bean.Category
import com.lancer.passwordhelper.model.database.DaoManager
import com.lancer.passwordhelper.model.network.MainNetWorkManager
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 *
 *created by Lancer on 2020/7/20
 *desc
 */
class MainRepository(
    private val daoManager: DaoManager,
    private val mainNetWork: MainNetWorkManager
) {
    suspend fun refreshCategory() = requestCategoryList()

    private suspend fun requestCategoryList() = withContext(Dispatchers.IO) {
        val list = daoManager.queryAllCategory()
        list
    }

    companion object {
        private var repository: MainRepository? = null

        fun getInstance(daoManager: DaoManager, mainNetWork: MainNetWorkManager): MainRepository {
            if (repository == null) {
                synchronized(MainRepository::class.java) {
                    if (repository == null) {
                        repository = MainRepository(daoManager, mainNetWork)
                    }
                }
            }
            return repository!!
        }
    }
}