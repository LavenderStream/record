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
                    val vm: ArrayList<ViewModel> = arrayListOf()
                    for (article in db) {
                        vm.add(ViewModel(article.title, article.title, article.title))
                    }
                    mView!!.handleDatas(vm)
                })
    }

    fun test() {
        val vm: ArrayList<ViewModel> = arrayListOf()
        for (item in 0 until 10) {
            vm.add(ViewModel("11", "22", "33"))
        }
        mView!!.handleDatas(vm)
    }
}