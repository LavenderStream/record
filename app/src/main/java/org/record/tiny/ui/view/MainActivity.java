package org.record.tiny.ui.view;

import android.os.Bundle;

import org.record.tiny.R;
import org.record.tiny.base.BaseActivity;
import org.record.tiny.ui.fragment.DisplayFragment;

@SuppressWarnings("All")
public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addFragment(R.id.activity_main_layout, DisplayFragment.newInstance());
    }
}
