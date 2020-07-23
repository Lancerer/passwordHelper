package com.lancer.passwordhelper.ui.fragment.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.lancer.passwordhelper.model.MainRepository

/**
 * @author lancer
 * @des
 * @Date 2020/7/23 14:36
 */
class HomeViewModelFactory(private val repository: MainRepository) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeViewModel(repository) as T
    }
}