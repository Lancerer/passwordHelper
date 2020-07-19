package com.lancer.passwordhelper.ui.fragment.setting

import android.content.Intent
import android.graphics.Color
import android.view.View
import com.lancer.eyelast.base.BaseFragment
import com.lancer.passwordhelper.R
import com.lancer.passwordhelper.databinding.FragmentSettingBinding
import com.lancer.passwordhelper.extension.showToast
import com.lancer.passwordhelper.ui.activity.setting.*
import com.lancer.passwordhelper.utils.SHARE_MORE
import com.lancer.passwordhelper.utils.ShareUtils

class SettingFragment : BaseFragment<FragmentSettingBinding>(), View.OnClickListener {

    override fun initView() {
        binding.settingToolbar.setTitle(getString(R.string.bottom_name_setting))
        binding.settingToolbar.setTitleTextColor(Color.BLACK)

        binding.settingAccountManagementIv.setOnClickListener(this)
        binding.settingAccountManagementTv.setOnClickListener(this)
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

        binding.settingFingerprintSwitcch.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                "勾选上".showToast()
            } else {
                "未勾选上".showToast()
            }
        }

        binding.settingLongClickSwitcch.setOnCheckedChangeListener{ buttonView, isChecked ->
            if (isChecked) {
                "勾选上".showToast()
            } else {
                "未勾选上".showToast()
            }
        }
    }

    override fun initData() {
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.setting_account_management_iv, R.id.setting_account_management_tv -> {
                startActivity(Intent(activity, AccountManagementActivity::class.java))
            }
            R.id.setting_theme_iv, R.id.setting_theme_tv -> {
                "切换主题".showToast()
            }
            R.id.setting_tag_iv, R.id.setting_tag_tv -> {
                startActivity(Intent(activity, TagActivity::class.java))
            }
            R.id.setting_folder_iv, R.id.setting_folder_tv -> {
                startActivity(Intent(activity, FolderActivity::class.java))
            }
            R.id.setting_in_out_iv, R.id.setting_in_out_tv -> {
                startActivity(Intent(activity, InOutActivity::class.java))

            }
            R.id.setting_share_iv, R.id.setting_share_tv -> {
                activity?.let { ShareUtils.share(it, "pwHelper", SHARE_MORE) }
            }
            R.id.setting_feedback_iv, R.id.setting_feedback_tv -> {
                startActivity(Intent(activity, FeedbackActivity::class.java))
            }
            R.id.setting_update_iv, R.id.setting_update_tv -> {
                getString(R.string.no_update_hint).showToast()
            }
            R.id.setting_about_iv, R.id.setting_about_tv -> {
                startActivity(Intent(activity, AboutActivity::class.java))
            }
            else -> {
                getString(R.string.unknow).showToast()
            }
        }
    }
    override fun initLayout(): Int = R.layout.fragment_setting

}