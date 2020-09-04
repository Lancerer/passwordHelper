package com.lancer.passwordhelper.model.bean

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class VideoInfo(
    val videoId: Long,
    val playUrl: String,
    val title: String,
    val description: String,
    val category: String,
    val library: String,
    val consumption: Consumption,
    val cover: Cover,
    val author: Author?,
    val webUrl: WebUrl
) : Parcelable
