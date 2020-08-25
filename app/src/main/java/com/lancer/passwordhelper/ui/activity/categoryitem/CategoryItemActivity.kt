package com.lancer.passwordhelper.ui.activity.categoryitem

import android.graphics.Color
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.lancer.passwordhelper.InjectorUtil
import com.lancer.passwordhelper.R
import com.lancer.passwordhelper.base.BaseActivity
import com.lancer.passwordhelper.databinding.ActivityCategoryItemBinding
import com.lancer.passwordhelper.model.bean.Card

@Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class CategoryItemActivity : BaseActivity<ActivityCategoryItemBinding>() {


    private val viewModel by lazy {
        ViewModelProvider(this, InjectorUtil.getCategoryItemViewModelFactory())
            .get(CategoryItemViewModel::class.java)
    }

    private lateinit var mAdapter: CategoryItemAdapter

    private var categoryName: String? = null

    private var cardList = ArrayList<Card>()


    override fun initView() {
        binding.toolbar.setTitleTextColor(Color.BLACK)
        mAdapter = CategoryItemAdapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = mAdapter
    }

    override fun initData() {
        if (intent != null) {
            if (intent.getStringExtra("categoryName").isNotEmpty()) {
                categoryName = intent.getStringExtra("categoryName")
                binding.toolbar.title = categoryName
            }
        }

        categoryName?.let {
            viewModel.getCardListByCategory(it)
        }
        viewModel.dataList.observe(this, Observer {
            cardList = it as ArrayList<Card>
            mAdapter.setList(cardList)
        })
    }

    override fun initLayout() = R.layout.activity_category_item

}