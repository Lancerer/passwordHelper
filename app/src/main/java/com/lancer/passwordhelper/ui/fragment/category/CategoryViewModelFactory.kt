package com.lancer.passwordhelper.ui.fragment.category

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.lancer.passwordhelper.model.MainRepository

/**
 * @author lancer
 * @des
 * @Date 2020/7/21 9:21
 */
class CategoryViewModelFactory(private val repository: MainRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CategoryViewModel(repository) as T
    }
}