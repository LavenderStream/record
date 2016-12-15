package org.record.tiny.ui;

import android.content.Intent;
import android.os.Bundle;

import org.record.tiny.R;
import org.record.tiny.base.MvpActivity;
import org.record.tiny.component.CircleButton;
import org.record.tiny.ui.model.Article;
import org.record.tiny.ui.view.MainPresenter;
import org.record.tiny.ui.view.MainView;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

@SuppressWarnings("Allw")
public class MainActivity extends MvpActivity<MainPresenter> implements MainView {

    @Bind(R.id.bt_edit)
    CircleButton mEditButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @OnClick(R.id.bt_edit)
    void goEditActivity() {
        startActivity(new Intent(this, EditActivity.class));
    }

    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter();
    }

    @Override
    public void getYear(String year) {

    }

    @Override
    public void getMonth(String month) {

    }

    @Override
    public void getDay(String day) {

    }

    @Override
    public void getContext(List<Article> articles) {

    }

    @Override
    public void getLocal(String local) {

    }

    @Override
    public void error(int error) {

    }
}
