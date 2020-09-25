package com.lancer.passwordhelper.ui.activity.login

import androidx.lifecycle.MutableLiveData
import com.lancer.passwordhelper.base.BaseViewModel
import com.lancer.passwordhelper.model.MainRepository

/**
 * @author lancer
 * @des
 * @Date 2020/7/28 16:39
 */
class LoginViewModel(private val repository: MainRepository) : BaseViewModel() {

    //TODO
    var isLoginSuccess = MutableLiveData<Boolean>()
    var isRegisterSuccess = MutableLiveData<Boolean>()

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


    fun register(username: String, password: String) {
        launch({
            val register = repository.register(username, password, password)
            isRegisterSuccess.value = register.errorCode == 0

        }, { errorMsg ->
            mExceptionLiveData.value = errorMsg
        }, {})
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
