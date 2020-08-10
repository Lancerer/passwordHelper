package com.lancer.passwordhelper.ui.activity.input

import android.app.Activity
import android.content.Intent
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.afollestad.materialdialogs.MaterialDialog
import com.lancer.passwordhelper.Constant
import com.lancer.passwordhelper.InjectorUtil
import com.lancer.passwordhelper.R
import com.lancer.passwordhelper.base.BaseActivity
import com.lancer.passwordhelper.model.bean.Card
import com.lancer.passwordhelper.databinding.ActivityInputBinding
import com.lancer.passwordhelper.extension.showToast

class InputActivity : BaseActivity<ActivityInputBinding>(), View.OnClickListener {

    init {
        baseTag = InputActivity::class.java.simpleName
    }

    private var name: String? = null
    private var account: String? = null
    private var password: String? = null
    private var link: String? = null
    private var remark: String? = null
    private var folder: String? = null

    private var hasSee: Boolean = false

    private var currentCard: Card? = Card()

    private lateinit var mSpinnerAdapter: ArrayAdapter<String>

    private val dataList = ArrayList<String>().apply {
        add("默认")
        add("娱乐")
        add("办公")
        add("教育")
    }

    fun isInit() = ::mSpinnerAdapter.isInitialized

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

        mSpinnerAdapter =
            ArrayAdapter(this@InputActivity, android.R.layout.simple_list_item_1, dataList)
        mSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.inputSpinner.adapter = mSpinnerAdapter

        binding.inputSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                folder = mSpinnerAdapter.getItem(position)
            }

        }
    }

    override fun initData() {
        //接收从EditActivity传递过来的数据
        if (intent.getSerializableExtra(Constant.PUT_EXTRA_NAME) != null) {
            currentCard = intent.getSerializableExtra(Constant.PUT_EXTRA_NAME) as Card
            binding.viewModel = currentCard
            //设置选中项
            binding.inputSpinner.setSelection(mSpinnerAdapter.getPosition(currentCard?.folder))
        }

        viewModel.getCategoryList()

        viewModel.spinnerDataList.observe(this, Observer {
            dataList.clear()
            for (category in it) {
                dataList.add(category.categoryName)
            }
            mSpinnerAdapter.notifyDataSetChanged()
        })
    }

    override fun onClick(v: View?) {
        name = binding.inputNameEt.text?.trim().toString()
        account = binding.inputAccountEt.text?.trim().toString()
        password = binding.inputPasswordEt.text?.trim().toString()
        link = binding.inputLinkEt.text?.trim().toString()
        remark = binding.inputRemarkEt.text?.trim().toString()

        when (v?.id) {
            R.id.input_back_iv -> {
                backLogic()
            }
            R.id.input_random_iv -> {
                //TODO
            }
            R.id.input_like_iv -> {
                //TODO
            }
            R.id.input_save_iv -> {
                saveCard()
            }
            R.id.input_enable_iv -> {
                isEnablePwd()
            }
        }
    }

    /**
     * 返回逻辑
     */
    private fun backLogic() {
        if (!name.isNullOrEmpty() || !account.isNullOrEmpty() || !password.isNullOrEmpty() || !link.isNullOrEmpty() || !remark.isNullOrEmpty()) {
            MaterialDialog(this).show {
                title = getString(R.string.input_edit_text_has_data)
                positiveButton(R.string.confirm) { finish() }
                negativeButton(R.string.cancel)
            }
        } else {
            finish()
        }
    }

    /**
     * 保存密码
     */
    private fun saveCard() {
        if (account.isNullOrEmpty() || password.isNullOrEmpty()) {
            getString(R.string.register_null_hint).showToast()
            return
        }

        currentCard?.let { card ->
            //名称为空的情况
            card.account = account
            card.password = password
            card.webUrl = link
            card.remark = remark
            card.isCollect = 0
            if (name.isNullOrEmpty()) {
                card.name = account
            } else {
                card.name = name
            }
            if (folder.isNullOrEmpty()) {
                card.folder = "默认"
            } else {
                card.folder = folder
            }
            viewModel.saveCard(card)
            getString(R.string.input_save_success).showToast()
            val intent = Intent()
            intent.putExtra("card", card)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }

    }

    /**
     * 是否隐藏密码
     */
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