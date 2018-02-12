package org.tiny.main.preview

import io.reactivex.Observable
import org.tiny.lib.core.BasePresenter
import org.tiny.lib.database.DbHelper

/**
 * Created by tiny on 2/12/2018
 */
class PreviewPresenter(view: PreviewContract.IView) : BasePresenter<PreviewContract.IView>(view) {
    fun run(id: Int) {
        if (id == -1) return
        Observable.just(DbHelper.getSession().articleDao.load(id.toLong()))
                .compose(applyScheduler())
                .subscribe { db -> mView!!.handleData(db.content) }
    }
}