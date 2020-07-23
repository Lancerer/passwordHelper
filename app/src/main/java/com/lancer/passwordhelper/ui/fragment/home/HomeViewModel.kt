package com.lancer.passwordhelper.ui.fragment.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lancer.passwordhelper.bean.Card
import com.lancer.passwordhelper.model.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: MainRepository) : ViewModel() {

    val dataList = MutableLiveData<List<Card>>()

    fun getCardList() {
        viewModelScope.launch(Dispatchers.Main) {
            dataList.value = repository.getCardLstFromDataBase()
        }
    }
}