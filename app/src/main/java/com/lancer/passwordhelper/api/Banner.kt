package com.lancer.passwordhelper.api

/**
 * @author lancer
 * @des
 * @Date 2020/9/8 10:14
 */
data class Banner(
    val `data`: List<Data>,
    val errorCode: Int,
    val errorMsg: String
)

data class Data(
    val desc: String,
    val id: Int,
    val imagePath: String,
    val isVisible: Int,
    val order: Int,
    val title: String,
    val type: Int,
    val url: String
)