package com.lancer.passwordhelper.ui.fragment.category

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lancer.passwordhelper.bean.Category
import com.lancer.passwordhelper.model.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CategoryViewModel(private val repository: MainRepository) : ViewModel() {


     var dataList = MutableLiveData<List<Category>>()


    fun requestCategoryList() {
        viewModelScope.launch(Dispatchers.IO) {
            dataList.value = repository.getCategoryListFromDataBase()
        }
    }

}