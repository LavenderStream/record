package org.record.tiny.ui.preview;

import org.record.tiny.R;
import org.record.tiny.base.MvpFragment;
import org.record.tiny.databinding.PreviewFragmentBinding;
import org.record.tiny.utils.EventIntent;

@SuppressWarnings("All")
public class PreviewFragment extends MvpFragment<PreviewFragmentBinding, PreviewPresenter> implements PreviewView {

    private String mLocalInfo;
    private String mContextInfo;
    private String mTitleInfo;

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
        binding.tvLocation.setText(mLocalInfo);
        binding.tvContext.setText(mContextInfo);
        binding.tvTitle.setText(mTitleInfo);

        binding.activityPreview.post(new Runnable() {
            @Override
            public void run() {
                mvpPresenter.save2Bitmap(binding.activityPreview.getRootView());
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
