package com.lancer.passwordhelper.ui.activity.input

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lancer.passwordhelper.base.BaseViewModel
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
class InputViewModel(private val repository: MainRepository) : BaseViewModel() {

    val spinnerDataList = MutableLiveData<List<Category>>()
    fun getCategoryList() {
        launch({
            spinnerDataList.value = repository.getCategoryListFromDataBase()
        }, {
            mExceptionLiveData.value = it
        }, {

        })
    }

    fun saveCard(card: Card) {
        launch({
            repository.saveCard(card)
        }, {
            mExceptionLiveData.value = it
        }, {

        })

    }
}