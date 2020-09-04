package com.lancer.passwordhelper.ui.fragment.news

import androidx.lifecycle.MutableLiveData
import com.lancer.passwordhelper.base.BaseViewModel
import com.lancer.passwordhelper.model.MainRepository
import com.lancer.passwordhelper.model.bean.Daily

class CardViewModel(private val repository: MainRepository) : BaseViewModel() {
    val dataList = MutableLiveData<Daily>()
    fun requestDaily(url: String = "http://baobab.kaiyanapp.com/api/v5/index/tab/feed") {
        launch({
            dataList.value = repository.daily(url)
        }, {
            mExceptionLiveData.value = it
        }, {
            "complete"
        })
    }
}