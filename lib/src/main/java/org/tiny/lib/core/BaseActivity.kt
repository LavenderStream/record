package org.tiny.lib.core

import android.content.Context
import android.os.Bundle
import com.trello.rxlifecycle2.LifecycleTransformer
import com.trello.rxlifecycle2.android.ActivityEvent
import com.trello.rxlifecycle2.android.FragmentEvent
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper


/**
 * Created by tiny on 2/10/2018
 */
abstract class BaseActivity<P : BasePresenter<*>> : UiActivity(), BaseView {
    protected var mPresenter: P? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val layoutId = createLayoutId()
        if (layoutId != -1)
            setContentView(createLayoutId())
        mPresenter = createPresenter()
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter!!.detachView()
    }

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase))
    }

    override fun <T> bindLifecycle(event: ActivityEvent): LifecycleTransformer<T>? {
        return super.bindUntilEvent<T>(event)
    }

    override fun <T> bindLifecycle(event: FragmentEvent): LifecycleTransformer<T>? {
        return null
    }

    abstract fun createPresenter(): P?

    abstract fun createLayoutId(): Int
}