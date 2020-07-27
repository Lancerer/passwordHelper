package com.lancer.passwordhelper.ui.activity.edit

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.viewModelScope
import com.lancer.passwordhelper.bean.Card
import com.lancer.passwordhelper.model.MainRepository
import kotlinx.coroutines.launch

/**
 * @author lancer
 * @des
 * @Date 2020/7/27 15:53
 */
class EditViewModel(private val repository: MainRepository) : ViewModel() {

   fun deleteCard(card: Card){
       val launch = viewModelScope.launch {
           repository.deleteCard(card)
       }
   }

}