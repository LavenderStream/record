package org.record.tiny.component;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import org.record.tiny.R;

import butterknife.Bind;
import butterknife.ButterKnife;

@SuppressWarnings("All")
public class PicturePreviewWrapper {

    @Bind(R.id.tv_location)
    TextView mLocationTextView;
    @Bind(R.id.tv_context)
    VerticalTextView mContextTextView;
    @Bind(R.id.tv_title)
    TextView mTitleTextView;
    private String mTitle;
    private String mLocation;
    private String mContext;
    private View mViewLayout;

    public PicturePreviewWrapper(Context context, String title, String content, String location) {
        mViewLayout = View.inflate(context, R.layout.activity_preview, null);
        ButterKnife.bind(this, mViewLayout);

        mContext = content;
        mLocation = location;
        mTitle = title;
    }

    public View getViewLayout() {
        mTitleTextView.setText(mTitle.replace(" ", "\n\n"));
        mLocationTextView.setText("于\n\n" + mLocation);
        mContextTextView.setText(mContext);

        return mViewLayout;
    }
}
