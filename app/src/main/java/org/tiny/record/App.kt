package org.tiny.record

import android.app.Application
import org.tiny.component.ComponentManager
import org.tiny.component.IApp

/**
 * Created by tiny on 2/10/2018
 */
class App : Application() {
    private val mComponentManager = ComponentManager.getInstance()
    override fun onCreate() {
        super.onCreate()
        mComponentManager.addProvider(IApp::class.java, ComponentImpl())
    }

    override fun onTerminate() {
        super.onTerminate()
        mComponentManager.removeProvider(IApp::class.java)
    }
}