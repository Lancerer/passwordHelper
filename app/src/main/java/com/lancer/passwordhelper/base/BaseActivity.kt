package com.lancer.passwordhelper.base

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.gyf.immersionbar.ImmersionBar
import com.lancer.passwordhelper.R

abstract class BaseActivity<V : ViewDataBinding> : AppCompatActivity() {
    protected lateinit var binding: V
     var baseTag = "base"

    /**
     * 换肤框架
     */
//    override fun getDelegate(): AppCompatDelegate {
//        return SkinAppCompatDelegateImpl.get(this, this);
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.w(baseTag, "onCreate")
        binding = DataBindingUtil.setContentView(this, initLayout())
        setStatusBarColor()
        initView()
        initData()
    }

    override fun onStart() {
        super.onStart()
        Log.w(baseTag, "onStart")

    }

    override fun onResume() {
        super.onResume()
        Log.w(baseTag, "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.w(baseTag, "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.w(baseTag, "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.w(baseTag, "onDestroy")
        binding.unbind()
    }

    abstract fun initView()
    abstract fun initData()
    abstract fun initLayout(): Int


    open fun setStatusBarColor() {
//        ImmersionBar.hideStatusBar(window)
        ImmersionBar.with(this)
            .autoStatusBarDarkModeEnable(true, 0.2f)
            .fitsSystemWindows(true)
            .statusBarColor(R.color.color_gary)
            .init()
    }

    fun start(clz: Class<*>) {
        startActivity(Intent(this, clz))
    }
}