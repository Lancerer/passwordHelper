package com.lancer.passwordhelper.ui.fragment.category

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lancer.eyelast.base.BaseFragment
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