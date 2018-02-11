package org.tiny.main

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.ListFragment
import org.tiny.component.IMain
import org.tiny.component.event.LocationEvent
import org.tiny.lib.core.RxBus
import org.tiny.main.edit.EditFragment
import org.tiny.main.location.LocationManager
import org.tiny.main.preview.PreviewFragment

/**
 * Created by tiny on 2/10/2018
 */
class ComponentImpl : IMain {
    override fun getFragments(): Array<Class<out Fragment>> {
        return arrayOf(ListFragment::class.java, EditFragment::class.java, PreviewFragment::class.java)
    }

    override fun getLocation(context: Context) {
        LocationManager().getLocations(context, { poi ->
            RxBus.getInstance().post(LocationEvent(poi))
        })
    }

}