package com.lancer.passwordhelper.ui.fragment.card

import com.lancer.passwordhelper.base.BaseFragment
import com.lancer.passwordhelper.R
import com.lancer.passwordhelper.databinding.FragmentCardBinding
import com.lancer.passwordhelper.ui.fragment.category.CategoryFragment

class CardFragment : BaseFragment<FragmentCardBinding>() {    init {
    name = CardFragment::class.java.simpleName
}

    companion object {
        fun newInstance() = CardFragment()
    }

    override fun initView() {
        binding.cardStatusView.showEmpty()
    }

    override fun initData() {

    }

    override fun initLayout(): Int = R.layout.fragment_card


}