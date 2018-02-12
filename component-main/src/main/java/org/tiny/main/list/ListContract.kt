package org.tiny.main.list

import org.tiny.lib.core.BaseView

/**
 * Created by tiny on 2/11/2018
 */
class ListContract {
    interface IView : BaseView {
        fun handleDatas(vm: ArrayList<ListViewModel>)
    }
}