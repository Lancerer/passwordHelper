package com.lancer.passwordhelper.ui.fragment.category

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lancer.passwordhelper.model.MainRepository
import com.lancer.passwordhelper.model.bean.Category
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CategoryViewModel(private val repository: MainRepository) : ViewModel() {


     var dataList = MutableLiveData<List<Category>>()


    fun requestCategoryList() {
        viewModelScope.launch(Dispatchers.Main) {
            dataList.value = repository.getCategoryListFromDataBase()
        }
    }

}