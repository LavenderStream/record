package org.record.tiny.demo.main;

import android.os.Bundle;

import org.record.tiny.R;
import org.record.tiny.base.BaseActivity;
import org.record.tiny.base.BasePresenter;
import org.record.tiny.demo.ui.view.TabStyleWrapper;


/**
 * @param conpresenter/<P>
 */
@SuppressWarnings("All")
public abstract class TabStyleActivity<P extends BasePresenter> extends BaseActivity implements TabStyleWrapper.TabStyle {

    protected TabStyleWrapper mViewWrapper;
    protected P mvpPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(createViewLayoutId());
        mvpPresenter = createPresenter();

        if (mViewWrapper == null) {
            mViewWrapper = new TabStyleWrapper(this, getSupportFragmentManager(), findViewById(R.id.activity_demo));
        }
    }

    protected abstract int createViewLayoutId();

    protected abstract P createPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mvpPresenter != null) {
            mvpPresenter.detachView();
        }
    }
}
