package org.tiny.record.view.main

import com.trello.rxlifecycle2.android.ActivityEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import org.tiny.component.IMain
import org.tiny.component.event.ClickEvent
import org.tiny.lib.core.BasePresenter
import org.tiny.lib.core.RxBus

/**
 * Created by tiny on 2/11/2018
 */
class MainPresenter(view: MainContract.IView) : BasePresenter<MainContract.IView>(view) {
    fun run() {
        registerEditClick()
        registerItemClick()
    }

    fun registerItemClick() {
        RxBus.getInstance()
                .toFlowable(ClickEvent::class.java)
                .filter { e -> e.message.equals(IMain.CLICK_ITEM) }
                .compose(mView!!.bindLifecycle(ActivityEvent.PAUSE))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { id -> mView!!.jumpPreviewPage(id.t as Int) }
    }

    fun registerEditClick() {
        RxBus.getInstance()
                .toFlowable(ClickEvent::class.java)
                .filter { e -> e.message.equals(IMain.CLICK_EDIT) }
                .compose(mView!!.bindLifecycle(ActivityEvent.PAUSE))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { mView!!.jumpEditPage() }
    }
}