package com.lancer.passwordhelper.utils

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import com.lancer.passwordhelper.BaseApplication

/**
 *
 *created by Lancer on 2020/7/19
 *desc
 */
object CommonUtils {

    /**
     * 判断某个应用是否安装。
     * @param packageName
     * 要检查是否安装的应用包名
     * @return 安装返回true，否则返回false。
     */
    fun isInstalled(packageName: String): Boolean {
        val packageInfo: PackageInfo? = try {
            BaseApplication.context.packageManager.getPackageInfo(packageName, 0)
        } catch (e: PackageManager.NameNotFoundException) {
            null
        }
        return packageInfo != null
    }


    /**
     * 判断手机是否安装了QQ。
     */
    fun isQQInstalled() = isInstalled("com.tencent.mobileqq")

    /**
     * 判断手机是否安装了微信。
     */
    fun isWechatInstalled() = isInstalled("com.tencent.mm")

    /**
     * 判断手机是否安装了微博。
     * */
    fun isWeiboInstalled() = isInstalled("com.sina.weibo")


    /**
     * 将textView内容复制到姐剪切板
     */
    fun setTextToClipboard(text: String) {
        val cmb: ClipboardManager =
            BaseApplication.instance.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clipData = ClipData.newPlainText(null, text)
        cmb.setPrimaryClip(clipData)
    }
}