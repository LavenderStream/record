package org.tiny.component

import android.content.Context
import android.support.v4.app.Fragment

/**
 * Created by tiny on 2/10/2018
 */
interface IMain : IProvider {
    companion object {
        val PAGE_LIST = 0
        val PAGE_EDIT = 1
        val CLICK_ITEM = 1
        val CLICK_EDIT = 2
    }

    fun getLocation(context: Context)
    fun getFragments(): Array<Class<out Fragment>>
    fun startEditActivity(context: Context)
    fun startPreviewActivity(context: Context, id: Int)
}