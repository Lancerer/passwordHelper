package com.lancer.passwordhelper.ui.activity.setting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lancer.eyelast.base.BaseActivity
import com.lancer.passwordhelper.R
import com.lancer.passwordhelper.databinding.ActivityAccountManagementBinding
import com.lancer.passwordhelper.extension.showToast

class AccountManagementActivity : BaseActivity<ActivityAccountManagementBinding>() {
    override fun initView() {
        "AccountManagementActivity".showToast()
    }

    override fun initData() {
    }

    override fun initLayout(): Int=R.layout.activity_account_management

}