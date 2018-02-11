package org.tiny.main.edit

import org.tiny.componentmain.R
import org.tiny.lib.core.BaseFragment


/**
 * Created by tiny on 2/11/2018
 */
class EditFragment : BaseFragment<EditPresenter>(), EditContract.IView {
    override fun createLayoutId(): Int {
        return R.layout.main_fragment_edit
    }

    override fun createPresenter(): EditPresenter? {
        return EditPresenter(this)
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
    }
    override fun onDestroy() {
        super.onDestroy()
    }
}