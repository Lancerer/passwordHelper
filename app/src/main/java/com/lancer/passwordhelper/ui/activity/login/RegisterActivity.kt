package com.lancer.passwordhelper.ui.activity.login

import com.lancer.passwordhelper.base.BaseActivity
import com.lancer.passwordhelper.Constant
import com.lancer.passwordhelper.R
import com.lancer.passwordhelper.databinding.ActivityRegisterBinding
import com.lancer.passwordhelper.extension.showToast
import com.lancer.passwordhelper.utils.AppPrefsUtils

class RegisterActivity : BaseActivity<ActivityRegisterBinding>() {

    private var username: String? = null
    private var password: String? = null
    private var confirmPassword: String? = null
    override fun initView() {

    }

    override fun initData() {
        binding.registerLoginTv.setOnClickListener {
            finish()
        }
        binding.registerRegisterBt.setOnClickListener {
            register()
        }
    }

    private fun register() {
        username = binding.registerUsernameEt.text.trim().toString()
        password = binding.registerPasswordEt.text.trim().toString()
        confirmPassword = binding.registerConfirmPasswordEt.text.trim().toString()

        if (!username.isNullOrBlank() && !password.isNullOrBlank() && !confirmPassword.isNullOrBlank()) {
            if (password.equals(confirmPassword)) {
                AppPrefsUtils.putString(Constant.CURRENT_USERNAME, username!!)
                AppPrefsUtils.putString(Constant.CURRENT_PASSWORD, password!!)
                finish()
            } else {
                getString(R.string.password_diff_hint).showToast()
            }
        } else {
            getString(R.string.register_null_hint).showToast()
        }

    }

    override fun initLayout(): Int = R.layout.activity_register

}