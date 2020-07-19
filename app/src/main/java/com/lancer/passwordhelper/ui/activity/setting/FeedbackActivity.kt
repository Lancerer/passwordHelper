package com.lancer.passwordhelper.ui.activity.setting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lancer.eyelast.base.BaseActivity
import com.lancer.passwordhelper.R
import com.lancer.passwordhelper.databinding.ActivityFeedbackBinding
import com.lancer.passwordhelper.extension.showToast

class FeedbackActivity : BaseActivity<ActivityFeedbackBinding>() {
    override fun initView() {
        "FeedbackActivity".showToast()
    }

    override fun initData() {
    }

    override fun initLayout(): Int = R.layout.activity_feedback
}