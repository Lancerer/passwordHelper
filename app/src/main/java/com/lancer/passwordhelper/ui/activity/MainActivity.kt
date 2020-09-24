package com.lancer.passwordhelper.ui.activity

import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.lancer.passwordhelper.base.BaseActivity
import com.lancer.passwordhelper.R
import com.lancer.passwordhelper.databinding.ActivityMainBinding
import okhttp3.*
import java.io.IOException
import java.util.concurrent.TimeUnit

class MainActivity : BaseActivity<ActivityMainBinding>() {
    init {
        baseTag = MainActivity::class.java.simpleName
    }

    override fun initView() {
        val navController = findNavController(R.id.nav_host_fragment)
        binding.mainBottomView.setupWithNavController(navController)
    }

    override fun initData() {
    }

    override fun initLayout(): Int = R.layout.activity_main


    //okHttp同步请求
    private fun execute() {
        val client = OkHttpClient.Builder()
            .readTimeout(5000L, TimeUnit.SECONDS)
            .writeTimeout(5000L, TimeUnit.SECONDS)
            .build()
        val request = Request.Builder()
            .get()
            .url("http://www.baidu.com")
            .build()

        val call = client.newCall(request)

        val response = call.execute()
    }

    //okHttp异步请求
    private fun enqueue() {
        val client = OkHttpClient.Builder()
            .readTimeout(5000L, TimeUnit.SECONDS)
            .writeTimeout(5000L, TimeUnit.SECONDS)
            .build()
        val request = Request.Builder()
            .post(RequestBody.create(MediaType.get("text/pain"), ""))
            .url("http://www.baidu.com")
            .build()

        val call = client.newCall(request)

        call.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
            }

            override fun onResponse(call: Call, response: Response) {
            }

        })
    }
}