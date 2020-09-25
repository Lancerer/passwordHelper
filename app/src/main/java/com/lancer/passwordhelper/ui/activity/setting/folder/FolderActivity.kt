package com.lancer.passwordhelper.ui.activity.setting.folder

import android.text.InputType
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.input.input
import com.lancer.passwordhelper.InjectorUtil
import com.lancer.passwordhelper.R
import com.lancer.passwordhelper.base.BaseActivity
import com.lancer.passwordhelper.databinding.ActivityFolderBinding
import com.lancer.passwordhelper.model.bean.Category

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

    private var dataList = ArrayList<Category>()
    private lateinit var mAdapter: FolderAdapter
    override fun initView() {
        binding.folderAddFloat.setOnClickListener {
            //点击添加类别
            addCategory("")
        }
        mAdapter = FolderAdapter()
        binding.folderRecycler.adapter = mAdapter
        binding.folderRecycler.layoutManager =
            LinearLayoutManager(this)

        mAdapter.setOnItemClickListener { adapter, view, position ->
            showLogicDialog(view, position)
        }
    }

    private fun showLogicDialog(view: View, position: Int) {
        MaterialDialog(this).show {
            title(null, "是否删除该分类?")
            positiveButton(R.string.confirm) {
                viewModel.deleteCategory(dataList[position])
                dataList.removeAt(position)
                mAdapter.setList(dataList)
            }
            negativeButton(R.string.cancel)
        }
    }


    override fun initData() {
        viewModel.mExceptionLiveData.observe(this, Observer {
            Log.d("TAG", it.toString())
        })
        viewModel.dataList.observe(this, Observer {
            dataList = it as ArrayList<Category>
            mAdapter.setList(dataList)
        })
        viewModel.getCategory()
    }


    private fun addCategory(categoryName: String) {
        MaterialDialog(this).show {
            title(R.string.create_category)
            input(
                hint = if (categoryName.isEmpty()) "输入你想创建的类别名称" else categoryName,
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