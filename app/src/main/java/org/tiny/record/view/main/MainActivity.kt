package org.tiny.record.view.main

import android.os.Bundle
import android.support.v4.app.Fragment
import org.tiny.component.ComponentManager
import org.tiny.component.IMain
import org.tiny.lib.core.BaseActivity
import org.tiny.lib.core.BasePresenter
import org.tiny.lib.core.BaseTabStyleWrapper
import org.tiny.lib.core.BaseView
import org.tiny.record.R

/**
 * Created by tiny on 2/11/2018
 */
class MainActivity : BaseActivity<BasePresenter<BaseView>>(), BaseView, BaseTabStyleWrapper.Fragments {
    val mComponent: IMain = ComponentManager.getInstance().getComponent(IMain::class.java)
    val mTabStyleWrapper: BaseTabStyleWrapper = BaseTabStyleWrapper()

    override fun createPresenter(): BasePresenter<BaseView>? {
        return BasePresenter(this)
    }

    override fun createLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        super.onCreate(savedInstanceState)
        mTabStyleWrapper.addParams(this, supportFragmentManager, R.id.root_view)
        mTabStyleWrapper.setTab(0)
    }

    override fun getFragments(): Array<Class<out Fragment>> {
        return mComponent.getFragments()
    }
}
