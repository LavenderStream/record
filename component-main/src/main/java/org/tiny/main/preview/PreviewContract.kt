package org.tiny.main.preview

import org.tiny.lib.core.BaseView

/**
 * Created by tiny on 2/12/2018
 */
class PreviewContract {
    interface IView : BaseView {
        fun handleData(content: String)
    }
}