package org.tiny.main.location

import android.content.Context
import com.apkfuns.logutils.LogUtils
import com.baidu.location.LocationClient
import com.baidu.location.LocationClientOption
import com.baidu.location.Poi

/**
 * Created by tiny on 2/10/2018
 */
@Suppress("DEPRECATION")
class LocationManager {
    private var mLocationClient: LocationClient? = null

    fun getLocations(context: Context, callback: (ArrayList<Poi>) -> Unit) {
        LogUtils.d("SplashPresenter --------------------------- ")

        val option = LocationClientOption()
        option.setIsNeedLocationPoiList(true)
        option.setIsNeedLocationDescribe(true)
        mLocationClient = LocationClient(context)
        mLocationClient!!.setLocOption(option)
        mLocationClient!!.registerLocationListener({ location ->
            callback.invoke(location.poiList as ArrayList<Poi>)
        })
        mLocationClient!!.start()
    }
}