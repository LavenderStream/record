package org.tiny.main.list

import org.tiny.componentmain.R
import org.tiny.componentmain.databinding.MainFragmentListBinding
import org.tiny.lib.core.BaseFragment

/**
 * Created by tiny on 2/11/2018
 */
class ListFragment : BaseFragment<MainFragmentListBinding, ListPresenter>(), ListContract.IView {
    override fun createPresenter(): ListPresenter? {
        return ListPresenter(this)
    }

    override fun createLayoutId(): Int {
        return R.layout.main_fragment_list
    }
}