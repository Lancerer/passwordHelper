package com.lancer.passwordhelper.ui.activity.setting

import android.view.View
import com.afollestad.materialdialogs.MaterialDialog
import com.lancer.passwordhelper.R
import com.lancer.passwordhelper.base.BaseActivity
import com.lancer.passwordhelper.databinding.ActivityAccountManagementBinding
import com.lancer.passwordhelper.ui.activity.login.LoginActivity
import com.lancer.passwordhelper.ui.fragment.dialog.EditPasswordFragment

class AccountManagementActivity : BaseActivity<ActivityAccountManagementBinding>(),
    View.OnClickListener {
    init {
        baseTag = AccountManagementActivity::class.java.simpleName
    }

    override fun initView() {
        binding.pwdLl.setOnClickListener(this)
        binding.clearLl.setOnClickListener(this)
        binding.outLl.setOnClickListener(this)
    }

    override fun initData() {
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.pwd_ll -> {
                val editPasswordFragment=EditPasswordFragment()
                editPasswordFragment.show(supportFragmentManager,"editPasswordFragment")
            }
            R.id.clear_ll -> {
                MaterialDialog(this).show {
                    title(null, "确定要删除所有数据吗?")
                    positiveButton(R.string.confirm) { }
                    negativeButton(R.string.cancel) { }
                }
            }
            R.id.out_ll -> {
                start(LoginActivity::class.java)
                finish()
            }
        }
    }

    override fun initLayout(): Int = R.layout.activity_account_management


}