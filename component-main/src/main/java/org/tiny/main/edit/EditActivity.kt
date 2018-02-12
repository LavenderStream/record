package org.tiny.main.edit

import android.os.Bundle
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.main_activity_edit.*
import org.tiny.componentmain.R
import org.tiny.lib.core.BaseActivity
import org.tiny.main.ComponentImpl
import java.util.*


/**
 * Created by tiny on 2/12/2018
 */
class EditActivity : BaseActivity<EditPresenter>(), EditContract.IView {
    override fun createPresenter(): EditPresenter? {
        return EditPresenter(this)
    }

    override fun createLayoutId(): Int {
        return R.layout.main_activity_edit
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initSpinner()
        mPresenter!!.run(et_content, sp_location)
    }

    private fun initSpinner() {
        val data: ArrayList<String> = arrayListOf()
        for (location in ComponentImpl.locations) {
            data.add(location.name)
        }
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        sp_location.adapter = adapter
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter!!.save(et_content)
    }
}