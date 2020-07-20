package com.lancer.passwordhelper.ui.fragment.category

import com.lancer.passwordhelper.base.BaseFragment
import com.lancer.passwordhelper.R
import com.lancer.passwordhelper.databinding.FragmentCategoryBinding

class CategoryFragment : BaseFragment<FragmentCategoryBinding>() {

    companion object {
        fun newInstance() = CategoryFragment()
    }

    override fun initView() {
        binding.categoryStatusView.showEmpty()
    }

    override fun initData() {
    }

    override fun initLayout(): Int = R.layout.fragment_category

}