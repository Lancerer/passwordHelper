package com.lancer.passwordhelper.ui.fragment.setting

import android.content.Intent
import android.graphics.Color
import android.util.Log
import android.view.View
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.input.input
import com.lancer.passwordhelper.Constant
import com.lancer.passwordhelper.R
import com.lancer.passwordhelper.base.BaseFragment
import com.lancer.passwordhelper.databinding.FragmentSettingBinding
import com.lancer.passwordhelper.extension.showToast
import com.lancer.passwordhelper.ui.activity.setting.AccountManagementActivity
import com.lancer.passwordhelper.ui.activity.setting.folder.FolderActivity
import com.lancer.passwordhelper.utils.AppPrefsUtils
import com.lancer.passwordhelper.utils.FingerPrintUtils
import com.lancer.passwordhelper.utils.SHARE_MORE
import com.lancer.passwordhelper.utils.ShareUtils
import java.util.concurrent.Executor


class SettingFragment : BaseFragment<FragmentSettingBinding>(), View.OnClickListener {


    //TODO 为什么要这样写
    private val viewModel by lazy {
        ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(SettingViewModel::class.java)
    }

    var isUserFinger: Boolean = AppPrefsUtils.getBoolean(Constant.HAS_USER_FINGER)

    init {
        name = SettingFragment::class.java.simpleName
    }

    override fun onResume() {
        super.onResume()
        initView()
        initData()
    }

    override fun initView() {
        binding.settingFingerprintSwitcch.isChecked = isUserFinger
        binding.settingToolbar.title = getString(R.string.bottom_name_setting)
        binding.settingToolbar.setTitleTextColor(Color.BLACK)
        binding.settingToolbar.setTitleTextColor(Color.BLACK)
        binding.settingAccountManagementIv.setOnClickListener(this)
        binding.settingAccountManagementTv.setOnClickListener(this)
        binding.settingThemeIv.setOnClickListener(this)
        binding.settingThemeTv.setOnClickListener(this)
//        binding.settingTagIv.setOnClickListener(this)
//        binding.settingTagTv.setOnClickListener(this)
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
                showConfirmDialog()
            } else {
                binding.settingFingerprintSwitcch.isChecked = false
                AppPrefsUtils.putBoolean(Constant.HAS_USER_FINGER, false)
            }
        }

    }

    private fun showConfirmDialog() {
        activity?.let {
            if (FingerPrintUtils.isSupportFingerPrint(it)) {
                MaterialDialog(it).show {
                    title(null, "请输入主密码")
                    input { materialDialog, charSequence ->
                        if (charSequence.toString() == AppPrefsUtils.getString(Constant.CURRENT_PASSWORD)) {
                            setFinger()
                        } else {
                            "主密码错误，请重试".showToast()
                            binding.settingFingerprintSwitcch.isChecked = false
                        }
                    }
                    negativeButton(R.string.cancel) {}
                    positiveButton(R.string.confirm) {}
                }
            } else {
                binding.settingFingerprintSwitcch.isChecked = false
            }
        }
    }

    private lateinit var biometricPrompt: BiometricPrompt
    private lateinit var promptInfo: BiometricPrompt.PromptInfo
    private lateinit var executor: Executor

    //录入指纹
    private fun setFinger() {
        executor = ContextCompat.getMainExecutor(context)
        //最终回到AuthenticationCallback获得认证结果
        biometricPrompt =
            BiometricPrompt(this, executor, object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                    //认证方式错误
                    super.onAuthenticationError(errorCode, errString)
                    Log.d("Authentication", "认证方式错误")
                    binding.settingFingerprintSwitcch.isChecked = false
                }

                override fun onAuthenticationFailed() {
                    //认证方式失败
                    super.onAuthenticationFailed()
                    Log.d("Authentication", "认证方式失败")
                    binding.settingFingerprintSwitcch.isChecked = false
                }

                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    //认证方式成功
                    super.onAuthenticationSucceeded(result)
                    Log.d("Authentication", "认证方式成功")
                    binding.settingFingerprintSwitcch.isChecked = true
                    AppPrefsUtils.putBoolean(Constant.HAS_USER_FINGER, true)
                }
            })
        //弹出验证指纹对话框
        promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("我的应用的生物识别登录")
            .setSubtitle("使用您的生物特征凭证登录")
            .setNegativeButtonText("Use account password")
            .build()

        //执行方法
        biometricPrompt.authenticate(promptInfo)

    }

    override fun initData() {
        binding.viewModel = viewModel
    }


    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.setting_account_management_iv, R.id.setting_account_management_tv -> {
                startActivity(Intent(activity, AccountManagementActivity::class.java))
            }
            R.id.setting_theme_iv, R.id.setting_theme_tv -> {
                /*         activity?.let {
                             MaterialDialog(it).show {
                                 title(R.string.setting_theme_hint)
                                 listItems(R.array.themeName) { dialog, index, text ->
                                     if (text == Constant.DEFAULT) {
                                         SkinCompatManager.getInstance().restoreDefaultTheme()
                                     } else {
                                         SkinCompatManager.getInstance().loadSkin(
                                             text.toString(),
                                             SkinCompatManager.SKIN_LOADER_STRATEGY_BUILD_IN
                                         )
                                     }
                                 }
                             }
                         }*/
            }
            /*    R.id.setting_tag_iv, R.id.setting_tag_tv -> {
                    "标签管理".showToast()
                    startActivity(Intent(activity, TagActivity::class.java))
                }*/
            R.id.setting_folder_iv, R.id.setting_folder_tv -> {
                "文件夹".showToast()
                startActivity(Intent(activity, FolderActivity::class.java))
            }
            R.id.setting_in_out_iv, R.id.setting_in_out_tv -> {
                "导入导出功能".showToast()
//                startActivity(Intent(activity, InOutActivity::class.java))
            }
            R.id.setting_share_iv, R.id.setting_share_tv -> {
                activity?.let { ShareUtils.share(it, "pwHelper", SHARE_MORE) }
            }
            R.id.setting_feedback_iv, R.id.setting_feedback_tv -> {
                "反馈".showToast()
            }
            R.id.setting_update_iv, R.id.setting_update_tv -> {
                getString(R.string.no_update_hint).showToast()
            }
            R.id.setting_about_iv, R.id.setting_about_tv -> {
                "mr.lancer@qq.com".showToast()
            }
            else -> {
                getString(R.string.unknow).showToast()
            }
        }
    }

    override fun initLayout(): Int = R.layout.fragment_setting
    override fun onDestroy() {
        super.onDestroy()
        binding.unbind()
    }
}