package com.lancer.passwordhelper.ui.activity.setting.folder

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.lancer.passwordhelper.R
import com.lancer.passwordhelper.databinding.ItemFolderBinding
import com.lancer.passwordhelper.model.bean.Category

/**
 * @author lancer
 * @des
 * @Date 2020/8/4 17:01
 */
class FolderAdapter(layoutId: Int = R.layout.item_folder) :
    BaseQuickAdapter<Category, BaseDataBindingHolder<ItemFolderBinding>>(layoutId) {
    override fun convert(holder: BaseDataBindingHolder<ItemFolderBinding>, item: Category) {
        val dataBinding = holder.dataBinding
        if (dataBinding != null) {
            dataBinding.viewModel = item
            dataBinding.executePendingBindings()
        }
    }

}