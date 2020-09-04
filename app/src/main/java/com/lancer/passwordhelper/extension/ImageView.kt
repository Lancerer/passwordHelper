package com.lancer.passwordhelper.extension

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.lancer.passwordhelper.R
import com.lancer.passwordhelper.utils.DensityUtils.dip2px
import jp.wasabeef.glide.transformations.RoundedCornersTransformation


/**
 * Glide加载图片，可以制定圆角弧度
 * @param url 图片地址
 * @param round 圆角，单位是dp
 * @param cornerType 圆角角度
 */
fun ImageView.load(
    url: String,
    round: Float = 0f,
    cornerType: RoundedCornersTransformation.CornerType = RoundedCornersTransformation.CornerType.ALL
) {
    if (round == 0f) {
        Glide.with(this.context).load(url).into(this)
    } else {
        val options = RequestOptions.bitmapTransform(
            RoundedCornersTransformation(
                dip2px(this.context, round),
                0,
                cornerType
            )
        ).placeholder(R.drawable.shape_album_loading_bg)
    }
}