package com.lancer.passwordhelper.ui.activity.video

import androidx.lifecycle.ViewModel
import com.lancer.passwordhelper.model.MainRepository
import com.lancer.passwordhelper.model.bean.VideoInfo

class VideoViewModel(private val repository: MainRepository) : ViewModel() {
    var videoInfoData: VideoInfo? = null

    var videoId: Long = 0L

}