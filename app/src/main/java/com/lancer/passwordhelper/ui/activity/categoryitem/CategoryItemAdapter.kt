package com.lancer.passwordhelper.ui.activity.categoryitem

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.lancer.passwordhelper.R
import com.lancer.passwordhelper.databinding.ItemCategoryItemBinding
import com.lancer.passwordhelper.model.bean.Card

/**
 * @author lancer
 * @des
 * @Date 2020/8/11 9:43
 */
class CategoryItemAdapter(layoutId: Int = R.layout.item_category_item) : BaseQuickAdapter<Card,
        BaseDataBindingHolder<ItemCategoryItemBinding>>(layoutId) {
    override fun convert(holder: BaseDataBindingHolder<ItemCategoryItemBinding>, item: Card) {
        val dataBinding = holder.dataBinding
        if (dataBinding != null) {
            dataBinding.viewModel = item
            dataBinding.executePendingBindings()
        }
    }
}