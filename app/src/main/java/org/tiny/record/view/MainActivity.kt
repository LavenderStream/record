package org.tiny.record.view

import android.os.Bundle
import org.tiny.component.ComponentManager
import org.tiny.component.IMain
import org.tiny.lib.core.BaseActivity
import org.tiny.lib.core.BasePresenter
import org.tiny.lib.core.BaseView
import org.tiny.record.R
import org.tiny.record.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding, BasePresenter<BaseView>>() {
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

    }
}
