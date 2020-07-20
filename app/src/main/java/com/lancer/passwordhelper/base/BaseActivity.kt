package com.lancer.passwordhelper.base

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.SkinAppCompatDelegateImpl
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.gyf.immersionbar.ImmersionBar

abstract class BaseActivity<V : ViewDataBinding> : AppCompatActivity() {
    protected lateinit var binding: V
    var name = "base"

    /**
     * 换肤框架
     */
    override fun getDelegate(): AppCompatDelegate {
        return SkinAppCompatDelegateImpl.get(this, this);
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(name, "onCreate")
        binding = DataBindingUtil.setContentView(this, initLayout())
        setStatusBarColor()
        initView()
        initData()
    }

    override fun onStart() {
        super.onStart()
        Log.d(name, "onStart")

    }

    override fun onResume() {
        super.onResume()
        Log.d(name, "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(name, "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(name, "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(name, "onDestroy")
        binding.unbind()
    }

    abstract fun initView()
    abstract fun initData()
    abstract fun initLayout(): Int


    open fun setStatusBarColor() {
        ImmersionBar.hideStatusBar(window)
//        ImmersionBar.with(this)
//            .autoStatusBarDarkModeEnable(true, 0.2f)
//            .fitsSystemWindows(true)
//            .statusBarColorInt(Color.WHITE)
//            .init()
    }

    fun start(clz: Class<*>) {
        startActivity(Intent(this, clz))
    }
}