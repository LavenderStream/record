package org.tiny.lib.core

import com.trello.rxlifecycle2.LifecycleTransformer
import com.trello.rxlifecycle2.android.ActivityEvent

/**
 * Created by tiny on 2/10/2018
 */
interface BaseView {
    fun <T> bindLifecycle(event: ActivityEvent): LifecycleTransformer<T>
}