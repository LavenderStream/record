package org.tiny.main

import android.content.Context
import android.support.v4.app.Fragment
import org.tiny.component.IMain
import org.tiny.component.event.LocationEvent
import org.tiny.lib.core.RxBus
import org.tiny.main.location.LocationManager

/**
 * Created by tiny on 2/10/2018
 */
class ComponentImpl : IMain {
    override fun getLocation(context: Context) {
        LocationManager().getLocations(context, { poi ->
            RxBus.getInstance().post(LocationEvent(poi))
        })
    }

    override fun getMainPage(): Fragment {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override fun getTestString(): String {
        return "main"
    }
}