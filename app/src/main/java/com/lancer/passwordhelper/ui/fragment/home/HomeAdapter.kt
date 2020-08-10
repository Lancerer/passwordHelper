package com.lancer.passwordhelper.ui.fragment.home

import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.lancer.passwordhelper.R
import com.lancer.passwordhelper.model.bean.Card
import com.lancer.passwordhelper.utils.PinYinUtils

/**
 * @author lancer
 * @des
 * @Date 2020/7/23 13:42
 */
class HomeAdapter(layoutId: Int = R.layout.item_home) :
    BaseQuickAdapter<Card, BaseViewHolder>(layoutId) {
    override fun convert(holder: BaseViewHolder, item: Card) {

        PinYinUtils.setTextBg(holder.getView<TextView>(R.id.item_home_iv),item.name)
        holder.setText(R.id.item_home_account_tv, item.account)
        holder.setText(R.id.item_home_name_tv, item.name)
    }
}