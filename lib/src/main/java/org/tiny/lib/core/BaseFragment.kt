package org.tiny.lib.core

import android.content.Context
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.trello.rxlifecycle2.LifecycleTransformer
import com.trello.rxlifecycle2.android.ActivityEvent
import com.trello.rxlifecycle2.android.FragmentEvent
import com.trello.rxlifecycle2.components.support.RxFragment

/**
 * Created by tiny on 2/11/2018
 */
abstract class BaseFragment<B : ViewDataBinding, P : BasePresenter<*>> : RxFragment(), BaseView {
    protected var mPresenter: P? = null
    protected var mBinding: B? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        mPresenter = createPresenter()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = DataBindingUtil.inflate(inflater, createLayoutId(), container, false)
        return mBinding!!.getRoot()
    }

    override fun <T> bindLifecycle(event: ActivityEvent): LifecycleTransformer<T>? {
        return null
    }

    override fun <T> bindLifecycle(event: FragmentEvent): LifecycleTransformer<T> {
        return super.bindUntilEvent(event)
    }

    abstract fun createPresenter(): P?

    abstract fun createLayoutId(): Int
}