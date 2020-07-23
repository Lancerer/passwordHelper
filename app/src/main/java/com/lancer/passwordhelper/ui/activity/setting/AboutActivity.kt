package com.lancer.passwordhelper.ui.activity.setting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lancer.passwordhelper.R
import com.lancer.passwordhelper.base.BaseActivity
import com.lancer.passwordhelper.databinding.ActivityAboutBinding
import com.lancer.passwordhelper.ui.activity.SplashActivity

class AboutActivity : BaseActivity<ActivityAboutBinding>() {
    init {
        baseTag = AboutActivity::class.java.simpleName
    }
    override fun initView() {

    }

    override fun initData() {
    }

    override fun initLayout(): Int = R.layout.activity_about

}