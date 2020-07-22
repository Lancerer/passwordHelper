package com.lancer.passwordhelper.ui.activity.input

import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.afollestad.materialdialogs.MaterialDialog
import com.lancer.passwordhelper.InjectorUtil
import com.lancer.passwordhelper.R
import com.lancer.passwordhelper.base.BaseActivity
import com.lancer.passwordhelper.bean.Card
import com.lancer.passwordhelper.databinding.ActivityInputBinding
import com.lancer.passwordhelper.extension.showToast

class InputActivity : BaseActivity<ActivityInputBinding>(), View.OnClickListener {

    private var name: String? = null
    private var account: String? = null
    private var password: String? = null
    private var link: String? = null
    private var remark: String? = null

    private var hasSee: Boolean = false

    private val viewModel by lazy {
        ViewModelProvider(
            this,
            InjectorUtil.getInputViewModelFactory()
        ).get(InputViewModel::class.java)
    }

    override fun initView() {
        binding.inputBackIv.setOnClickListener(this)
        binding.inputRandomIv.setOnClickListener(this)
        binding.inputLikeIv.setOnClickListener(this)
        binding.inputSaveIv.setOnClickListener(this)
        binding.inputEnableIv.setOnClickListener(this)

    }

    override fun initData() {


    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.input_back_iv -> {
                backLogic()
            }
            R.id.input_random_iv -> {
                //todo
            }
            R.id.input_like_iv -> {

            }
            R.id.input_save_iv -> {
                saveCard()
            }
            R.id.input_enable_iv -> {
                isEnablePwd()
            }
        }
    }

    private fun backLogic() {
        name = binding.inputNameEt.text?.trim().toString()
        account = binding.inputAccountEt.text?.trim().toString()
        password = binding.inputPasswordEt.text?.trim().toString()
        link = binding.inputLinkEt.text?.trim().toString()
        remark = binding.inputRemarkEt.text?.trim().toString()

        if (!name.isNullOrEmpty() || !account.isNullOrEmpty() || !password.isNullOrEmpty() || !link.isNullOrEmpty() || !remark.isNullOrEmpty()) {
            MaterialDialog(this).show {
                title = "输入框中还有未保存数据，是否继续推出？"
                positiveButton { finish() }
                negativeButton { }
            }
        } else {
            finish()
        }
    }

    private fun saveCard() {
        name = binding.inputNameEt.text?.trim().toString()
        account = binding.inputAccountEt.text?.trim().toString()
        password = binding.inputPasswordEt.text?.trim().toString()
        link = binding.inputLinkEt.text?.trim().toString()
        remark = binding.inputRemarkEt.text?.trim().toString()


        if (account.isNullOrEmpty() || password.isNullOrEmpty()) {
            "账号密码不能为空".showToast()
            return
        }
        //名称为空的情况
        if (name.isNullOrEmpty()) {
            val card = Card()
            card.name = account
            card.account = account
            card.password = password
            card.webUrl = link
            card.isCollect = 0
            viewModel.saveCard(card)
            "保存成功".showToast()
            finish()
        } else {
            val card = Card()
            card.name = name
            card.account = account
            card.password = password
            card.webUrl = link
            card.isCollect = 0
            viewModel.saveCard(card)
            "保存成功".showToast()
            finish()
        }

    }

    private fun isEnablePwd() {
        if (!hasSee) {
            hasSee = true
            binding.inputEnableIv.setImageResource(R.drawable.ic_password_disable)
            binding.inputPasswordEt.transformationMethod =
                HideReturnsTransformationMethod.getInstance()
        } else {
            binding.inputPasswordEt.transformationMethod =
                PasswordTransformationMethod.getInstance()
            binding.inputEnableIv.setImageResource(R.drawable.ic_password_enable)
            hasSee = false
        }
    }

    override fun initLayout(): Int = R.layout.activity_input


}