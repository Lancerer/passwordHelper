package com.lancer.passwordhelper.ui.fragment.category

import androidx.databinding.DataBindingUtil
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.lancer.passwordhelper.R
import com.lancer.passwordhelper.databinding.ItemCategoryBinding
import com.lancer.passwordhelper.model.bean.Category

/**
 * @author lancer
 * @des
 * @Date 2020/7/21 9:59
 */
class CategoryAdapter(layoutId: Int = R.layout.item_category) :
    BaseQuickAdapter<Category, BaseDataBindingHolder<ItemCategoryBinding>>(layoutId) {

    private val colors = arrayListOf(
        R.drawable.shape_category_fragment_item1,
        R.drawable.shape_category_fragment_item2,
        R.drawable.shape_category_fragment_item3,
        R.drawable.shape_category_fragment_item4,
        R.drawable.shape_category_fragment_item5
    )

    override fun onItemViewHolderCreated(
        viewHolder: BaseDataBindingHolder<ItemCategoryBinding>,
        viewType: Int
    ) {
        DataBindingUtil.bind<ItemCategoryBinding>(viewHolder.itemView)
    }

    override fun convert(holder: BaseDataBindingHolder<ItemCategoryBinding>, item: Category) {
        val dataBinding = holder.dataBinding
        if (dataBinding != null) {
            dataBinding.viewModel = item
            dataBinding.executePendingBindings()
            dataBinding.itemCategoryNameTv.setBackgroundResource(colors[holder.layoutPosition%colors.size])
        }
    }


}