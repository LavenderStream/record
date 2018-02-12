package org.tiny.record.view.splash

import com.trello.rxlifecycle2.android.ActivityEvent
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import org.tiny.component.ComponentManager
import org.tiny.component.IMain
import org.tiny.component.event.LocationEvent
import org.tiny.lib.core.BasePresenter
import org.tiny.lib.core.RxBus
import org.tiny.record.App
import java.util.concurrent.TimeUnit


/**
 * Created by tiny on 2/10/2018
 */
class SplashPresenter(view: SplashContract.IView) : BasePresenter<SplashContract.IView>(view) {

    private val mComponent: IMain = ComponentManager.getInstance().getComponent(IMain::class.java)
    private var mDisposable: Disposable? = null

    fun run() {
        mDisposable = RxBus.getInstance()
                .toFlowable(LocationEvent::class.java)
                .compose(mView!!.bindLifecycle(ActivityEvent.DESTROY))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ poi ->
                    mView!!.start(poi.locations)
                })

        mComponent.getLocation(App.context!!)
    }

    fun limit() {
        Observable.timer(5, TimeUnit.SECONDS)
                .compose(mView!!.bindLifecycle(ActivityEvent.DESTROY))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    mDisposable!!.dispose()
                    mView!!.skip()
                })
    }
}