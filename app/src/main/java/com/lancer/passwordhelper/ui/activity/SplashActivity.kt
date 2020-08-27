package com.lancer.passwordhelper.ui.activity

import com.lancer.passwordhelper.R
import com.lancer.passwordhelper.base.BaseActivity
import com.lancer.passwordhelper.databinding.ActivitySplashBinding

class SplashActivity : BaseActivity<ActivitySplashBinding>() {
    init {
        baseTag = SplashActivity::class.java.simpleName
    }

    override fun initView() {

    }

    override fun initData() {
    }

    override fun initLayout(): Int = R.layout.activity_splash


}