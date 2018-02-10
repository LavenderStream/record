package org.tiny.component

import android.content.Context
import android.support.v4.app.Fragment

/**
 * Created by tiny on 2/10/2018
 */
interface IMain : IProvider {
    fun getTestString(): String
    fun getLocation(context: Context)
    fun getMainPage(): Fragment
}