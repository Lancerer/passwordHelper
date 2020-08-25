package com.lancer.passwordhelper.ui.activity.categoryitem

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.lancer.passwordhelper.base.BaseViewModel
import com.lancer.passwordhelper.model.MainRepository
import com.lancer.passwordhelper.model.bean.Card

/**
 * @author lancer
 * @des
 * @Date 2020/8/11 9:04
 */
class CategoryItemViewModel(private val repository: MainRepository) : BaseViewModel() {

    val dataList = MutableLiveData<List<Card>>()

    fun getCardListByCategory(categoryName: String) {
        launch(
            {
                dataList.value = repository.findCardListByCategoryName(categoryName)
            }, {
                mExceptionLiveData.value = it
            }, {
                Log.d(CategoryItemViewModel::class.java.simpleName, "onComplete")
            }
        )
    }
}