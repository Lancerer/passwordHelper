package com.lancer.passwordhelper.ui.fragment.category

import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.lancer.passwordhelper.InjectorUtil
import com.lancer.passwordhelper.base.BaseFragment
import com.lancer.passwordhelper.R
import com.lancer.passwordhelper.databinding.FragmentCategoryBinding


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


    override fun initView() {
        binding.toolbar.title = "分类"
    }

    override fun initData() {
        mAdapter = CategoryAdapter()
        binding.categoryRecycler.adapter = mAdapter
        binding.categoryRecycler.layoutManager = GridLayoutManager(context, 3)
        //TODO 添加ItemDecoration

//        viewModel.requestCategoryList()
//        viewModel.dataList.observe(this, Observer {
//            mAdapter.setNewInstance(it as MutableList<Category>)
//        })
    }

    override fun initLayout(): Int = R.layout.fragment_category

}