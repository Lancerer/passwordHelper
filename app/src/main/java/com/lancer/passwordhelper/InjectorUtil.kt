package com.lancer.passwordhelper

import com.lancer.passwordhelper.model.MainRepository
import com.lancer.passwordhelper.model.database.DaoManager
import com.lancer.passwordhelper.model.network.MainNetWorkManager
import com.lancer.passwordhelper.ui.activity.input.InputViewModelFactory
import com.lancer.passwordhelper.ui.fragment.category.CategoryViewModelFactory

/**
 * @author lancer
 * @des
 * @Date 2020/7/21 9:14
 */
object InjectorUtil {

    private fun getMainRepository() = MainRepository.getInstance(
        DaoManager.getInstance(),
        MainNetWorkManager.getInstance()
    )


    fun getCategoryViewModelFactory() = CategoryViewModelFactory(getMainRepository())

    fun getInputViewModelFactory() = InputViewModelFactory(getMainRepository())

}