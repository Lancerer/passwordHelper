package com.lancer.passwordhelper.ui

import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.lancer.passwordhelper.base.BaseActivity
import com.lancer.passwordhelper.R
import com.lancer.passwordhelper.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun initView() {
        val navController = findNavController(R.id.nav_host_fragment)
        binding.mainBottomView.setupWithNavController(navController)
    }

    override fun initData() {
    }

    override fun initLayout(): Int = R.layout.activity_main
}