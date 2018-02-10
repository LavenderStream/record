package org.tiny.record.view.splash

import com.apkfuns.logutils.LogUtils
import com.trello.rxlifecycle2.android.ActivityEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import org.tiny.component.ComponentManager
import org.tiny.component.IMain
import org.tiny.component.event.LocationEvent
import org.tiny.lib.core.BasePresenter
import org.tiny.lib.core.RxBus
import org.tiny.record.App


/**
 * Created by tiny on 2/10/2018
 */
class SplashPresenter(view: SplashContract.IView) : BasePresenter<SplashContract.IView>(view) {

    private val mComponent: IMain = ComponentManager.getInstance().getComponent(IMain::class.java)

    fun run() {
        RxBus.getInstance()
                .toFlowable(LocationEvent::class.java)
                .compose(mView!!.bindLifecycle(ActivityEvent.DESTROY))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ poi ->
                    LogUtils.d("SplashPresenter " + poi.locations.size)
                    mView!!.start(poi.locations)
                })

        mComponent.getLocation(App.context!!)
    }
}