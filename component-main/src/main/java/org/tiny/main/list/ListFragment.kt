package org.tiny.main.list

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import kotlinx.android.synthetic.main.main_fragment_list.*
import org.tiny.component.IMain
import org.tiny.component.event.ClickEvent
import org.tiny.componentmain.R
import org.tiny.lib.core.BaseFragment
import org.tiny.lib.core.RxBus
import org.tiny.lib.view.SimpleRecyclerAdapter
import org.tiny.lib.view.SimpleRecyclerViewHolder

/**
 * Created by tiny on 2/11/2018
 */
class ListFragment : BaseFragment<ListPresenter>(), ListContract.IView {

    private var mAdapter: SimpleRecyclerAdapter<ListViewModel>? = null
    private val mData: ArrayList<ListViewModel> = arrayListOf()

    override fun createPresenter(): ListPresenter? {
        return ListPresenter(this)
    }

    override fun createLayoutId(): Int {
        return R.layout.main_fragment_list
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun onResume() {
        super.onResume()
        mPresenter!!.run()
    }

    private fun initView() {
        mAdapter = object : SimpleRecyclerAdapter<ListViewModel>(activity, mData) {
            override fun bindData(holder: SimpleRecyclerViewHolder?, position: Int, item: ListViewModel?) {
                holder!!.setText(R.id.tv_title, item!!.title)
                holder.setText(R.id.tv_subtitle, item.subTitle)
                holder.setText(R.id.tv_desc, item.description)
                holder.getView(R.id.root_view).setOnClickListener {
                    RxBus.getInstance().post(ClickEvent(IMain.CLICK_ITEM, position))
                }
            }

            override fun getItemLayoutId(viewType: Int): Int {
                return R.layout.main_list_item
            }
        }
        rv_list.adapter = mAdapter
        rv_list.layoutManager = LinearLayoutManager(activity)
        btn_write.setOnClickListener {
            RxBus.getInstance().post(ClickEvent(IMain.CLICK_EDIT, IMain.CLICK_EDIT))
        }
    }

    override fun handleDatas(vm: ArrayList<ListViewModel>) {
        mData.clear()
        mData.addAll(vm)
        mAdapter!!.notifyDataSetChanged()
    }
}