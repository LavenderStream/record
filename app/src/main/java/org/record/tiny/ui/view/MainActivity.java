package org.record.tiny.ui.view;

import android.os.Bundle;
import android.widget.Button;

import com.apkfuns.logutils.LogUtils;

import org.record.tiny.R;
import org.record.tiny.base.BaseActivity;
import org.record.tiny.utils.RxIntent;

import java.util.HashMap;

import butterknife.BindView;
import io.reactivex.functions.Consumer;

@SuppressWarnings("All")
public class MainActivity extends BaseActivity {
    @BindView(R.id.but)
    Button mbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //addFragment(R.id.activity_main_layout, DisplayFragment.newInstance());

        RxIntent.getInstance().subscribe(new Consumer<HashMap>() {
            @Override
            public void accept(HashMap hashMap) throws Exception {
                LogUtils.d("MainActivity -> accept: " + hashMap);
            }
        });
    }
}
