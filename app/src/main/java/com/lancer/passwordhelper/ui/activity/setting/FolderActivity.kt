package com.lancer.passwordhelper.ui.activity.setting

import com.lancer.passwordhelper.base.BaseActivity
import com.lancer.passwordhelper.R
import com.lancer.passwordhelper.databinding.ActivityFolderBinding
import com.lancer.passwordhelper.extension.showToast

class FolderActivity : BaseActivity<ActivityFolderBinding>() {
    override fun initView() {
        "FolderActivity".showToast()
    }

    override fun initData() {
    }

    override fun initLayout(): Int = R.layout.activity_folder

}