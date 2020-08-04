package com.lancer.passwordhelper.ui.activity.setting.folder

import android.text.InputType
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.input.input
import com.lancer.passwordhelper.InjectorUtil
import com.lancer.passwordhelper.base.BaseActivity
import com.lancer.passwordhelper.R
import com.lancer.passwordhelper.bean.Category
import com.lancer.passwordhelper.databinding.ActivityFolderBinding
import com.lancer.passwordhelper.extension.showToast

class FolderActivity : BaseActivity<ActivityFolderBinding>() {
    init {
        baseTag = FolderActivity::class.java.simpleName
    }

    private val viewModel: FolderViewModel by lazy {
        ViewModelProvider(
            this,
            InjectorUtil.getFolderViewModelFactory()
        ).get(FolderViewModel::class.java)
    }

    private lateinit var mAdapter: FolderAdapter
    override fun initView() {
        binding.folderAddFloat.setOnClickListener {
            //点击添加类别
            addCategory()
        }
        mAdapter = FolderAdapter()
        binding.folderRecycler.adapter = mAdapter
        binding.folderRecycler.layoutManager =
            LinearLayoutManager(this)
    }


    override fun initData() {
        viewModel.mExceptionLiveData.observe(this, Observer {
            it.showToast()
        })
        viewModel.dataList.observe(this, Observer {
            mAdapter.setList(it)
        })
        viewModel.getCategory()
    }


    private fun addCategory() {
        //MaterialDialog input dialog
        MaterialDialog(this).show {
            title(R.string.create_category)
            input(
                hint = "输入你想创建的类别名称",
                inputType = InputType.TYPE_CLASS_TEXT
            ) { materialDialog, charSequence ->
                val category = Category()
                category.categoryName = charSequence.toString()
                viewModel.saveCategory(category)
                viewModel.getCategory()
            }
            positiveButton(R.string.confirm)
            negativeButton(R.string.cancel)
        }

    }


    override fun initLayout(): Int = R.layout.activity_folder

}