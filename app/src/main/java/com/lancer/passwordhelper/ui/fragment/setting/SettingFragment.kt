package com.lancer.passwordhelper.ui.fragment.setting

import android.graphics.Color
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lancer.eyelast.base.BaseFragment
import com.lancer.passwordhelper.R
import com.lancer.passwordhelper.databinding.FragmentSettingBinding
import com.lancer.passwordhelper.extension.showToast

class SettingFragment : BaseFragment<FragmentSettingBinding>(), View.OnClickListener {

    override fun initView() {
        binding.settingToolbar.setTitle(getString(R.string.bottom_name_setting))
        binding.settingToolbar.setTitleTextColor(Color.BLACK)

        binding.settingAccountManagementIv.setOnClickListener(this)
        binding.settingAccountManagementTv.setOnClickListener(this)
        binding.settingFingerprintIv.setOnClickListener(this)
        binding.settingFingerprintTv.setOnClickListener(this)
        binding.settingLongClickIv.setOnClickListener(this)
        binding.settingLongClickTv.setOnClickListener(this)
        binding.settingThemeIv.setOnClickListener(this)
        binding.settingThemeTv.setOnClickListener(this)
        binding.settingTagIv.setOnClickListener(this)
        binding.settingTagTv.setOnClickListener(this)
        binding.settingFolderIv.setOnClickListener(this)
        binding.settingFolderTv.setOnClickListener(this)
        binding.settingInOutIv.setOnClickListener(this)
        binding.settingInOutTv.setOnClickListener(this)
        binding.settingShareIv.setOnClickListener(this)
        binding.settingShareTv.setOnClickListener(this)
        binding.settingFeedbackIv.setOnClickListener(this)
        binding.settingFeedbackTv.setOnClickListener(this)
        binding.settingUpdateIv.setOnClickListener(this)
        binding.settingUpdateTv.setOnClickListener(this)
        binding.settingAboutIv.setOnClickListener(this)
        binding.settingAboutTv.setOnClickListener(this)

    }

    override fun initData() {
    }

    override fun initLayout(): Int = R.layout.fragment_setting
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.setting_account_management_iv, R.id.setting_account_management_tv -> {

            }
            R.id.setting_fingerprint_iv, R.id.setting_fingerprint_tv -> {

            }
            R.id.setting_long_click_iv, R.id.setting_long_click_tv -> {

            }
            R.id.setting_theme_iv, R.id.setting_theme_tv -> {

            }
            R.id.setting_tag_iv, R.id.setting_tag_tv -> {

            }
            R.id.setting_folder_iv, R.id.setting_folder_tv -> {

            }
            R.id.setting_in_out_iv, R.id.setting_in_out_tv -> {

            }
            R.id.setting_share_iv, R.id.setting_share_tv -> {

            }
            R.id.setting_feedback_iv, R.id.setting_feedback_tv -> {

            }
            R.id.setting_update_iv, R.id.setting_update_tv -> {

            }
            R.id.setting_about_iv, R.id.setting_about_tv -> {

            }
            else -> {
                getString(R.string.unknow).showToast()
            }
        }
    }

}