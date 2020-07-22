package com.lancer.passwordhelper.model

import android.annotation.SuppressLint
import com.lancer.passwordhelper.bean.Card
import com.lancer.passwordhelper.bean.Category
import com.lancer.passwordhelper.model.database.DaoManager
import com.lancer.passwordhelper.model.network.MainNetWorkManager
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 *created by Lancer on 2020/7/20
 *desc 数据仓库，包含网络数据，数据库数据，数据获取通过构造器获得
 */
class MainRepository(
    private val daoManager: DaoManager,
    private val mainNetWork: MainNetWorkManager
) {
    suspend fun getCategoryListFromDataBase() = withContext(Dispatchers.IO) {
        val list = daoManager.queryAllCategory()
        list
    }

    suspend fun saveCard(card: Card)= withContext(Dispatchers.IO){
        val insertCard = daoManager.insertCard(card)
        insertCard
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