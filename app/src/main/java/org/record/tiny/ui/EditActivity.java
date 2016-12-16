package org.record.tiny.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.record.tiny.R;
import org.record.tiny.base.MvpActivity;
import org.record.tiny.component.CircleButton;
import org.record.tiny.component.PicturePreviewWrapper;
import org.record.tiny.ui.view.EditPresenter;
import org.record.tiny.ui.view.EditView;
import org.record.tiny.utils.LogUtils;

import butterknife.Bind;
import butterknife.OnClick;

@SuppressWarnings("All")
public class EditActivity extends MvpActivity<EditPresenter> implements EditView {

    @Bind(R.id.tv_local_edit)
    EditText mLocalEditText;
    @Bind(R.id.tv_title)
    TextView mTitleTextView;
    @Bind(R.id.bt_edit)
    CircleButton mSaveButton;
    @Bind(R.id.et_content_layout)
    EditText mContentLayout;
    @Bind(R.id.activity_edit)
    RelativeLayout mlayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        mvpPresenter.start();
    }

    @Override
    protected EditPresenter createPresenter() {
        return new EditPresenter(this);
    }

    @OnClick(R.id.bt_edit)
    void goShared() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive()) {
            imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
        }

        PicturePreviewWrapper picturePreviewWrapper = new PicturePreviewWrapper(this,
                mTitleTextView.getText().toString(), mContentLayout.getText().toString(), mLocalEditText.getText().toString());

        mlayout.removeAllViews();
        View view = picturePreviewWrapper.getViewLayout();
        view.setVisibility(View.VISIBLE);
        mlayout.addView(view);

        mvpPresenter.save2Bitmap(mlayout.getRootView());
    }


    @Override
    public void getLocal(String local) {
        mLocalEditText.setText(local);
    }

    @Override
    public void getTitle(String title) {
        mTitleTextView.setText(title);
    }

    @Override
    public void startShare(String filePath) {
        LogUtils.d("EditActivity -> startShare: " + filePath);
        ImageView imageView = new ImageView(this);
        Picasso.with(this).load(filePath).into(imageView);
        mlayout.addView(imageView);
    }

    @Override
    public void error(int error) {

    }
}
