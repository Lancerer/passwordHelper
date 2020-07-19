package com.lancer.passwordhelper.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import com.lancer.passwordhelper.R
import java.util.*

/**
 *
 *created by Lancer on 2020/7/18
 *desc 一个方便在多种状态切换的view
 */
class MultipleStatusView : RelativeLayout {
    /**
     * view的状态
     */
    private var mViewStatus = -1

    /**
     * 填充器
     */
    private var mInflater: LayoutInflater? = null

    /**
     * 默认空布局id
     */
    private var mDefaultEmptyViewResId: Int = 0

    /**
     * 默认错误布局id
     */
    private var mDefaultErrorViewResId: Int = 0

    /**
     * 默认加载布局id
     */
    private var mDefaultLoadingViewResId: Int = 0

    /**
     * 默认无网络布局id
     */
    private var mDefaultNoNetworkViewResId: Int = 0

    /**
     * 默认内容布局id
     */
    private var mDefaultContentViewResId: Int = 0

    /**
     * 空布局
     */
    private var mEmptyView: View? = null

    /**
     *错误布局
     */
    private var mErrorView: View? = null

    /**
     *加载布局
     */
    private var mLoadingView: View? = null

    /**
     *无网络布局
     */
    private var mNoNetworkView: View? = null

    /**
     *包裹在MultipleStatusView内的布局，
     */
    private var mContentView: View? = null


    private val mOtherIds = ArrayList<Int>()
    private var mOnRetryClickListener: OnClickListener? = null

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {

        mInflater = LayoutInflater.from(getContext());
        val a = context.obtainStyledAttributes(
            attrs,
            R.styleable.MultipleStatusView,
            defStyleAttr,
            0
        )
        mDefaultEmptyViewResId = a.getResourceId(
            R.styleable.MultipleStatusView_emptyView,
            R.layout.layout_deafult_empty_view
        )
        mDefaultErrorViewResId = a.getResourceId(
            R.styleable.MultipleStatusView_errorView,
            R.layout.layout_deafult_error_view
        )
        mDefaultLoadingViewResId = a.getResourceId(
            R.styleable.MultipleStatusView_loadingView,
            R.layout.layout_deafult_loading_view
        )
        mDefaultNoNetworkViewResId = a.getResourceId(
            R.styleable.MultipleStatusView_noNetworkView,
            R.layout.layout_deafult_no_network_view
        )
        mDefaultContentViewResId = a.getResourceId(
            R.styleable.MultipleStatusView_contentView,
            NULL_RESOURCE_ID
        )
        a.recycle()
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        showContent()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        clear(mEmptyView, mLoadingView, mErrorView, mNoNetworkView)
        mOtherIds?.clear()
        if (null != mOnRetryClickListener) {
            mOnRetryClickListener = null
        }
        mInflater = null
    }

    /**
     * 获取当前状态
     */
    public fun getViewStatus(): Int {
        return mViewStatus
    }

    /**
     * 设置重试点击事件
     *
     * @param onRetryClickListener 重试点击事件
     */
    fun setOnRetryClickListener(onRetryClickListener: OnClickListener) {
        this.mOnRetryClickListener = onRetryClickListener
    }

    /**
     * 显示空视图
     */
    fun showEmpty() {
        showEmpty(mDefaultEmptyViewResId, DEFAULT_LAYOUT_PARAMS)
    }

    /**
     * 显示空视图
     * @param layoutId 自定义布局文件
     * @param layoutParams 布局参数
     */
    fun showEmpty(layoutId: Int, layoutParams: ViewGroup.LayoutParams?) {
        showEmpty(inflateView(layoutId)!!, layoutParams)
    }

    /**
     * 显示空视图
     * @param view 自定义视图
     * @param layoutParams 布局参数
     */
    fun showEmpty(view: View, layoutParams: ViewGroup.LayoutParams?) {
        checkNull(view, "Empty view is null!")
        mViewStatus = STATUS_EMPTY
        if (null == mEmptyView) {
            mEmptyView = view
            val emptyRetryView =
                mEmptyView!!.findViewById<View>(R.id.empty_retry_view)
            if (null != mOnRetryClickListener && null != emptyRetryView) {
                emptyRetryView.setOnClickListener(mOnRetryClickListener)
            }
            mOtherIds.add(mEmptyView!!.id)
            addView(mEmptyView, 0, layoutParams)
        }
        showViewById(mEmptyView!!.id)
    }

    /**
     * 显示错误视图
     */
    fun showError() {
        showError(mDefaultErrorViewResId, DEFAULT_LAYOUT_PARAMS)
    }

    /**
     * 显示错误视图
     * @param layoutId 自定义布局文件
     * @param layoutParams 布局参数
     */
    fun showError(layoutId: Int, layoutParams: ViewGroup.LayoutParams?) {
        showError(inflateView(layoutId)!!, layoutParams)
    }

    /**
     * 显示错误视图
     * @param view 自定义视图
     * @param layoutParams 布局参数
     */
    fun showError(view: View, layoutParams: ViewGroup.LayoutParams?) {
        checkNull(view, "Error view is null!")
        mViewStatus = STATUS_ERROR
        if (null == mErrorView) {
            mErrorView = view
            val errorRetryView =
                mErrorView!!.findViewById<View>(R.id.error_retry_view)
            if (null != mOnRetryClickListener && null != errorRetryView) {
                errorRetryView.setOnClickListener(mOnRetryClickListener)
            }
            mOtherIds.add(mErrorView!!.id)
            addView(mErrorView, 0, layoutParams)
        }
        showViewById(mErrorView!!.id)
    }

    /**
     * 显示加载中视图
     */
    fun showLoading() {
        showLoading(mDefaultLoadingViewResId, DEFAULT_LAYOUT_PARAMS)
    }

    /**
     * 显示加载中视图
     * @param layoutId 自定义布局文件
     * @param layoutParams 布局参数
     */
    fun showLoading(layoutId: Int, layoutParams: ViewGroup.LayoutParams?) {
        showLoading(inflateView(layoutId)!!, layoutParams!!)
    }

    public final fun showLoading(view: View, layoutParams: ViewGroup.LayoutParams) {
        checkNull(view, "Loading view is null!")
        mViewStatus = STATUS_LOADING
        if (mLoadingView == null) {
            mLoadingView = view
            mOtherIds.add(mLoadingView!!.id)
            addView(mLoadingView, 0, layoutParams)
        }
        showViewById(mLoadingView!!.id)
    }

    public final fun showNoNetwork() {
        showNoNetwork(mDefaultNoNetworkViewResId, DEFAULT_LAYOUT_PARAMS)
    }

    public final fun showNoNetwork(layoutId: Int, layoutParams: ViewGroup.LayoutParams) {
        showNoNetwork(inflateView(layoutId)!!, layoutParams)
    }

    public final fun showNoNetwork(view: View, layoutParams: ViewGroup.LayoutParams) {

    }

    /**
     * 显示内容布局,也就是包裹在MultipleStatusView中的布局
     */
    public final fun showContent() {
        mViewStatus = STATUS_CONTENT
        //当MultipleStatusView内部没有布局就填充默认的布局
        if (mContentView == null && mDefaultContentViewResId != NULL_RESOURCE_ID) {
            mContentView = mInflater?.inflate(mDefaultContentViewResId, null)
            addView(mContentView, 0, DEFAULT_LAYOUT_PARAMS)
        }
        //有的话就直接将他包裹的子布局填充即可
        showContentView()
    }

    /**
     * 显示MultipleStatusView内部的所有子布局
     */
    private fun showContentView() {
        val childCount = childCount
        for (i in 0 until childCount) {
            val view = getChildAt(i)
            view.visibility = if (mOtherIds.contains(view.id)) View.GONE else View.VISIBLE
        }
    }

    /**
     * 填充布局方法
     */
    private fun inflateView(layoutId: Int): View? {
        return mInflater?.inflate(layoutId, null)
    }

    private fun showViewById(viewId: Int) {
        val childCount = childCount
        for (i in 0 until childCount) {
            val view = getChildAt(i)
            view.visibility = if (view.id == viewId) View.VISIBLE else View.GONE
        }
    }

    private fun checkNull(any: Any?, hint: String) {
        if (null == any) {
            throw NullPointerException(hint)
        }
    }

    private fun clear(vararg views: View?) {
        if (views.isEmpty()) {
            return
        }
        try {
            for (view in views) {
                view?.let { removeView(it) }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    companion object {
        const val STATUS_CONTENT = 0x00
        const val STATUS_LOADING = 0x01
        const val STATUS_EMPTY = 0x02
        const val STATUS_ERROR = 0x03
        const val STATUS_NO_NETWORK = 0x04
        private const val NULL_RESOURCE_ID = -1


        private val DEFAULT_LAYOUT_PARAMS =
            LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT
            )
    }
}