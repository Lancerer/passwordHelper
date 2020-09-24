package com.lancer.passwordhelper

/**
 *
 *created by Lancer on 2020/7/19
 *desc
 */
object Constant {

    const val COMMON_SP_NAME: String = "helper"

    //当前账户用户名
    const val CURRENT_USERNAME = "current_username"

    //当前账户密码
    const val CURRENT_PASSWORD = "current_password"

    const val HAS_USER = "has_user"

    //是否初始化数据库
    const val HAS_INIT_DATABASE = "has_init_database"

    //是否使用指纹
    const val HAS_USER_FINGER = "has_user_finger"

    //是否需改密码
    const val HAS_EDIT_PASSWORD = "has_edit_password"

    const val EDIT_ACTIVITY_REQUEST_CODE = 1
    const val HOME_FRAGMENT_REQUEST_CODE = 2

    //是否是第一次登录
    const val IS_FIRST_LOGIN = "is_first_login"

    //传递card值intent的flag
    const val PUT_EXTRA_NAME = "put_extra_name"


    //换肤颜色
    const val DEFAULT = ""
    const val YELLOW = "yellow"
    const val RED = "red"
    const val GREEN = "green"
    const val BLUE = "blue"
    const val PINK = "pink"
    const val WHITE = "white"
    const val BLACK = "black"
    const val ORANGE = "orange"
}