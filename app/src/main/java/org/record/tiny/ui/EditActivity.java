package org.record.tiny.ui;

import android.os.Bundle;

import org.record.tiny.R;
import org.record.tiny.base.MvpActivity;
import org.record.tiny.ui.view.EditPresenter;
import org.record.tiny.ui.view.EditView;

public class EditActivity extends MvpActivity<EditPresenter> implements EditView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
    }

    @Override
    protected EditPresenter createPresenter() {
        return new EditPresenter();
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
    public void getLocal(String local) {

    }

    @Override
    public void error(int error) {

    }
}
