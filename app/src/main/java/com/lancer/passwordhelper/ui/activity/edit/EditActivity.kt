package com.lancer.passwordhelper.ui.activity.edit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.afollestad.materialdialogs.MaterialDialog
import com.lancer.passwordhelper.InjectorUtil
import com.lancer.passwordhelper.R
import com.lancer.passwordhelper.base.BaseActivity
import com.lancer.passwordhelper.bean.Card
import com.lancer.passwordhelper.databinding.ActivityEditBinding
import com.lancer.passwordhelper.extension.showToast
import com.lancer.passwordhelper.ui.activity.input.InputActivity
import com.lancer.passwordhelper.utils.CommonUtils

class EditActivity : BaseActivity<ActivityEditBinding>(), View.OnClickListener {
    init {
        baseTag = EditActivity::class.java.simpleName
    }

    private var card: Card? = null

    private val viewModel by lazy {
        ViewModelProvider(
            this,
            InjectorUtil.getEditViewModelFactory()
        ).get(EditViewModel::class.java)
    }

    //TODO 传入id实现数据库更新方法 update或者save
    override fun initView() {
        binding.editEditIv.setOnClickListener(this)
        binding.editDeleteIv.setOnClickListener(this)
        binding.editCopyAccountTv.setOnClickListener(this)
        binding.editCopyPasswordTv.setOnClickListener(this)
        binding.editCopyLinkTv.setOnClickListener(this)
    }

    override fun initData() {
        if (intent.getSerializableExtra("list") != null) {
            card = intent.getSerializableExtra("list") as Card
            binding.viewModel = card
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.edit_edit_iv -> {
                //TODO 使用startActivityForResult方法，修改后的数据也要更新到edit界面
                if (card != null) {
                    val intent = Intent(this@EditActivity, InputActivity::class.java)
                    intent.putExtra("list", card)
                    startActivity(intent)
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
        }
    }

    private fun deleteCard() {
        MaterialDialog(this).show {
            title(null, "你是否要删除${card?.name}信息")
            positiveButton(null, "确定") {
                viewModel.deleteCard(card!!)
                finish()
            }
            negativeButton(null, "取消")
        }
    }

    override fun initLayout(): Int = R.layout.activity_edit


}