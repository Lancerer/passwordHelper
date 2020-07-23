package com.lancer.passwordhelper.ui.activity.input

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.viewModelScope
import com.lancer.passwordhelper.R
import com.lancer.passwordhelper.bean.Card
import com.lancer.passwordhelper.model.MainRepository
import kotlinx.android.synthetic.main.activity_input.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * @author lancer
 * @des
 * @Date 2020/7/22 15:15
 */
class InputViewModel(private val repository: MainRepository) : ViewModel() {

    fun onClick(view: View) {
        when (view.id) {
            R.id.input_back_iv -> {

            }
        }
    }


    fun saveCard(card: Card) {
        viewModelScope.launch(Dispatchers.Main) {
         repository.saveCard(card)
        }
    }
}