package com.lancer.passwordhelper.ui.activity.input

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lancer.passwordhelper.bean.Card
import com.lancer.passwordhelper.bean.Category
import com.lancer.passwordhelper.model.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * @author lancer
 * @des
 * @Date 2020/7/22 15:15
 */
class InputViewModel(private val repository: MainRepository) : ViewModel() {

    val spinnerDataList = MutableLiveData<List<Category>>()
    fun getCategoryList() {
        viewModelScope.launch(Dispatchers.Main) {
            spinnerDataList.value = repository.getCategoryListFromDataBase()
        }
    }

    fun saveCard(card: Card) {
        viewModelScope.launch(Dispatchers.Main) {
            repository.saveCard(card)
        }
    }
}