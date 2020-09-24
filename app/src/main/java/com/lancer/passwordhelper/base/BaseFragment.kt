package com.lancer.passwordhelper.base

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment<V : ViewDataBinding> : Fragment() {
    protected lateinit var binding: V
    var name = "base"

    lateinit var mActivity: Activity


    /**
     * 是否已经加载过数据
     */
    private var mHasLoadedData = false


    override fun onAttach(context: Context) {
        super.onAttach(context)
        mActivity = context as Activity
        Log.w(name, "onAttach")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.w(name, "onCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.w(name, "onCreateView")
        binding = DataBindingUtil.inflate(inflater, initLayout(), container, false)
        return binding.root

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.w(name, "onActivityCreated")

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.w(name, "onViewCreated")

    }


    override fun onStart() {
        super.onStart()
        Log.w(name, "onStart")

    }

    override fun onResume() {
        super.onResume()
        Log.w(name, "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.w(name, "onPause")

    }

    override fun onStop() {
        super.onStop()
        Log.w(name, "onStop")

    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.w(name, "onDestroyView")
        binding.unbind()
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.w(name, "onDestroy")
    }

    override fun onDetach() {
        super.onDetach()
        Log.w(name, "onDetach")

    }

    abstract fun initView()

    abstract fun initData()

    abstract fun initLayout(): Int
}