package com.lancer.passwordhelper.ui.fragment.home

import android.content.Intent
import android.util.Log
import com.lancer.passwordhelper.base.BaseFragment
import com.lancer.passwordhelper.R
import com.lancer.passwordhelper.databinding.FragmentHomeBinding
import com.lancer.passwordhelper.extension.showToast
import com.lancer.passwordhelper.model.database.DaoManager
import com.lancer.passwordhelper.ui.activity.input.InputActivity
import com.lancer.passwordhelper.ui.activity.setting.InOutActivity

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    companion object {
        fun newInstance() =
            HomeFragment()
    }


    override fun initView() {
        binding.toolbar.title = "主页"
        binding.homeAddFloat.setOnClickListener {
            startActivity(Intent(activity, InputActivity::class.java))
        }
    }

    override fun initData() {
        val queryAllCard = DaoManager.getInstance().queryAllCard()
        if (queryAllCard.isNotEmpty()) {
            for (card in queryAllCard) {
             Log.d("yayaya", "name==${ card.name}  account==${card.account}  password==${card.password}")
            }
        } else {
            "null".showToast()
        }
    }

    override fun initLayout(): Int = R.layout.fragment_home

}