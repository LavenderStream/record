package org.tiny.lib.core


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
}