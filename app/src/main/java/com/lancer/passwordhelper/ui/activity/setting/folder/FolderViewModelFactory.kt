package com.lancer.passwordhelper.ui.activity.setting.folder

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.lancer.passwordhelper.model.MainRepository

/**
 * @author lancer
 * @des
 * @Date 2020/8/4 17:02
 */
@Suppress("UNCHECKED_CAST")
class FolderViewModelFactory(private val repository: MainRepository) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return FolderViewModel(repository) as T
    }
}