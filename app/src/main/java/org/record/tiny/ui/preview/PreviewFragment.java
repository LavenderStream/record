package org.record.tiny.ui.preview;

import android.widget.RelativeLayout;
import android.widget.TextView;

import org.record.tiny.R;
import org.record.tiny.base.MvpFragment;
import org.record.tiny.component.VerticalTextView;
import org.record.tiny.utils.EventIntent;

import butterknife.BindView;

@SuppressWarnings("All")
public class PreviewFragment extends MvpFragment<PreviewPresenter> implements PreviewView {

    private String mLocalInfo;
    private String mContextInfo;
    private String mTitleInfo;

    @BindView(R.id.tv_location)
    TextView mLocalView;
    @BindView(R.id.tv_context)
    VerticalTextView mContextView;
    @BindView(R.id.tv_title)
    TextView mTitleView;
    @BindView(R.id.activity_preview)
    RelativeLayout mLayout;


    public PreviewFragment() {
        mLocalInfo = (String) EventIntent.getInstance().get("preview_local");
        mContextInfo = (String) EventIntent.getInstance().get("preview_context");
        mTitleInfo = (String) EventIntent.getInstance().get("preview_title");
    }

    public static PreviewFragment newInstance() {
        PreviewFragment fragment = new PreviewFragment();
        return fragment;
    }


    @Override
    protected int createViewLayoutId() {
        return R.layout.preview_fragment;
    }

    @Override
    protected PreviewPresenter createPresenter() {
        return new PreviewPresenter(this);
    }

    @Override
    public void onCreateView() {
        super.onCreateView();
        mLocalView.setText(mLocalInfo);
        mContextView.setText(mContextInfo);
        mTitleView.setText(mTitleInfo);

        mLayout.post(new Runnable() {
            @Override
            public void run() {
                mvpPresenter.save2Bitmap(mLayout.getRootView());
            }
        });
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void error(int error) {

    }
}
