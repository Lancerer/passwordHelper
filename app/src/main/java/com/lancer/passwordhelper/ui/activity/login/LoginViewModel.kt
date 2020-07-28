package com.lancer.passwordhelper.ui.activity.login

import androidx.lifecycle.*
import com.lancer.passwordhelper.api.BaseResponse
import com.lancer.passwordhelper.api.LoginBean
import com.lancer.passwordhelper.model.MainRepository
import kotlinx.coroutines.launch
import java.lang.Exception

/**
 * @author lancer
 * @des
 * @Date 2020/7/28 16:39
 */
class LoginViewModel(private val repository: MainRepository) : ViewModel() {

    //TODO
    var isLoginSuccess = MutableLiveData<Boolean>()

//    fun login(username: String, password: String) {
//        Transformations.switchMap(isLoginSuccess) { isSuccess ->
//            liveData {
//                val result = try {
//                    val login = repository.login(username, password)
//                    Result.success(login)
//                } catch (e: Exception) {
//                    Result.failure<BaseResponse<LoginBean>>(e)
//                }
//                emit(result)
//            }
//        }
//    }

    private fun launch(block: suspend () -> Unit, error: suspend (Throwable) -> Unit) =
        viewModelScope.launch {
            try {
                block()
            } catch (e: Throwable) {
                error(e)
            }
        }
}