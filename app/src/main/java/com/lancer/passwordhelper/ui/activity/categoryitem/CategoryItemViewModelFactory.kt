package com.lancer.passwordhelper.ui.activity.categoryitem

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.lancer.passwordhelper.model.MainRepository

/**
 * @author lancer
 * @des
 * @Date 2020/8/11 9:05
 */
@Suppress("UNCHECKED_CAST")
class CategoryItemViewModelFactory(private val repository: MainRepository) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CategoryItemViewModel(repository) as T
    }
}