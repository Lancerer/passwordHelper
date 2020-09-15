package com.lancer.passwordhelper.ui.activity.login

import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.lancer.passwordhelper.base.BaseActivity
import com.lancer.passwordhelper.Constant
import com.lancer.passwordhelper.InjectorUtil
import com.lancer.passwordhelper.R
import com.lancer.passwordhelper.api.Banner
import com.lancer.passwordhelper.api.Resource
import com.lancer.passwordhelper.api.Status
import com.lancer.passwordhelper.databinding.ActivityLoginBinding
import com.lancer.passwordhelper.extension.showToast
import com.lancer.passwordhelper.ui.activity.MainActivity
import com.lancer.passwordhelper.ui.activity.input.InputActivity
import com.lancer.passwordhelper.utils.AppPrefsUtils

class LoginActivity : BaseActivity<ActivityLoginBinding>() {

    init {
        baseTag = LoginActivity::class.java.simpleName
    }

    var username: String? = null
    var password: String? = null

    private val viewModel by lazy {
        ViewModelProvider(
            this,
            InjectorUtil.getLoginViewModelFactory()
        ).get(LoginViewModel::class.java)
    }

    override fun initView() {
        binding.loginLoginBt.setOnClickListener {
            username = binding.loginUsernameEt.text.trim().toString()
            password = binding.loginPasswordEt.text.trim().toString()

            if (username.isNullOrEmpty() || password.isNullOrEmpty()) {
                "账号或密码不能为空".showToast()
            } else {
                viewModel.login(username!!, password!!)
            }
        }

        binding.loginRegisterTv.setOnClickListener {
            //TODO 使用startActivityForResult
            start(RegisterActivity::class.java)
        }
        viewModel.getBanner()
        viewModel.getBanner().observe(this, Observer {
            it?.let { resource: Resource<out Banner?> ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        resource.data?.let { banner ->
                            Log.d("lancerr", banner.toString())
                        }
                    }
                    Status.ERROR -> {

                    }
                    Status.LOADING -> {

                    }
                }
            }
        })
    }

    override fun initData() {
        if (AppPrefsUtils.getString(Constant.CURRENT_USERNAME).isNotEmpty()) {
            binding.loginUsernameEt.setText(AppPrefsUtils.getString(Constant.CURRENT_USERNAME))
            binding.loginPasswordEt.setText(AppPrefsUtils.getString(Constant.CURRENT_PASSWORD))
        }

        viewModel.isLoginSuccess.observe(this, Observer { success ->
            if (success) {
                AppPrefsUtils.putString(Constant.CURRENT_USERNAME, username!!)
                AppPrefsUtils.putString(Constant.CURRENT_PASSWORD, password!!)
                start(MainActivity::class.java)
                finish()
            } else {
                "登陆失败".showToast()
            }
        })
    }

    override fun initLayout(): Int = R.layout.activity_login

}