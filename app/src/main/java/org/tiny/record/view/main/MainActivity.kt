package org.tiny.record.view.main

import android.os.Bundle
import android.support.v4.app.Fragment
import com.apkfuns.logutils.LogUtils
import org.tiny.component.ComponentManager
import org.tiny.component.IMain
import org.tiny.lib.core.BaseActivity
import org.tiny.lib.core.BaseTabStyleWrapper
import org.tiny.record.R

/**
 * Created by tiny on 2/11/2018
 */
class MainActivity : BaseActivity<MainPresenter>(), MainContract.IView, BaseTabStyleWrapper.Fragments {

    val mComponent: IMain = ComponentManager.getInstance().getComponent(IMain::class.java)
    val mTabStyleWrapper: BaseTabStyleWrapper = BaseTabStyleWrapper()

    override fun createPresenter(): MainPresenter? {
        return MainPresenter(this)
    }

    override fun createLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        super.onCreate(savedInstanceState)
        mTabStyleWrapper.addParams(this, supportFragmentManager, R.id.root_view)
        mTabStyleWrapper.setTab(IMain.PAGE_LIST)
    }

    override fun onResume() {
        super.onResume()
        mPresenter!!.run()
    }

    override fun getFragments(): Array<Class<out Fragment>> {
        return mComponent.getFragments()
    }

    override fun jumpEditPage() {
        mComponent.startEditActivity(this)
    }

    override fun jumpPreviewPage(id: Int) {
        LogUtils.d("MainActivity -> jumpPreviewPage " + id)
    }
}
