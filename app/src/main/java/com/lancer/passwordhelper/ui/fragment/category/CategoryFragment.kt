package com.lancer.passwordhelper.ui.fragment.category

import android.content.Intent
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.lancer.passwordhelper.InjectorUtil
import com.lancer.passwordhelper.R
import com.lancer.passwordhelper.base.BaseFragment
import com.lancer.passwordhelper.databinding.FragmentCategoryBinding
import com.lancer.passwordhelper.model.bean.Category
import com.lancer.passwordhelper.ui.activity.categoryitem.CategoryItemActivity


class CategoryFragment : BaseFragment<FragmentCategoryBinding>() {
    init {
        name = CategoryFragment::class.java.simpleName
    }

    private val viewModel: CategoryViewModel by lazy {
        ViewModelProvider(
            this,
            InjectorUtil.getCategoryViewModelFactory()
        ).get(CategoryViewModel::class.java)
    }

    private lateinit var mAdapter: CategoryAdapter

    private var dataList = ArrayList<Category>()

    override fun onResume() {
        super.onResume()
        initView()
        initData()
    }

    override fun initView() {
        binding.toolbar.title = "分类"
        mAdapter = CategoryAdapter()
        binding.categoryRecycler.layoutManager = GridLayoutManager(context, 3)
        binding.categoryRecycler.adapter = mAdapter

        mAdapter.setOnItemClickListener { adapter, view, position ->
            val intent = Intent(activity, CategoryItemActivity::class.java)
            intent.putExtra("categoryName", dataList[position].categoryName)
            startActivity(intent)
        }
    }

    override fun initData() {
        viewModel.requestCategoryList()
        viewModel.dataList.observe(this, Observer {
            dataList = it as ArrayList<Category>
            mAdapter.setList(dataList)
        })
    }

    override fun initLayout(): Int = R.layout.fragment_category

    override fun onDestroy() {
        super.onDestroy()
        binding.unbind()
    }
}