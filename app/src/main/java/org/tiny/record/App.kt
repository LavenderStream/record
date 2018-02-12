package org.tiny.record

import android.annotation.SuppressLint
import android.content.Context
import org.tiny.component.ComponentManager
import org.tiny.component.IApp
import org.tiny.lib.core.BaseApplication
import org.tiny.lib.database.DbHelper

/**
 * Created by tiny on 2/10/2018
 */
class App : BaseApplication() {
    private val mComponentManager = ComponentManager.getInstance()

    override fun onCreate() {
        super.onCreate()
        App.context = this
        DbHelper.init(this)
        mComponentManager.addProvider(IApp::class.java, ComponentImpl())
        mComponentManager.addComponent(Contracts.COMPONENT_MAIN)
    }

    override fun onTerminate() {
        super.onTerminate()
        mComponentManager.removeProvider(IApp::class.java)
        mComponentManager.removeComponent(Contracts.COMPONENT_MAIN)
    }

    override fun addFont(): String {
        return Contracts.FONT
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        var context: Context? = null
    }
}