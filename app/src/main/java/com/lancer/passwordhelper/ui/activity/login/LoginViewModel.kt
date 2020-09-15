package com.lancer.passwordhelper.ui.activity.login

import androidx.lifecycle.*
import com.lancer.passwordhelper.api.Banner
import com.lancer.passwordhelper.api.Resource
import com.lancer.passwordhelper.base.BaseViewModel
import com.lancer.passwordhelper.model.MainRepository
import kotlinx.coroutines.Dispatchers
import java.lang.Exception

/**
 * @author lancer
 * @des
 * @Date 2020/7/28 16:39
 */
class LoginViewModel(private val repository: MainRepository) : BaseViewModel() {

    //TODO
    var isLoginSuccess = MutableLiveData<Boolean>()

    fun login(username: String, password: String) {
        launch({
            val login = repository.login(username, password)
            isLoginSuccess.value = login.errorCode == 0
        }, { errorMsg ->
            mExceptionLiveData.value = errorMsg
        }, {

        }
        )
    }

    fun getBanner() = liveData(Dispatchers.IO) {
        emit(Resource.loading(null))
        try {
            emit(Resource.success(repository.getBanner("https://www.wanandroid.com/banner/json")))
        } catch (e: Exception) {
            emit(Resource.error(e.message, null))
        }
    }

//    private fun launch(block: suspend () -> Unit, error: suspend (Throwable) -> Unit) =
//        viewModelScope.launch {
//            try {
//                block()
//            } catch (e: Throwable) {
//                error(e)
//            }
//        }
}