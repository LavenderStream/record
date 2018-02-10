package org.tiny.record.view.splash

import com.baidu.location.Poi
import org.tiny.lib.core.BaseView

/**
 * Created by tiny on 2/10/2018
 */
class SplashContract {
    interface IView : BaseView {
        fun start(locations: ArrayList<Poi>)
        fun skip()
    }
}