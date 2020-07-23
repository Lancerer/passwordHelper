package com.lancer.passwordhelper.ui.activity.login

import com.lancer.passwordhelper.base.BaseActivity
import com.lancer.passwordhelper.Constant
import com.lancer.passwordhelper.R
import com.lancer.passwordhelper.databinding.ActivityLoginBinding
import com.lancer.passwordhelper.ui.activity.MainActivity
import com.lancer.passwordhelper.ui.activity.input.InputActivity
import com.lancer.passwordhelper.utils.AppPrefsUtils

class LoginActivity : BaseActivity<ActivityLoginBinding>() {

    init {
        baseTag = LoginActivity::class.java.simpleName
    }

    override fun initView() {
        binding.loginLoginBt.setOnClickListener {
            start(MainActivity::class.java)
            finish()
        }

        binding.loginRegisterTv.setOnClickListener {
            start(RegisterActivity::class.java)
        }
    }

    override fun initData() {
        if (AppPrefsUtils.getString(Constant.CURRENT_USERNAME).isNotEmpty()) {
            binding.loginUsernameEt.setText(AppPrefsUtils.getString(Constant.CURRENT_USERNAME))
        }
    }

    override fun initLayout(): Int = R.layout.activity_login

}