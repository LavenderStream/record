package org.tiny.lib.core

import android.app.Application
import org.tiny.lib.R
import uk.co.chrisjenx.calligraphy.CalligraphyConfig


/**
 * Created by tiny on 2/10/2018
 */
abstract class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        val font = addFont()
        if (font.isEmpty()) return
        CalligraphyConfig.initDefault(CalligraphyConfig.Builder()
                .setDefaultFontPath(font)
                .setFontAttrId(R.attr.fontPath)
                .build()
        )
    }

    abstract fun addFont(): String
}