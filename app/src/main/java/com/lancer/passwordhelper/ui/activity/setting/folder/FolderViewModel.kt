package com.lancer.passwordhelper.ui.activity.setting.folder

import androidx.lifecycle.MutableLiveData
import com.lancer.passwordhelper.base.BaseViewModel
import com.lancer.passwordhelper.model.MainRepository
import com.lancer.passwordhelper.model.bean.Category


/**
 * @author lancer
 * @des
 * @Date 2020/8/4 17:01
 */
class FolderViewModel(private val repository: MainRepository) : BaseViewModel() {
    val dataList = MutableLiveData<List<Category>>()

    fun getCategory() {
        launch({
            dataList.value = repository.getCategoryListFromDataBase()
        }, {
            mExceptionLiveData.value = it
        }, {})
    }

    fun saveCategory(category: Category) {
        launch({
            repository.saveCategory(category)
        }, { mExceptionLiveData.value = it }, {

        })
    }

    fun deleteCategory(category: Category) {
        launch({
            repository.deleteCategory(category)
        }, {
            mExceptionLiveData.value = it
        }, {})
    }
}