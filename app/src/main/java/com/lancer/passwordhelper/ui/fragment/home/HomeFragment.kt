package com.lancer.passwordhelper.ui.fragment.home

import com.lancer.passwordhelper.base.BaseFragment
import com.lancer.passwordhelper.R
import com.lancer.passwordhelper.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    companion object {
        fun newInstance() =
            HomeFragment()
    }


    override fun initView() {
        binding.homeStatusView.showEmpty()
    }

    override fun initData() {
    }

    override fun initLayout(): Int = R.layout.fragment_home

}