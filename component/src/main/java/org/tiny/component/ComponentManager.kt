package org.tiny.component

import android.support.annotation.Nullable
import android.text.TextUtils
import java.util.concurrent.ConcurrentHashMap


/**
 * Created by tiny on 2/10/2018
 */
class ComponentManager private constructor() {
    private val components = ConcurrentHashMap<String, IComponent>()
    private val provider = ConcurrentHashMap<String, IProvider>()

    @Synchronized
    fun addProvider(interfaceClass: Class<*>, impl: IProvider) {
        provider.put(interfaceClass.name, impl)
    }

    @Suppress("UNCHECKED_CAST")
    @Synchronized
    fun <T : IProvider> getComponent(interfaceClass: Class<*>): T {
        return provider.get(interfaceClass.name) as T
    }

    @Synchronized
    fun removeProvider(interfaceClass: Class<*>) {
        provider.remove(interfaceClass.name)
    }

    /**
     * 注册组件
     *
     * @param classname 组件名
     */
    @Synchronized
    fun addComponent(@Nullable classname: String) {
        if (TextUtils.isEmpty(classname)) {
            return
        }
        if (components.keys.contains(classname)) {
            return
        }
        try {
            val clazz = Class.forName(classname)
            val app = clazz.newInstance() as IComponent
            app.onCreate()
            components.put(classname, app)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    /**
     * 反注册组件
     *
     * @param classname 组件名
     */
    @Synchronized
    fun removeComponent(@Nullable classname: String) {
        if (TextUtils.isEmpty(classname)) {
            return
        }
        if (components.keys.contains(classname)) {
            components.get(classname)!!.onDestroy()
            components.remove(classname)
            return
        }
        try {
            val clazz = Class.forName(classname)
            val app = clazz.newInstance() as IComponent
            app.onDestroy()
            components.remove(classname)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    companion object {
        private var mInstance: ComponentManager? = null
        fun getInstance(): ComponentManager {
            if (mInstance == null) {
                synchronized(ComponentManager::class.java) {
                    if (mInstance == null) {
                        mInstance = ComponentManager()
                    }
                }
            }
            return mInstance!!
        }
    }
}