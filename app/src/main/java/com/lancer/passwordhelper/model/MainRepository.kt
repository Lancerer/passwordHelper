package com.lancer.passwordhelper.model

import com.lancer.passwordhelper.model.bean.Card
import com.lancer.passwordhelper.model.bean.Category
import com.lancer.passwordhelper.model.database.DaoManager
import com.lancer.passwordhelper.model.network.MainNetWorkManager
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


    suspend fun saveCard(card: Card) = withContext(Dispatchers.IO) {
        val insertCard = daoManager.insertCard(card)
        insertCard
    }

    suspend fun getCardLstFromDataBase() = withContext(Dispatchers.IO) {
        val list = daoManager.queryAllCard()
        list
    }

    suspend fun findCardListByCategoryName(categoryName: String) = withContext(Dispatchers.IO) {
        val findCardByCategory = daoManager.findCardByCategory(categoryName)
        findCardByCategory
    }

    suspend fun deleteCard(card: Card) = withContext(Dispatchers.IO) {
        daoManager.deleteCard(card)
    }


    suspend fun saveCategory(category: Category) = withContext(Dispatchers.IO) {
        val insertCategory = daoManager.insertCategory(category)
        insertCategory
    }

    suspend fun getCategoryListFromDataBase() = withContext(Dispatchers.IO) {
        val list = daoManager.queryAllCategory()
        list
    }

    suspend fun deleteCategory(category: Category) = withContext(Dispatchers.IO) {
        daoManager.deleteCategory(category)
    }

    suspend fun daily(url: String) = withContext(Dispatchers.IO) {
        val fetchDaily = mainNetWork.fetchDaily(url)
        fetchDaily
    }

    suspend fun login(username: String, password: String) = withContext(Dispatchers.IO) {
        val response = mainNetWork.fetchLogin(username, password)
        response
    }

    suspend fun register(username: String, password: String, rePassword: String) =
        withContext(Dispatchers.IO) {
            val response = mainNetWork.fetchRegister(username, password, rePassword)
            response
        }

    suspend fun loginOut() = withContext(Dispatchers.IO) {
        val response = mainNetWork.fetchLoginOut()
        response
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