package com.lancer.passwordhelper.ui.activity.setting

import com.lancer.passwordhelper.R
import com.lancer.passwordhelper.base.BaseActivity
import com.lancer.passwordhelper.databinding.ActivityAboutBinding

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