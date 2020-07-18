package com.lancer.eyelast.base

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
        Log.d(name, "onAttach")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(name, "onCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(name, "onCreateView")
        binding = DataBindingUtil.inflate(inflater, initLayout(), container, false)
        return binding.root

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d(name, "onActivityCreated")

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(name, "onViewCreated")
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

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(name, "onDestroyView")
        binding.unbind()
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(name, "onDestroy")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d(name, "onDetach")

    }

    abstract fun initView()

    abstract fun initData()

    abstract fun initLayout(): Int
}