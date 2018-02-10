package org.tiny.record.view.splash

import android.os.Bundle
import com.apkfuns.logutils.LogUtils
import com.baidu.location.Poi
import org.tiny.lib.core.BaseActivity
import org.tiny.record.databinding.ActivitySplashBinding

/**
 * Created by tiny on 2/10/2018
 */
class SplashActivity : BaseActivity<ActivitySplashBinding, SplashPresenter>(), SplashContract.IView {

    override fun createPresenter(): SplashPresenter? {
        return SplashPresenter(this)
    }

    override fun createLayoutId(): Int {
        return -1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPresenter!!.run()
    }

    override fun skip() {
    }

    override fun start(locations: ArrayList<Poi>) {
        locations.forEach({ point ->
            LogUtils.d("location: " + point.name)
        })
    }
}