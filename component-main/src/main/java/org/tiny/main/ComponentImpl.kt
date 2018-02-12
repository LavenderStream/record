package org.tiny.main

import android.content.Context
import android.content.Intent
import android.support.v4.app.Fragment
import com.baidu.location.Poi
import org.tiny.component.IMain
import org.tiny.component.event.LocationEvent
import org.tiny.lib.core.RxBus
import org.tiny.main.edit.EditActivity
import org.tiny.main.list.ListFragment
import org.tiny.main.location.LocationManager

/**
 * Created by tiny on 2/10/2018
 */
class ComponentImpl : IMain {

    override fun startEditActivity(context: Context) {
        context.startActivity(Intent(context, EditActivity::class.java))
    }

    override fun getFragments(): Array<Class<out Fragment>> {
        return arrayOf(ListFragment::class.java)
    }

    override fun getLocation(context: Context) {
        LocationManager().getLocations(context, { poi ->
            locations.clear()
            locations.addAll(poi)
            RxBus.getInstance().post(LocationEvent(poi))
        })
    }

    companion object {
        var locations: ArrayList<Poi> = arrayListOf()
    }
}