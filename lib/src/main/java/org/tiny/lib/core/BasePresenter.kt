package org.tiny.lib.core

import io.reactivex.FlowableTransformer
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


/**
 * Created by tiny on 2/10/2018
 */
open class BasePresenter<V : BaseView>(view: V) {
    protected var mView: V? = null

    fun attachView(view: V) {
        mView = view
    }

    fun detachView() {
        this.mView = null
    }

    init {
        attachView(view)
    }

    protected fun <T> applySchedulers(): FlowableTransformer<T, T> {
        return FlowableTransformer { upstream ->
            upstream.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
        }
    }

    protected fun <T> applyScheduler(): ObservableTransformer<T, T> {
        return ObservableTransformer { upstream ->
            upstream.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
        }
    }


}