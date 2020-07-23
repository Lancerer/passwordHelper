package com.lancer.passwordhelper.ui.fragment.home

import android.content.Intent
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.lancer.passwordhelper.InjectorUtil
import com.lancer.passwordhelper.base.BaseFragment
import com.lancer.passwordhelper.R
import com.lancer.passwordhelper.bean.Card
import com.lancer.passwordhelper.databinding.FragmentHomeBinding
import com.lancer.passwordhelper.extension.showToast
import com.lancer.passwordhelper.model.database.DaoManager
import com.lancer.passwordhelper.ui.activity.input.InputActivity
import com.lancer.passwordhelper.ui.activity.setting.InOutActivity

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    init {
        name = HomeFragment::class.java.simpleName
    }

    private lateinit var mAdapter: HomeAdapter

    private val viewModel: HomeViewModel by lazy {
        ViewModelProvider(
            this,
            InjectorUtil.getHomeViewModelFactory()
        ).get(HomeViewModel::class.java)
    }

    override fun initView() {
        binding.toolbar.title = "主页"
        binding.homeAddFloat.setOnClickListener {
            startActivity(Intent(activity, InputActivity::class.java))
        }

        binding.homeRecycler.layoutManager = LinearLayoutManager(activity)
        mAdapter = HomeAdapter()
        binding.homeRecycler.adapter = mAdapter
    }

    override fun initData() {
        viewModel.getCardList()
        viewModel.dataList.observe(this, Observer {
            mAdapter.setNewInstance(it as MutableList<Card>)
        })

        mAdapter.setOnItemClickListener { adapter, view, position ->
            //TODO
        }
    }

    override fun initLayout(): Int = R.layout.fragment_home

}