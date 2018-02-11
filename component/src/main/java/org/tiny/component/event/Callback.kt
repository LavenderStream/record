package org.tiny.component.event

/**
 * Created by tiny on 2/11/2018
 */
interface Callback<T> {
    fun callback(t: T);
}