package com.lancer.passwordhelper.ui.activity.input

import androidx.lifecycle.MutableLiveData
import com.lancer.passwordhelper.base.BaseViewModel
import com.lancer.passwordhelper.model.MainRepository
import com.lancer.passwordhelper.model.bean.Card
import com.lancer.passwordhelper.model.bean.Category

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