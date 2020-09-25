package com.lancer.passwordhelper.ui.activity.edit

import android.content.Intent
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.afollestad.materialdialogs.MaterialDialog
import com.lancer.passwordhelper.Constant
import com.lancer.passwordhelper.InjectorUtil
import com.lancer.passwordhelper.R
import com.lancer.passwordhelper.base.BaseActivity
import com.lancer.passwordhelper.databinding.ActivityEditBinding
import com.lancer.passwordhelper.extension.showToast
import com.lancer.passwordhelper.model.bean.Card
import com.lancer.passwordhelper.ui.activity.input.InputActivity
import com.lancer.passwordhelper.utils.CommonUtils

class EditDeleteActivity : BaseActivity<ActivityEditBinding>(), View.OnClickListener {
    init {
        baseTag = EditDeleteActivity::class.java.simpleName
    }

    private var currentCard: Card? = Card()
    private var hasSee: Boolean = false

    private val viewModel by lazy {
        ViewModelProvider(
            this,
            InjectorUtil.getEditViewModelFactory()
        ).get(EditViewModel::class.java)
    }

    override fun initView() {
        binding.editEditIv.setOnClickListener(this)
        binding.editDeleteIv.setOnClickListener(this)
        binding.editCopyAccountTv.setOnClickListener(this)
        binding.editCopyPasswordTv.setOnClickListener(this)
        binding.editCopyLinkTv.setOnClickListener(this)
        binding.editEnableIv.setOnClickListener(this)
    }

    override fun initData() {
        if (intent.getSerializableExtra(Constant.PUT_EXTRA_NAME) != null) {
            currentCard = intent.getSerializableExtra(Constant.PUT_EXTRA_NAME) as Card
            binding.viewModel = currentCard
        }
        viewModel.mExceptionLiveData.observe(this, Observer {
            it.showToast()
        })
    }

    //接收从input中修改过的内容
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data != null && data.extras != null) {
            val card = data.getSerializableExtra("card") as Card
            binding.viewModel = card
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.edit_edit_iv -> {
                if (currentCard != null) {
                    val intent = Intent(this@EditDeleteActivity, InputActivity::class.java)
                    intent.putExtra(Constant.PUT_EXTRA_NAME, currentCard)
                    startActivityForResult(intent, Constant.EDIT_ACTIVITY_REQUEST_CODE)
                }
            }
            R.id.edit_delete_iv -> {
                deleteCard()
            }
            R.id.edit_copy_account_tv -> {
                CommonUtils.setTextToClipboard(binding.editAccountTv.text.toString())
                "复制成功".showToast()
            }
            R.id.edit_copy_password_tv -> {
                CommonUtils.setTextToClipboard(binding.editPasswordTv.text.toString())
                "复制成功".showToast()
            }
            R.id.edit_copy_link_tv -> {
                CommonUtils.setTextToClipboard(binding.editLinkTv.text.toString())
                "复制成功".showToast()
            }
            R.id.edit_enable_iv -> {
                isEnablePwd()
            }
        }
    }

    private fun deleteCard() {
        MaterialDialog(this).show {
            title(null, "你是否要删除${currentCard?.name}信息")
            positiveButton(null, "确定") {
                viewModel.deleteCard(currentCard!!)
                finish()
            }
            negativeButton(null, "取消")
        }
    }

    /**
     * 是否隐藏密码
     */
    private fun isEnablePwd() {
        if (!hasSee) {
            hasSee = true
            binding.editEnableIv.setImageResource(R.drawable.ic_password_disable)
            binding.editPasswordTv.transformationMethod =
                HideReturnsTransformationMethod.getInstance()
        } else {
            binding.editPasswordTv.transformationMethod =
                PasswordTransformationMethod.getInstance()
            binding.editEnableIv.setImageResource(R.drawable.ic_password_enable)
            hasSee = false
        }
    }

    override fun initLayout(): Int = R.layout.activity_edit


}