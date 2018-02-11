package org.tiny.record.view.splash

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import com.apkfuns.logutils.LogUtils
import com.baidu.location.Poi
import org.tiny.lib.core.BaseActivity
import org.tiny.record.databinding.ActivitySplashBinding
import org.tiny.record.view.main.MainActivity

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
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN)
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        mPresenter!!.run()
    }

    override fun skip() {
    }

    override fun start(locations: ArrayList<Poi>) {
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        startActivity(Intent(this, MainActivity::class.java))
        finish()
        locations.forEach({ point ->
            LogUtils.d("location: " + point.name)
        })
    }
}