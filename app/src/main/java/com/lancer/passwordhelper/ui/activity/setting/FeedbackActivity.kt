package com.lancer.passwordhelper.ui.activity.setting

import com.lancer.passwordhelper.base.BaseActivity
import com.lancer.passwordhelper.R
import com.lancer.passwordhelper.databinding.ActivityFeedbackBinding
import com.lancer.passwordhelper.extension.showToast

class FeedbackActivity : BaseActivity<ActivityFeedbackBinding>() {
    init {
        baseTag = FeedbackActivity::class.java.simpleName
    }

    override fun initView() {
        "FeedbackActivity".showToast()
    }

    override fun initData() {
    }

    override fun initLayout(): Int = R.layout.activity_feedback
}