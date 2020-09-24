package com.lancer.passwordhelper.ui.activity.login

import android.text.TextUtils
import android.util.Log
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
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
import java.util.concurrent.Executor

class LoginActivity : BaseActivity<ActivityLoginBinding>() {

    init {
        baseTag = LoginActivity::class.java.simpleName
    }

    var username: String? = null
    var password: String? = null

    //是否使用指纹识别
    var isUserFinger: Boolean = AppPrefsUtils.getBoolean(Constant.HAS_USER_FINGER)

    private val viewModel by lazy {
        ViewModelProvider(
            this,
            InjectorUtil.getLoginViewModelFactory()
        ).get(LoginViewModel::class.java)
    }

    override fun initView() {
        if (!isUserFinger) {
            login()
        } else {
            setFinger()
        }
    }

    private fun login() {
        binding.loginLoginBt.setOnClickListener {
            if (!AppPrefsUtils.getBoolean(Constant.IS_FIRST_LOGIN)) {
                if (!judge()) {
                    return@setOnClickListener
                } else {
                    AppPrefsUtils.putString(Constant.CURRENT_USERNAME, username!!)
                    AppPrefsUtils.putString(Constant.CURRENT_PASSWORD, password!!)
                    start(MainActivity::class.java)
                    finish()
                }
            }
        }

        /* binding.loginLoginBt.setOnClickListener {
             if (!judge()) {
                 return@setOnClickListener
             }
             viewModel.login(username!!, password!!)
         }

         binding.loginRegisterBt.setOnClickListener {
             if (!judge()) {
                 return@setOnClickListener
             }
             viewModel.register(username!!, password!!)
         }
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
         viewModel.isRegisterSuccess.observe(this, Observer { success ->
             if (success) {
                 AppPrefsUtils.putString(Constant.CURRENT_USERNAME, username!!)
                 AppPrefsUtils.putString(Constant.CURRENT_PASSWORD, password!!)
                 start(MainActivity::class.java)
                 finish()
             } else {
                 "登陆失败".showToast()
             }
         })*/
    }

    private lateinit var biometricPrompt: BiometricPrompt
    private lateinit var promptInfo: BiometricPrompt.PromptInfo
    private lateinit var executor: Executor

    private fun setFinger() {
        executor = ContextCompat.getMainExecutor(this)
        //最终回到AuthenticationCallback获得认证结果
        biometricPrompt =
            BiometricPrompt(this, executor, object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                    //认证方式错误
                    super.onAuthenticationError(errorCode, errString)
                    Log.d("Authentication", "认证方式错误")
                    login()
                }

                override fun onAuthenticationFailed() {
                    //认证方式失败
                    super.onAuthenticationFailed()
                    Log.d("Authentication", "认证方式失败")
                }

                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    //认证方式成功
                    super.onAuthenticationSucceeded(result)
                    Log.d("Authentication", "认证方式成功")
                    start(MainActivity::class.java)
                    finish()
                }
            })
        //弹出验证指纹对话框
        promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("我的应用的生物识别登录")
            .setSubtitle("使用您的生物特征凭证登录")
            .setNegativeButtonText("使用账号密码登录")
            .setDeviceCredentialAllowed(false)
            .build()

        //执行方法
        biometricPrompt.authenticate(promptInfo)
    }

    private fun judge(): Boolean {
        username = binding.loginUsernameEt.text.trim().toString()
        password = binding.loginPasswordEt.text.trim().toString()

        if (TextUtils.isEmpty(username) || username?.length!! < 6) {
            "账号不能为空或者账号长度必须大于6".showToast()
            return false
        }
        if (TextUtils.isEmpty(password) || password?.length!! < 6) {
            "账号不能为空或者账号长度必须大于6".showToast()
            return false
        }
        return true

    }

    override fun initData() {
    }

    override fun initLayout(): Int = R.layout.activity_login

}