package com.lancer.passwordhelper.ui.fragment.news

import android.app.Activity
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseProviderMultiAdapter
import com.chad.library.adapter.base.provider.BaseItemProvider
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.lancer.passwordhelper.ActionUrl.process
import com.lancer.passwordhelper.Const
import com.lancer.passwordhelper.R
import com.lancer.passwordhelper.extension.RecyclerViewHelp
import com.lancer.passwordhelper.extension.load
import com.lancer.passwordhelper.model.bean.Daily
import com.lancer.passwordhelper.model.bean.VideoInfo
import com.lancer.passwordhelper.ui.activity.video.VideoActivity.Companion.start
import jp.wasabeef.glide.transformations.RoundedCornersTransformation

class CardAdapter(fragment: CardFragment) : BaseProviderMultiAdapter<Daily.Item>() {
    init {
        addItemProvider(Banner3ViewHolder(fragment))
        addItemProvider(FollowCardViewHolder(fragment))
        addItemProvider(InformationCardFollowCardViewHolder(fragment))
        addItemProvider(TextCardViewFooter2ViewHolder(fragment))
        addItemProvider(TextCardViewFooter3ViewHolder(fragment))
        addItemProvider(TextCardViewHeader5ViewHolder(fragment))
        addItemProvider(TextCardViewHeader7ViewHolder(fragment))
        addItemProvider(TextCardViewHeader8ViewHolder(fragment))
        addItemProvider(EmptyViewHolder())

    }

    override fun getItemType(data: List<Daily.Item>, position: Int): Int {
        return RecyclerViewHelp.getItemViewType(data[position])

    }

    private inner class EmptyViewHolder:BaseItemProvider<Daily.Item>(){
        override val itemViewType: Int
            get() = Const.ItemViewType.UNKNOWN
        override val layoutId: Int
            get() = R.layout.layout_deafult_empty_view

        override fun convert(helper: BaseViewHolder, item: Daily.Item) {

        }

    }
    private inner class Banner3ViewHolder(val fragment: CardFragment) :
        BaseItemProvider<Daily.Item>() {
        override val itemViewType: Int
            get() = Const.ItemViewType.BANNER3
        override val layoutId: Int
            get() = R.layout.item_banner_three_type

        override fun convert(helper: BaseViewHolder, item: Daily.Item) {
            val ivPicture: ImageView =
                helper.getView(R.id.ivPicture)
            val ivAvatar: ImageView =
                helper.getView(R.id.ivAvatar)
            ivPicture.load(item.data.image, 4f, RoundedCornersTransformation.CornerType.ALL)
            ivAvatar.load(item.data.header.icon, 4f, RoundedCornersTransformation.CornerType.ALL)
            helper.setText(R.id.tvTitle, item.data.header.title)
            helper.setText(R.id.tvDescription, item.data.header.description)
            if (item.data.label != null) {
                if (TextUtils.isEmpty(item.data.label.text)) {
                    helper.setVisible(R.id.tvLabel, true)
                } else {
                    helper.setGone(R.id.tvLabel, true)
                }
                helper.setText(R.id.tvLabel, item.data.label.text)
            } else {
                helper.setText(R.id.tvLabel, "")
            }

            helper.itemView.setOnClickListener {
                process(
                    fragment,
                    item.data.actionUrl,
                    item.data.header.title
                )
            }
        }

    }

    private inner class FollowCardViewHolder(val CardFragment: CardFragment) :
        BaseItemProvider<Daily.Item>() {
        override val itemViewType: Int
            get() = Const.ItemViewType.FOLLOW_CARD
        override val layoutId: Int
            get() = R.layout.item_follow_card_type

        override fun convert(helper: BaseViewHolder, item: Daily.Item) {
            val imageView: ImageView =
                helper.getView(R.id.ivVideo)
            val imageView1: ImageView =
                helper.getView(R.id.ivAvatar)
            Glide.with(context).load(item.data.content.data.cover.feed).into(imageView)
            Glide.with(context).load(item.data.header.icon).into(imageView1)
            helper.setText(R.id.tvVideoDuration, item.data.header.description)
            helper.setText(R.id.tvTitle, item.data.header.title)
            if (item.data.content.data.ad) {
                helper.setVisible(R.id.tvLabel, true)
            } else {
                helper.setGone(R.id.tvLabel, true)
            }
            if (item.data.content.data.library == "DAILY") {
                helper.setVisible(R.id.ivChoiceness, true)
            } else {
                helper.setVisible(R.id.ivChoiceness, false)
            }
            helper.itemView.setOnClickListener(View.OnClickListener { v: View? ->
                val (ad, _, author, _,
                    _, category, _, consumption, cover
                        , _, _, description, _, _, _
                        , _, id, _, _, _, _,
                    _, library, _, playUrl, _, _
                        , _, _, _, _, _, _, _, _
                        , _, _, _, _, _, title, _,
                    _, _, _, webUrl) = item.data.content.data
                if (ad || author == null) {
                    CardFragment.activity?.let { start(it, id) }
                } else {
                    CardFragment.activity?.let {
                        start(it, VideoInfo(
                                id,
                                playUrl,
                                title,
                                description,
                                category,
                                library,
                                consumption,
                                cover,
                                author,
                                webUrl
                            )
                        )
                    }
                }
            })
        }

    }

    private inner class InformationCardFollowCardViewHolder(val fragment: CardFragment) :
        BaseItemProvider<Daily.Item>() {
        override val itemViewType: Int
            get() = Const.ItemViewType.INFORMATION_CARD
        override val layoutId: Int
            get() = R.layout.item_information_card_type

        override fun convert(helper: BaseViewHolder, item: Daily.Item) {
            val ivCover: ImageView =
                helper.getView(R.id.ivCover)
            val recyclerView: RecyclerView = helper.getView(R.id.recyclerView)
            ivCover.load(item.data.backgroundImage, 4f, RoundedCornersTransformation.CornerType.TOP)

            recyclerView.setHasFixedSize(true)

            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.adapter = fragment.activity?.let {
                InformationCardFollowCardAdapter(
                    it
                    , item.data.actionUrl, item.data.titleList
                )
            }
            helper.itemView.setOnClickListener(View.OnClickListener { v: View? ->
                process(
                    fragment, item.data.actionUrl, ""
                )
            })
        }

    }

    private inner class TextCardViewFooter2ViewHolder(val fragment: CardFragment) :
        BaseItemProvider<Daily.Item>() {
        override val itemViewType: Int
            get() = Const.ItemViewType.TEXT_CARD_FOOTER2
        override val layoutId: Int
            get() = R.layout.item_text_card_type_footer_two

        override fun convert(helper: BaseViewHolder, item: Daily.Item) {
            helper.setText(R.id.tvFooterRightText2, item.data.text)

            helper.getView<TextView>(R.id.tvFooterRightText2).setOnClickListener {
                process(fragment, item.data.actionUrl, item.data.text)
            }
            helper.getView<ImageView>(R.id.ivTooterInto2).setOnClickListener {
                process(fragment, item.data.actionUrl, item.data.text)

            }
        }

    }

    private inner class TextCardViewFooter3ViewHolder(fragment: CardFragment) :
        BaseItemProvider<Daily.Item>() {
        override val itemViewType: Int
            get() = Const.ItemViewType.TEXT_CARD_FOOTER3
        override val layoutId: Int
            get() = R.layout.item_text_card_type_footer_three

        override fun convert(helper: BaseViewHolder, item: Daily.Item) {
            helper.setText(R.id.tvFooterRightText3, item.data.text)

        }

    }

    private inner class TextCardViewHeader5ViewHolder(val fragment: CardFragment) :
        BaseItemProvider<Daily.Item>() {
        override val itemViewType: Int
            get() = Const.ItemViewType.TEXT_CARD_HEADER5
        override val layoutId: Int
            get() = R.layout.item_text_card_type_header_five

        override fun convert(helper: BaseViewHolder, item: Daily.Item) {
            helper.setText(R.id.tvTitle5, item.data.text)
            if (item.data.actionUrl != null) {
                helper.setVisible(R.id.ivInto5, true)
            } else {
                helper.setGone(R.id.ivInto5, true)
            }

            if (item.data.follow != null) {
                helper.setVisible(R.id.tvFollow, true)
            } else {
                helper.setGone(R.id.tvFollow, true)
            }

            helper.getView<TextView>(R.id.tvTitle5).setOnClickListener {
                process(fragment, item.data.actionUrl, item.data.text)
            }
            helper.getView<ImageView>(R.id.ivInto5).setOnClickListener {
                process(fragment, item.data.actionUrl, "${item.data.text},${item.data.rightText}")
            }
        }

    }

    private inner class TextCardViewHeader7ViewHolder(fragment: CardFragment) :
        BaseItemProvider<Daily.Item>() {
        override val itemViewType: Int
            get() = Const.ItemViewType.TEXT_CARD_HEADER7
        override val layoutId: Int
            get() = R.layout.item_text_card_type_header_seven

        override fun convert(helper: BaseViewHolder, item: Daily.Item) {
            helper.setText(R.id.tvTitle7, item.data.text)
            helper.setText(R.id.tvRightText7, item.data.rightText)
            //TODO 点击事件
        }

    }

    private inner class TextCardViewHeader8ViewHolder(val fragment: CardFragment) :
        BaseItemProvider<Daily.Item>() {
        override val itemViewType: Int
            get() = Const.ItemViewType.TEXT_CARD_HEADER8
        override val layoutId: Int
            get() = R.layout.item_text_card_type_header_eight

        override fun convert(helper: BaseViewHolder, item: Daily.Item) {
            helper.setText(R.id.tvTitle8, item.data.text)
            helper.setText(R.id.tvRightText8, item.data.rightText)


            helper.getView<TextView>(R.id.tvRightText8).setOnClickListener {
                process(fragment, item.data.actionUrl, item.data.text)
            }
            helper.getView<ImageView>(R.id.ivInto8).setOnClickListener {
                process(fragment, item.data.actionUrl, item.data.text)

            }
        }

    }

    companion object {
        const val TAG = "CardAdapter"
        const val DEFAULT_LIBRARY_TYPE = "DEFAULT"
        const val NONE_LIBRARY_TYPE = "NONE"
        const val DAILY_LIBRARY_TYPE = "DAILY"
    }

    inner class InformationCardFollowCardAdapter(
        val activity: Activity,
        val actionUrl: String?,
        val dataList: List<String>
    ) :
        RecyclerView.Adapter<InformationCardFollowCardAdapter.ViewHolder>() {

        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val tvNews = view.findViewById<TextView>(R.id.tvNews)
        }

        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): InformationCardFollowCardAdapter.ViewHolder {
            return ViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_information_card_type_item, parent, false)
            )
        }

        override fun getItemCount() = dataList.size

        override fun onBindViewHolder(
            holder: InformationCardFollowCardAdapter.ViewHolder,
            position: Int
        ) {
            val item = dataList[position]
            holder.tvNews.text = item
            holder.itemView.setOnClickListener { process(activity, actionUrl) }
        }
    }
}
