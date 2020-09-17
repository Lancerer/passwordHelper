package com.lancer.passwordhelper.ui.activity.video

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.lancer.passwordhelper.model.MainRepository

@Suppress("UNCHECKED_CAST")
class VideoViewModelFactory(private val repository: MainRepository) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return VideoViewModel(repository) as T
    }
}