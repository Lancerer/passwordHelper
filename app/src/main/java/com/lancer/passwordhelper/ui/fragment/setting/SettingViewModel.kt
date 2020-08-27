package com.lancer.passwordhelper.ui.fragment.setting

import android.content.Context
import android.view.View
import androidx.lifecycle.ViewModel
import com.lancer.passwordhelper.R
import com.lancer.passwordhelper.extension.showToast


class SettingViewModel : ViewModel() {


    private fun changeTheme(context: Context) {

    }

    fun onClick(view: View) {
        when (view.id) {
            R.id.setting_account_management_iv, R.id.setting_account_management_tv -> {
                "主账号管理".showToast()
            }

        }
    }
}