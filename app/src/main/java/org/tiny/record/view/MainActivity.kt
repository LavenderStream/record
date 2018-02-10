package org.tiny.record.view

import android.os.Bundle
import com.apkfuns.logutils.LogUtils
import com.baidu.location.BDAbstractLocationListener
import com.baidu.location.BDLocation
import com.baidu.location.LocationClient
import com.baidu.location.LocationClientOption
import kotlinx.android.synthetic.main.activity_main.*
import org.tiny.component.ComponentManager
import org.tiny.component.IMain
import org.tiny.lib.core.BaseActivity
import org.tiny.lib.core.BasePresenter
import org.tiny.lib.core.BaseView
import org.tiny.record.R
import org.tiny.record.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding, BasePresenter<BaseView>>() {
    var mLocationClient: LocationClient? = null
    private val myListener = MyLocationListener()


    override fun createLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun createPresenter(): BasePresenter<BaseView>? {
        return BasePresenter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val main: IMain = ComponentManager.getInstance().getComponent(IMain::class.java)
        mBinding!!.model = TestViewModel("" + main.getTestString())

        mLocationClient = LocationClient(getApplicationContext())
        mLocationClient!!.registerLocationListener(myListener)

        val option = LocationClientOption()
        option.setIsNeedLocationPoiList(true);
        option.setIsNeedLocationDescribe(true)
        mLocationClient!!.setLocOption(option)
        mLocationClient!!.start()

        tv_textview.setOnClickListener { _ ->
            LogUtils.d("")
        }
    }

    inner class MyLocationListener : BDAbstractLocationListener() {
        override fun onReceiveLocation(location: BDLocation) {
            location.poiList.forEach(
                    { point ->
                        LogUtils.d("location: " + point.name)
                    }
            )

        }

        override fun onConnectHotSpotMessage(p0: String?, p1: Int) {
            super.onConnectHotSpotMessage(p0, p1)
        }

        override fun onLocDiagnosticMessage(p0: Int, p1: Int, p2: String?) {
            super.onLocDiagnosticMessage(p0, p1, p2)
        }

    }
}
