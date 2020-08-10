package com.lancer.passwordhelper

import android.app.Application
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import androidx.multidex.MultiDex
import com.lancer.passwordhelper.model.bean.Category
import com.lancer.passwordhelper.model.database.DaoMaster
import com.lancer.passwordhelper.model.database.DaoSession
import com.lancer.passwordhelper.utils.AppPrefsUtils
import com.scwang.smart.refresh.footer.ClassicsFooter
import com.scwang.smart.refresh.header.ClassicsHeader
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import skin.support.SkinCompatManager
import skin.support.app.SkinAppCompatViewInflater
import skin.support.design.app.SkinMaterialViewInflater
import kotlin.properties.Delegates


/**
 *
 *created by Lancer on 2020/7/18
 *desc
 */
class BaseApplication : Application() {


    private lateinit var daoMaster: DaoMaster
    private lateinit var helper: DaoMaster.DevOpenHelper
    private lateinit var daoSession: DaoSession
    private lateinit var dataBase: SQLiteDatabase

    companion object {
        var context: Context by Delegates.notNull()
            private set
        var instance: BaseApplication by Delegates.notNull()
            private set
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        instance = this
        //   initSkin()
        initDataBase()
    }

    /**
     * 初始化换肤矿建
     */
    private fun initSkin() {
        SkinCompatManager.withoutActivity(this)
            .addInflater(SkinAppCompatViewInflater())// 基础控件换肤初始化
            .addInflater(SkinMaterialViewInflater())// material design 控件换肤初始化[可选]
            .loadSkin()
    }

    /**
     * 初始化数据库
     */
    private fun initDataBase() {
        helper = DaoMaster.DevOpenHelper(this, "pwHelp.db", null)
        dataBase = helper.readableDatabase
        daoMaster = DaoMaster(dataBase)
        daoSession = daoMaster.newSession()
        if (!AppPrefsUtils.getBoolean(Constant.HAS_INIT_DATABASE)) {
            val arrayList = arrayListOf("默认", "娱乐", "办公", "教育")
            for (s in arrayList) {
                val category = Category(s)
                daoSession.categoryDao.insert(category)
            }
            AppPrefsUtils.putBoolean(Constant.HAS_INIT_DATABASE, true)
        }
    }

    fun getDaoSession(): DaoSession {
        return daoSession
    }

    fun getDataBase(): SQLiteDatabase {
        return dataBase
    }


    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    /*全局加载更多布局*/
    init {
        SmartRefreshLayout.setDefaultRefreshInitializer { context, layout ->
            layout.setEnableLoadMore(true)
            layout.setEnableLoadMoreWhenContentNotFull(true)
        }

        SmartRefreshLayout.setDefaultRefreshHeaderCreator { context, layout ->
            layout.setEnableHeaderTranslationContent(true)
            ClassicsHeader(context).setDrawableSize(20f)
        }

        SmartRefreshLayout.setDefaultRefreshFooterCreator { context, layout ->
            layout.setEnableFooterFollowWhenNoMoreData(true)
            layout.setEnableFooterTranslationContent(true)
            layout.setFooterHeight(153f)
            layout.setFooterTriggerRate(0.6f)
            ClassicsFooter(context).setDrawableSize(20f)
        }
    }
}