package com.lancer.passwordhelper.ui.fragment.news

import android.app.Activity
import android.content.Intent
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.lancer.passwordhelper.InjectorUtil
import com.lancer.passwordhelper.base.BaseFragment
import com.lancer.passwordhelper.R
import com.lancer.passwordhelper.databinding.FragmentCardBinding
import com.lancer.passwordhelper.ui.activity.video.VideoActivity
import com.scwang.smart.refresh.layout.constant.RefreshState

//替换为开眼每日新闻界面
class CardFragment : BaseFragment<FragmentCardBinding>() {
    init {
        name = CardFragment::class.java.simpleName
    }

    private val viewModel by lazy {
        ViewModelProvider(this, InjectorUtil.getCardViewModelFactory())
            .get(CardViewModel::class.java)
    }

    private lateinit var adapter: CardAdapter

    private var nextPageUrl: String? = null


    companion object {
        const val TAG = "NewDetailActivity"

        const val EXTRA_VIDEO_INFO = "videoInfo"
        const val EXTRA_VIDEO_ID = "videoId"
        fun newInstance() = CardFragment()
        fun start(context: Activity, videoId: Long) {
            val starter = Intent(context, VideoActivity::class.java)
            starter.putExtra(EXTRA_VIDEO_ID, videoId)
            context.startActivity(starter)
//            context.overridePendingTransition(R.anim.anl_push_bottom_in, R.anim.anl_push_up_out)
        }
    }

    override fun initView() {
        binding.cardStatusView.showLoading()
        binding.recyclerView.layoutManager = LinearLayoutManager(activity)
        binding.recyclerView.adapter = adapter
        //下拉刷新
        binding.refreshLayout.setOnRefreshListener {
            viewModel.requestDaily()
        }
        //上拉加载更多
        binding.refreshLayout.setOnLoadMoreListener {
            nextPageUrl?.let {
                viewModel.requestDaily(it)
            }
        }

    }

    override fun initData() {
        viewModel.requestDaily()
        viewModel.dataList.observe(this, Observer { response ->
            nextPageUrl = response.nextPageUrl
            binding.cardStatusView.showContent()
            if (response.itemList.isEmpty()) {
                binding.cardStatusView.showEmpty()
                return@Observer
            }

            when (binding.refreshLayout.state) {
                RefreshState.None, RefreshState.Refreshing -> {
                    adapter.data.clear()
                    adapter.addData(response.itemList)
                }
                RefreshState.Loading -> {
                    adapter.addData(response.itemList)
                }
                else -> {
                }
            }

            if (response.nextPageUrl.isNullOrBlank()) {
                //没有数据了
                binding.refreshLayout.finishLoadMoreWithNoMoreData()
            } else {
                binding.refreshLayout.closeHeaderOrFooter()
            }
        })
        viewModel.mExceptionLiveData.observe(this, Observer {
            Log.d("TAG", it)
        })
    }


    override fun initLayout(): Int = R.layout.fragment_card


}