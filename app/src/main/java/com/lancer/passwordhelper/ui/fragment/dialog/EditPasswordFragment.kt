package com.lancer.passwordhelper.ui.fragment.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.lancer.passwordhelper.Constant
import com.lancer.passwordhelper.R
import com.lancer.passwordhelper.databinding.DialogEditPasswordBinding
import com.lancer.passwordhelper.extension.showToast
import com.lancer.passwordhelper.utils.AppPrefsUtils

class EditPasswordFragment : DialogFragment(), View.OnClickListener {

    private lateinit var binding: DialogEditPasswordBinding

    private var oldPd: String? = null
    private var newPd: String? = null
    private var confirmPd: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.dialog_edit_password, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
    }

    private fun initData() {
        binding.confirmTv.setOnClickListener(this)
        binding.cancelTv.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        oldPd = binding.oldPasswordEt.text.toString()
        newPd = binding.newPasswordEt.text.toString()
        confirmPd = binding.confirmPasswordEt.text.toString()
        when (v?.id) {
            R.id.cancel_tv -> {
                this.dismiss()
            }
            R.id.confirm_tv -> {
                if (judge()) {
                    AppPrefsUtils.putString(Constant.CURRENT_PASSWORD, newPd!!)
                    AppPrefsUtils.putBoolean(Constant.HAS_EDIT_PASSWORD, true)
                    "修改密码成功".showToast()
                    this.dismiss()
                }
            }

        }
    }

    private fun judge(): Boolean {
        if (oldPd.isNullOrBlank() || newPd.isNullOrBlank() || confirmPd.isNullOrBlank()) {
            "密码不能为空".showToast()
            return false
        }
        if (!oldPd.equals(AppPrefsUtils.getString(Constant.CURRENT_PASSWORD))) {
            "旧密码输入错误".showToast()
            return false
        } else if (!newPd.equals(confirmPd)) {
            "两次密码输入不一致".showToast()
            return false
        }
        return true
    }


}