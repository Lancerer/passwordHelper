package com.lancer.passwordhelper.ui.fragment.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lancer.passwordhelper.model.bean.Card
import com.lancer.passwordhelper.extension.showToast
import com.lancer.passwordhelper.model.MainRepository
import kotlinx.coroutines.launch

//TODO 异常处理没加上
class HomeViewModel(private val repository: MainRepository) : ViewModel() {

    val dataList = MutableLiveData<List<Card>>()

    fun getCardList() {
        launch({
            val cardLstFromDataBase = repository.getCardLstFromDataBase()
            Log.d("tag", "#size=${cardLstFromDataBase.size}")
            dataList.value = cardLstFromDataBase
        }, {
            it.message.toString().showToast()
        })
    }

    private fun launch(block: suspend () -> Unit, error: suspend (Throwable) -> Unit) =
        viewModelScope.launch {
            try {
                block()
            } catch (e: Throwable) {
                error(e)
            }
        }
}



