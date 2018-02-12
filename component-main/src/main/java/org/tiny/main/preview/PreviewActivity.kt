package org.tiny.main.preview

import android.os.Bundle
import kotlinx.android.synthetic.main.main_activity_preview.*
import org.tiny.componentmain.R
import org.tiny.lib.core.BaseActivity

/**
 * Created by tiny on 2/11/2018
 */
class PreviewActivity : BaseActivity<PreviewPresenter>(), PreviewContract.IView {

    override fun createPresenter(): PreviewPresenter? {
        return PreviewPresenter(this)
    }

    override fun createLayoutId(): Int {
        return R.layout.main_activity_preview
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val id = intent.getIntExtra("PREVIEW_DATABASE_ID", -1)
        mPresenter!!.run(id)
    }

    override fun handleData(content: String) {
        tv_content.setText(content)
    }
}