package org.tiny.record.view.main

import org.tiny.lib.core.BaseView

/**
 * Created by tiny on 2/11/2018
 */
class MainContract {
    interface IView : BaseView {
        fun jumpEditPage()
        fun jumpPreviewPage(id: Int)
    }
}