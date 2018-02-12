package org.tiny.main.list

import com.trello.rxlifecycle2.android.FragmentEvent
import io.reactivex.Flowable
import org.tiny.lib.core.BasePresenter
import org.tiny.lib.database.DbHelper

/**
 * Created by tiny on 2/11/2018
 */
class ListPresenter(view: ListContract.IView) : BasePresenter<ListContract.IView>(view) {
    fun run() {
        Flowable.just(DbHelper.getSession().articleDao.loadAll())
                .compose(mView!!.bindLifecycle(FragmentEvent.DESTROY))
                .compose(applySchedulers())
                .subscribe({ db ->
                    val vm: ArrayList<ListViewModel> = arrayListOf()
                    for (article in db) {
                        vm.add(ListViewModel(article.title, article.time.toString(), article.content))
                    }
                    mView!!.handleDatas(vm)
                })
    }
}