package org.tiny.lib.core

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity


/**
 * Created by tiny on 2/10/2018
 */
abstract class BaseActivity<B : ViewDataBinding, P : BasePresenter<*>> : RxAppCompatActivity(), BaseView {
    protected var mPresenter: P? = null
    protected var mBinding: B? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, createLayoutId())
        mPresenter = createPresenter()
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter!!.detachView()
    }

    abstract fun createPresenter(): P?

    abstract fun createLayoutId(): Int
}