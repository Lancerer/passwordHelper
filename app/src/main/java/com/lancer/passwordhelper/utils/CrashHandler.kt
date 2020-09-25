package com.lancer.passwordhelper.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Build
import android.os.Environment
import android.os.Looper
import android.os.SystemClock
import android.util.Log
import com.lancer.passwordhelper.extension.showToast
import java.io.FileNotFoundException
import java.io.IOException
import java.io.PrintWriter
import java.io.StringWriter
import java.lang.reflect.Field
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.concurrent.thread
import kotlin.system.exitProcess


/**
 * @author lancer
 * @des 全局异常捕获
 * @Date 2020/8/26 16:10
 */
object CrashHandler : Thread.UncaughtExceptionHandler {

    private val TAG: String = CrashHandler::class.java.simpleName

    private lateinit var context: Context

    // 用来存储设备信息和异常信息
    private val infos: Map<String, String> = linkedMapOf()

    // 用于格式化日期,作为日志文件名的一部分
    @SuppressLint("SimpleDateFormat")
    private val formatter: DateFormat = SimpleDateFormat("yyyy-MM-dd")

    // 系统默认的UncaughtException处理类
    private val mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler()

    /**
     * 初始化方法
     */
    fun init(context: Context) {
        this.context = context
        //设置该CrashHandler为程序的默认处理器
        Thread.setDefaultUncaughtExceptionHandler(mDefaultHandler)
        //默认每隔三十天清除一次log
        clearCrashFile()
    }

    //当UncaughtException发生时会转入该函数来处理
    override fun uncaughtException(t: Thread, e: Throwable) {
        if (!handleException(e) && mDefaultHandler != null) {
            // 如果用户没有设置则让系统默认的异常处理器来处理
            mDefaultHandler.uncaughtException(t, e)
        } else {
            SystemClock.sleep(3000)
            //退出程序
            android.os.Process.killProcess(android.os.Process.myPid())
            exitProcess(1)
        }
    }

    /**
     * 自定义错误处理,收集错误信息 发送错误报告等操作均在此完成.
     *
     * @param ex
     * @return true:如果处理了该异常信息; 否则返回false.
     */
    private fun handleException(e: Throwable?): Boolean {
        if (e == null) {
            return false
        }
        try {
            thread {
                Looper.prepare();
                "很抱歉,程序出现异常,即将重启".showToast()
                Looper.loop();
            }.start()
            getDeviceInfo(context)
            saveCrashInfo(e)
            SystemClock.sleep(3000)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return true
    }

    //收集设备参数信息
    private fun getDeviceInfo(context: Context) {
        try {
            val pm: PackageManager = context.packageManager
            val pi: PackageInfo = pm.getPackageInfo(
                context.packageName,
                PackageManager.GET_ACTIVITIES
            )
            if (pi != null) {
                val versionName = pi.versionName + ""
                val versionCode = pi.versionCode.toString() + ""

//                infos[1]=versionName
//                infos.put("versionCode", versionCode)
            }
        } catch (e: PackageManager.NameNotFoundException) {
            Log.e(TAG, "an error occured when collect package info", e)
        }
        val fields: Array<Field> = Build::class.java.declaredFields
        for (field in fields) {
            try {
                field.isAccessible = true
                // infos.put(field.name, field.get(null).toString())
            } catch (e: Exception) {
                Log.e(TAG, "an error occured when collect crash info", e)
            }
        }

    }

    //  TODO
    // 保存错误信息到文件中
    private fun saveCrashInfo(ex: Throwable): String? {
        val stringBuffer = StringBuffer()
        //获得设备信息
        for (entry in infos.entries) {
            val key = entry.key
            val value = entry.value
            stringBuffer.append("$key=$value\n")
        }
        //获得错误信息
        val writer = StringWriter()
        val printWriter = PrintWriter("")
        ex.printStackTrace(printWriter)
        var cause = ex.cause
        while (cause != null) {
            cause.printStackTrace(printWriter)
            cause = ex.cause
        }
        printWriter.close()
        val result = writer.toString()
        stringBuffer.append(result)

        //写入文件
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd_HH-mm-ss", Locale.CHINA)
        val rootPath = Environment.getExternalStorageDirectory().absolutePath
        val crashFileName: String =
            "${rootPath}/crash_${simpleDateFormat.format(Date())}.log"
        try {
            val fos = java.io.FileOutputStream(crashFileName)
            fos.write(stringBuffer.toString().toByteArray())
            fos.close()
            return crashFileName
        } catch ( e: FileNotFoundException) {
            Log.e(TAG, "an FileNotFoundException occured when write crashfile to sdcard", e)
            e.printStackTrace()
        } catch ( e: IOException) {
            Log.e(TAG, "an IOException occured when write crashfile to sdcard", e)
            e.printStackTrace()
        }
        return null
    }

    private fun writeToFile(content: String): String {
        return ""
    }

    private fun clearCrashFile(day: Int = 30) {

    }

}