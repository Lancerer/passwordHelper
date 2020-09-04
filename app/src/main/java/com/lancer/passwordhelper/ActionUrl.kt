package com.lancer.passwordhelper

import android.app.Activity
import androidx.databinding.ViewDataBinding
import com.lancer.passwordhelper.base.BaseFragment
import com.lancer.passwordhelper.extension.showToast
import com.lancer.passwordhelper.ui.activity.web.WebActivity
import java.net.URLDecoder

object ActionUrl {
    /**
     * 处理ActionUrl事件。
     *
     * @param fragment 上下文环境
     * @param actionUrl 待处理的url
     * @param toastTitle toast提示标题 or 没有匹配的事件需要处理，给出的提示标题。
     */
    fun <V : ViewDataBinding>process(fragment: BaseFragment<V>, actionUrl: String?, toastTitle: String = "") {
        process(fragment.activity!! ,actionUrl, toastTitle)
    }

    /**
     * 处理ActionUrl事件。
     *
     * @param activity 上下文环境
     * @param actionUrl 待处理的url
     * @param toastTitle toast提示标题 or 没有匹配的事件需要处理，给出的提示标题。
     */
    fun process(activity: Activity, actionUrl: String?, toastTitle: String = "") {
        if (actionUrl == null) return
        val decodeUrl = URLDecoder.decode(actionUrl, "UTF-8")
        when {
            decodeUrl.startsWith(Const.ActionUrl.WEBVIEW) -> {
                WebActivity.start(activity, decodeUrl.getWebViewInfo().first(), decodeUrl.getWebViewInfo().last())
            }

            else -> {
                "该功能即将开放，敬请期待".showToast()
            }
        }
    }

    /**
     * 截取标题与url信息。
     *
     * @return first=标题 last=url
     */
    private fun String.getWebViewInfo(): Array<String> {
        val title = this.split("title=").last().split("&url").first()
        val url = this.split("url=").last()
        return arrayOf(title, url)
    }

}