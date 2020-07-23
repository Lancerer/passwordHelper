package com.lancer.passwordhelper.ui.activity.setting

import com.lancer.passwordhelper.base.BaseActivity
import com.lancer.passwordhelper.R
import com.lancer.passwordhelper.databinding.ActivityInOutBinding
import com.lancer.passwordhelper.extension.showToast

class InOutActivity : BaseActivity<ActivityInOutBinding>() {
    init {
        baseTag = InOutActivity::class.java.simpleName
    }

    override fun initView() {
       "InOutActivity".showToast()
    }

    override fun initData() {
    }

    override fun initLayout(): Int=R.layout.activity_in_out

}