package com.lancer.passwordhelper.test

import android.content.Context
import android.content.Intent
import android.graphics.PixelFormat
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.MotionEvent
import android.view.ViewGroup
import android.view.WindowManager
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.lancer.passwordhelper.R

class TestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    private fun init() {
        //给recyclerView添加条目动画
        initRecyclerViewItemAnim()
        //设置Activity转场动画
        initActivityAnim()
        //window和windowManager(添加view，更新view，删除view windowManager继承viewManager(三个接口),windowManagerImpI实现windowManager
        // windowManagerImpI对象委托windowManagerGlobal实现具体操作,最终是通过ViewRootImpI来),
        initWindow()
        createWindow()
        //Activity启动流程
        initActivityStartUp()
        //service的工作过程
        initService()
    }


    private fun initRecyclerViewItemAnim() {
        val animation = AnimationUtils.loadAnimation(this, R.anim.layout_item_anim)
        val controller = LayoutAnimationController(animation)
        controller.delay = 0.5f
        controller.order = LayoutAnimationController.ORDER_NORMAL
        val recyclerView = RecyclerView(this)
        recyclerView.layoutAnimation = controller
    }


    private fun initActivityAnim() {
        //注意overridePendingTransition(anim,anim)这个方法只能在startActivity()和finish()方法之后调用
        startActivity(Intent())
        overridePendingTransition(R.anim.nav_default_enter_anim, R.anim.nav_default_exit_anim)
    }

    override fun finish() {
        super.finish()
        //前面一个参数是进场动画，后面一个是退场动画
        overridePendingTransition(R.anim.nav_default_enter_anim, R.anim.nav_default_exit_anim)
    }


    //将一个Button添加到屏幕坐标为100,300的位置
    private fun initWindow() {
        val windowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val button = Button(this)
        button.text = "buttons"
        val layoutParams = WindowManager.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT,
            0,
            0,
            PixelFormat.TRANSPARENT
        )
        layoutParams.gravity = Gravity.CENTER
        layoutParams.x = 100
        layoutParams.y = 300
        windowManager.addView(button, layoutParams)
    }

    //实现可以拖动的window的方式
    fun onTouch(event: MotionEvent): Boolean {
        val windowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val button = Button(this)
        button.text = ""
        val layoutParams = WindowManager.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT,
            0,
            0,
            PixelFormat.TRANSPARENT
        )
        layoutParams.gravity = Gravity.CENTER
        layoutParams.x = 100
        layoutParams.y = 300
        windowManager.addView(button, layoutParams)


        val rawX = event.rawX
        val rawY = event.rawY
        when (event.action) {
            MotionEvent.ACTION_MOVE -> {
                layoutParams.x = rawX.toInt()
                layoutParams.y = rawY.toInt()
                windowManager.updateViewLayout(button, layoutParams)
            }
            else -> {
            }
        }
        return false
    }

    //window的创建=>activity中创建DecorView(FrameLayout)，activity讲布局添加到mContentParent中，然后DecorView被添加到window中,
    // window实际上是抽象类，他的实现类是PhoneView
    private fun createWindow() {

    }


    //Activity启动流程
    private fun initActivityStartUp() {

    }

    //Service工作过程主要是包含类contextWrapper和ContextImpI
    private fun initService() {

    }

    private fun initThreadLocal() {
        val booleanThreadLocal = ThreadLocal<Boolean>()
        booleanThreadLocal.set(true)
        Log.d("TAG", "${Thread.currentThread().name}thread${booleanThreadLocal.get()}")
        Thread {
            booleanThreadLocal.set(false)
            Log.d("TAG", "${Thread.currentThread().name}thread${booleanThreadLocal.get()}")
        }.start()
    }
}