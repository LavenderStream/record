package org.tiny.main.list

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.apkfuns.logutils.LogUtils
import kotlinx.android.synthetic.main.main_fragment_list.*
import org.tiny.componentmain.R
import org.tiny.lib.core.BaseFragment
import org.tiny.lib.view.SimpleRecyclerAdapter
import org.tiny.lib.view.SimpleRecyclerViewHolder

/**
 * Created by tiny on 2/11/2018
 */
class ListFragment : BaseFragment<ListPresenter>(), ListContract.IView {

    private var mAdapter: SimpleRecyclerAdapter<ViewModel>? = null
    private val mData: ArrayList<ViewModel> = arrayListOf()

    override fun createPresenter(): ListPresenter? {
        return ListPresenter(this)
    }

    override fun createLayoutId(): Int {
        return R.layout.main_fragment_list
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        LogUtils.d("ListFragment -> onViewCreated : ")
        mPresenter!!.test()
    }

    private fun initView() {
        mAdapter = object : SimpleRecyclerAdapter<ViewModel>(activity, mData) {
            override fun bindData(holder: SimpleRecyclerViewHolder?, position: Int, item: ViewModel?) {
                holder!!.setText(R.id.tv_title, item!!.title)
                holder.setText(R.id.tv_subtitle, item.subTitle)
                holder.setText(R.id.tv_desc, item.description)
            }

            override fun getItemLayoutId(viewType: Int): Int {
                return R.layout.main_list_item
            }
        }
        rv_list.adapter = mAdapter
        rv_list.layoutManager = LinearLayoutManager(activity)
    }

    override fun handleDatas(vm: ArrayList<ViewModel>) {
        LogUtils.d("ListFragment -> handleDatas : " + vm.size)
        mData.addAll(vm)
        mAdapter!!.notifyDataSetChanged()
    }
}