package org.tiny.main.edit

import android.text.TextUtils
import android.widget.EditText
import android.widget.Spinner
import com.apkfuns.logutils.LogUtils
import com.jakewharton.rxbinding2.widget.RxAdapterView
import com.jakewharton.rxbinding2.widget.RxTextView
import com.trello.rxlifecycle2.android.ActivityEvent
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import org.tiny.lib.core.BasePresenter
import org.tiny.lib.database.DbHelper
import org.tiny.lib.database.entity.Article
import org.tiny.main.ComponentImpl
import java.util.concurrent.TimeUnit

/**
 * Created by tiny on 2/11/2018
 */
class EditPresenter(view: EditContract.IView) : BasePresenter<EditContract.IView>(view) {
    var mDisposable: Disposable? = null
    var mLocationIndex: Int = 0
    val mLastIndex = DbHelper.getSession().articleDao.loadAll().size

    fun run(editView: EditText, spinner: Spinner) {
        LogUtils.d("EditPresenter -> run lastIndex: " + mLastIndex)
        mDisposable = Observable.combineLatest(RxTextView.textChanges(editView), RxAdapterView.itemSelections(spinner),
                BiFunction<CharSequence, Int, Article> { content, index ->
                    LogUtils.d("EditPresenter -> run index: " + index)
                    mLocationIndex = index
                    Article(mLastIndex.toLong(), "", System.currentTimeMillis(), content.toString(), ComponentImpl.locations.get(index).name)
                })
                .throttleLast(3, TimeUnit.SECONDS)
                .compose(mView!!.bindLifecycle(ActivityEvent.PAUSE))
                .observeOn(Schedulers.io())
                .subscribe({ t: Article ->
                    LogUtils.d("EditPresenter -> run: " + t)
                    DbHelper.getSession().articleDao.insertOrReplace(t)
                })
    }

    fun save(editView: EditText) {
        mDisposable!!.dispose()
        if (TextUtils.isEmpty(editView.text)) {
            DbHelper.getSession().articleDao.deleteByKey(mLastIndex.toLong())
        } else {
            DbHelper.getSession().articleDao.insertOrReplace(Article(mLastIndex.toLong(), "", System.currentTimeMillis(), editView.text.toString(),
                    ComponentImpl.locations.get(mLocationIndex).name))
        }
    }
}