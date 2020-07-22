package com.lancer.passwordhelper.model.database

import com.lancer.passwordhelper.BaseApplication
import com.lancer.passwordhelper.bean.Card
import com.lancer.passwordhelper.bean.Category
import io.reactivex.Observable

/**
 * @author lancer
 * @des  数据库数据查询管理类
 * @Date 2020/7/21 8:54
 */
class DaoManager() {

    /**
     * 插入card
     */
    fun insertCard(card: Card): Boolean {
        return BaseApplication.instance.getDaoSession().cardDao.insert(card) != -1L
    }

    /**
     * 更新card
     */
    fun updateCard(card: Card) {
        BaseApplication.instance.getDaoSession().cardDao.update(card)
    }

    /**
     * 删除card
     */
    fun deleteCard(card: Card) {
        BaseApplication.instance.getDaoSession().cardDao.delete(card)
    }

    fun deleteCard(cardList: List<Card>) {
        for (card in cardList) {
            BaseApplication.instance.getDaoSession().cardDao.delete(card)
        }
    }

    /**
     * 查询所有card
     */
    fun queryAllCard(): List<Card> {
        return BaseApplication.instance.getDaoSession().cardDao.loadAll()
    }

    /**
     * 根据姓名查询card信息
     */
    fun queryCard(name: String): List<Card>? {
        return BaseApplication.instance.getDaoSession().cardDao.queryBuilder()
            .where(CardDao.Properties.Name.eq(name)).list()
    }

    /**
     * 插入category
     */
    fun insertCategory(category: Category): Boolean {
        return BaseApplication.instance.getDaoSession().categoryDao.insert(category) != -1L
    }

    /**
     * 更新category
     */
    fun updateCategory(category: Category) {
        BaseApplication.instance.getDaoSession().categoryDao.update(category)
    }

    fun deleteCategory(categoryList: List<Category>) {
        for (category in categoryList) {
            BaseApplication.instance.getDaoSession().categoryDao.delete(category)
        }
    }

    /**
     * 删除category
     */
    fun deleteCategory(category: Category) {
        BaseApplication.instance.getDaoSession().categoryDao.delete(category)
    }

    /**
     * 查询所有的cate
     */
    fun queryAllCategory(): List<Category> {
        return BaseApplication.instance.getDaoSession().categoryDao.loadAll()
    }

    fun queryCategory(name: String): List<Category>? {
        return BaseApplication.instance.getDaoSession().categoryDao.queryBuilder()
            .where(CategoryDao.Properties.CategoryName.eq(name)).list()
    }


    companion object {
        private var daoManager: DaoManager? = null
        fun getInstance(): DaoManager {
            if (daoManager == null) {
                synchronized(DaoManager::class.java) {
                    if (daoManager == null) {
                        daoManager = DaoManager()
                    }
                }
            }
            return daoManager!!
        }
    }
}