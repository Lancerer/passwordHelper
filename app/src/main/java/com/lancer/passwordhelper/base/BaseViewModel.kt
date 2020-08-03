package com.lancer.passwordhelper.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lancer.passwordhelper.model.network.exception.ExceptionHandle
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

/**
 * @author lancer
 * @des
 * @Date 2020/7/31 10:46
 */
open class BaseViewModel : ViewModel() {
    /**
     * 统一异常处理liveData
     */
    var mExceptionLiveData: MutableLiveData<String> = MutableLiveData()


    /**
     * viewModel的拓展方法
     * block：协程主体
     * onError：错误回调
     * onComplete：完成回调
     *
     * onError方法在launch构造器中接收协程错误回调，block执行主要逻辑方法，onComplete方法在finally中回调
     */
    fun ViewModel.launch(
        block: suspend CoroutineScope.() -> Unit,
        onError: (e: String) -> Unit = {},
        onComplete: () -> Unit = {}
    ): Job {
        return viewModelScope.launch(CoroutineExceptionHandler { _, e ->
            onError(
                ExceptionHandle.handleException(e)
            )
        }) {
            try {
                block.invoke(this)
            } finally {
                onComplete()
            }
        }
    }

    /*
 launch是CoroutineScope的拓展方法，以下为他的一些参数
 launch(
     context: CoroutineContext = EmptyCoroutineContext,
     start: CoroutineStart = CoroutineStart.DEFAULT,
     block: suspend CoroutineScope.() -> Unit
     )*/
}