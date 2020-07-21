package com.lancer.passwordhelper.ui.fragment.category

import android.view.View
import androidx.databinding.DataBindingUtil
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.lancer.passwordhelper.R
import com.lancer.passwordhelper.bean.Category
import com.lancer.passwordhelper.databinding.ItemCategoryBinding

/**
 * @author lancer
 * @des
 * @Date 2020/7/21 9:59
 */
class CategoryAdapter(layoutId: Int = R.layout.item_category) :
    BaseQuickAdapter<Category, BaseDataBindingHolder<ItemCategoryBinding>>(layoutId) {
    override fun onItemViewHolderCreated(viewHolder: BaseDataBindingHolder<ItemCategoryBinding>, viewType: Int) {
        DataBindingUtil.bind<ItemCategoryBinding>(viewHolder.itemView)
    }

    override fun convert(holder: BaseDataBindingHolder<ItemCategoryBinding>, item: Category) {
        val dataBinding = holder.dataBinding
        if (dataBinding != null) {
            dataBinding.viewModel = item
            dataBinding.executePendingBindings()
        }
    }


}