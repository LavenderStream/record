package org.tiny.lib.core

import android.databinding.ViewDataBinding
import android.support.annotation.NonNull

/**
 * Created by tiny on 2/10/2018
 */
abstract class BaseViewWrapper<B : ViewDataBinding> {
    protected var mBinding: B? = null

    fun addBinding(@NonNull binding: B) {
        this.mBinding = binding
    }

    abstract fun release()
}