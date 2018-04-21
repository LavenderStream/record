package org.tiny.lib.core

import android.app.Application


/**
 * Created by tiny on 2/10/2018
 */
abstract class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}