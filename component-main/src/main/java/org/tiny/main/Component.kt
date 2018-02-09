package org.tiny.main

import org.tiny.component.ComponentManager
import org.tiny.component.IComponent
import org.tiny.component.IMain


/**
 * Created by tiny on 2/10/2018
 */
class Component : IComponent {
    private val mManager = ComponentManager.getInstance()
    override fun onCreate() {
        mManager.addProvider(IMain::class.java, ComponentImpl())
    }

    override fun onDestroy() {
        mManager.removeProvider(IMain::class.java)
    }
}