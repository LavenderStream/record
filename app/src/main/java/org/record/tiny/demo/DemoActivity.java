package org.record.tiny.demo;

import android.app.Activity;
import android.databinding.ViewDataBinding;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.record.tiny.R;
import org.record.tiny.base.BasePresenter;
import org.record.tiny.databinding.ActivityDemoBinding;
import org.record.tiny.ui.display.DisplayPresenter;

public class DemoActivity extends BindingActivity<ActivityDemoBinding, DisplayPresenter> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding.tvTextview.setText("haha");
    }

    @Override
    protected DisplayPresenter createPresenter() {
        return null;
    }

    @Override
    protected int createLayoutId() {
        return R.layout.activity_demo;
    }
}
