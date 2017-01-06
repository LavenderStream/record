package org.record.tiny.demo.main;

import android.os.Bundle;
import android.support.annotation.UiThread;

import com.apkfuns.logutils.LogUtils;
import com.google.gson.Gson;

import org.record.tiny.R;
import org.record.tiny.demo.favorite.FavoriteFragment;
import org.record.tiny.demo.fragment.ChoiceFragment;
import org.record.tiny.demo.model.Tab;

import java.util.List;

@SuppressWarnings("All")
public class MainActivity extends TabStyleActivity<MainPresenter> implements MainContract.View {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mvpPresenter.start(this);
    }

    @Override
    protected int createViewLayoutId() {
        return R.layout.activity_demo;
    }

    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter(this);
    }

    @UiThread
    @Override
    public void getTabs(List<Tab.DataBean> tabs) {
        LogUtils.d("MainActivity -> getTabs: " + tabs);
        Bundle bundle = new Bundle();
        Gson gone = new Gson();
        bundle.putString("tab_list", gone.toJson(tabs));
        mViewWrapper.setTab(0, bundle);
    }

    @Override
    public void showLoading() {
    }

    @Override
    public void hideLoading() {
    }

    @Override
    public void error(int error) {
    }

    @Override
    public Class[] getFragments() {
        return new Class[]{ChoiceFragment.class, FavoriteFragment.class};
    }
}
