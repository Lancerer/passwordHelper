package com.lancer.passwordhelper.utils

import android.app.Activity
import android.content.Intent
import com.lancer.passwordhelper.extension.showToast

/**
 *
 *created by Lancer on 2020/7/19
 *desc
 */


const val SHARE_MORE = 0
const val SHARE_QQ = 1
const val SHARE_WECHAT = 2
const val SHARE_WEIBO = 3
const val SHARE_QQZONE = 4
const val SHARE_WECHAT_MEMORIES = 5

object ShareUtils {
    private fun processShare(activity: Activity, shareContent: String, shareType: Int) {
        when (shareType) {
            SHARE_QQ -> {
                if (!CommonUtils.isQQInstalled()) {
                   "手机未安装QQ".showToast()
                    return
                }
                share(activity, shareContent, "com.tencent.mobileqq", "com.tencent.mobileqq.activity.JumpActivity")
            }
            SHARE_WECHAT -> {
                if (!CommonUtils.isWechatInstalled()) {
                    "手机未安装微信".showToast()
                    return
                }
                share(activity, shareContent, "com.tencent.mm", "com.tencent.mm.ui.tools.ShareImgUI")
            }

            SHARE_WEIBO -> {
                if (!CommonUtils.isWeiboInstalled()) {
                   "手机未安装微博".showToast()
                    return
                }
                share(activity, shareContent, "com.sina.weibo", "com.sina.weibo.composerinde.ComposerDispatchActivity")
            }
            SHARE_QQZONE -> {
                if (!CommonUtils.isWeiboInstalled()) {
                  "手机未安装QQ空间".showToast()
                    return
                }
                share(activity, shareContent, "com.qzone", "com.qzonex.module.operation.ui.QZonePublishMoodActivity")
            }
            SHARE_MORE -> {
                share(activity, shareContent)
            }
        }
    }
    private fun share(
        activity: Activity,
        shareContent: String,
        packageName: String,
        className: String
    ) {
        try {
            val shareIntent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, shareContent)
                setClassName(packageName, className)
            }
            activity.startActivity(shareIntent)
        } catch (e: Exception) {
            "分享出现未知异常".showToast()
        }
    }

    private fun share(activity: Activity, shareContent: String) {
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, shareContent)
        }
        activity.startActivity(Intent.createChooser(intent, "分享到"))
    }

    fun share(activity: Activity, shareContent: String, shareType: Int) {
        processShare(activity, shareContent, shareType)
    }
}