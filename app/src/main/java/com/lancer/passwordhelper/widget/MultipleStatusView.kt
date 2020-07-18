package com.lancer.passwordhelper.widget

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout

/**
 *
 *created by Lancer on 2020/7/18
 *desc
 */
class MultipleStatusView : RelativeLayout {
    private val mViewStatus = -1

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        attrs.run { }
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
    }

    /**
     * 获取当前状态
     */
    public fun getViewStatus(): Int {
        return mViewStatus
    }
}