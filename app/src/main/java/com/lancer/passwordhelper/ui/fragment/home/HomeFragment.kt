package com.lancer.passwordhelper.ui.fragment.home

import android.content.Intent
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.lancer.passwordhelper.Constant.PUT_EXTRA_NAME
import com.lancer.passwordhelper.InjectorUtil
import com.lancer.passwordhelper.base.BaseFragment
import com.lancer.passwordhelper.R
import com.lancer.passwordhelper.model.bean.Card
import com.lancer.passwordhelper.databinding.FragmentHomeBinding
import com.lancer.passwordhelper.ui.activity.edit.EditDeleteActivity
import com.lancer.passwordhelper.ui.activity.input.InputActivity

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    init {
        name = HomeFragment::class.java.simpleName
    }

    private lateinit var mAdapter: HomeAdapter

    private var dataList = ArrayList<Card>()

    private val viewModel: HomeViewModel by lazy {
        ViewModelProvider(
            this,
            InjectorUtil.getHomeViewModelFactory()
        ).get(HomeViewModel::class.java)
    }

    //TODO 删除数据后界面不更新 和BaseQuickAdapter的方法有关系
    override fun initView() {
        binding.toolbar.title = "主页"
        binding.homeAddFloat.setOnClickListener {
            startActivity(Intent(activity, InputActivity::class.java))
        }

        binding.homeRecycler.layoutManager = LinearLayoutManager(activity)
        mAdapter = HomeAdapter()
        binding.homeRecycler.adapter = mAdapter

        mAdapter.setOnItemClickListener { adapter, view, position ->
            //TODO
            val intent = Intent(activity, EditDeleteActivity::class.java)
            intent.putExtra(PUT_EXTRA_NAME, dataList[position])
            startActivity(intent)
        }
    }

    override fun initData() {
        viewModel.getCardList()
        viewModel.dataList.observe(this, Observer {
            if (dataList.isNotEmpty()) dataList.clear()
            for (card in it) {
                dataList.add(card)
            }
            //TODO setList 和setNewInstance的区别
            mAdapter.setList(dataList)
        })

    }

    override fun initLayout(): Int = R.layout.fragment_home

}