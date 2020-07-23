package com.lancer.passwordhelper.ui.activity.setting

import com.lancer.passwordhelper.base.BaseActivity
import com.lancer.passwordhelper.R
import com.lancer.passwordhelper.databinding.ActivityTagBinding
import com.lancer.passwordhelper.extension.showToast

class TagActivity : BaseActivity<ActivityTagBinding>() {

    init {
        baseTag = TagActivity::class.java.simpleName
    }

    override fun initView() {
        "tag".showToast()
    }

    override fun initData() {
    }

    override fun initLayout(): Int = R.layout.activity_tag
}