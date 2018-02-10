package org.tiny.record

import android.databinding.DataBindingUtil
import android.os.Bundle
import com.apkfuns.logutils.LogUtils
import com.trello.rxlifecycle2.android.ActivityEvent
import com.trello.rxlifecycle2.components.RxActivity
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import org.tiny.record.databinding.ActivityMainBinding
import java.util.concurrent.TimeUnit

class MainActivity : RxActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        Flowable.interval(3, TimeUnit.SECONDS)
                .compose(bindUntilEvent(ActivityEvent.DESTROY))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { timer ->
                        binding.model = TestViewModel("" + System.currentTimeMillis())
                        LogUtils.d("haha" + timer)
                }

    }
}
