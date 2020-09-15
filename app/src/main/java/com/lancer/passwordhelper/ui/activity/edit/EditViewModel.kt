package com.lancer.passwordhelper.ui.activity.edit


import com.lancer.passwordhelper.base.BaseViewModel
import com.lancer.passwordhelper.model.MainRepository
import com.lancer.passwordhelper.model.bean.Card
import kotlinx.coroutines.Job

/**
 * @author lancer
 * @des
 * @Date 2020/7/27 15:53
 */
class EditViewModel(private val repository: MainRepository) : BaseViewModel() {

    fun deleteCard(card: Card): Job {
        return launch(
            block = {
                repository.deleteCard(card)
            },
            onError = { errorMsg ->
                mExceptionLiveData.value = errorMsg
            },
            onComplete = {

            }
        )
    }

}