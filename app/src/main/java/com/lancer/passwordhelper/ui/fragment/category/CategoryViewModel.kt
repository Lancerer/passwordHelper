package com.lancer.passwordhelper.ui.fragment.category

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lancer.passwordhelper.bean.Category
import com.lancer.passwordhelper.model.MainRepository

class CategoryViewModel(private val repository: MainRepository) : ViewModel() {

    private var requestCategoryList = MutableLiveData<List<Category>>()

    /* val dataListLiveData = Transformations.switchMap(requestCategoryList) {
         liveData {
             val result = try {
                 val refreshCategory = repository.refreshCategory()
                 Result.success(refreshCategory)
             } catch (e: Exception) {
                 Result.failure<List<Category>>(e)
             }
             emit(result)
         }
     }*/
}