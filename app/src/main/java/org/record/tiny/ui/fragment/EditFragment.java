package org.record.tiny.ui.fragment;

import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.record.tiny.R;
import org.record.tiny.base.SimpleFragment;
import org.record.tiny.component.CircleButton;

import butterknife.BindView;
import butterknife.OnClick;

@SuppressWarnings("All")
public class EditFragment extends SimpleFragment<EditPresenter> implements EditView {

    @BindView(R.id.tv_local_edit)
    EditText mLocalEditText;
    @BindView(R.id.tv_title)
    TextView mTitleTextView;
    @BindView(R.id.bt_edit)
    CircleButton mSaveButton;
    @BindView(R.id.et_content_layout)
    EditText mContentLayout;
    @BindView(R.id.activity_edit)
    RelativeLayout mlayout;

    public EditFragment() {
    }

    public static EditFragment newInstance() {
        EditFragment fragment = new EditFragment();
        return fragment;
    }

    @Override
    protected EditPresenter createPresenter() {
        return new EditPresenter(this);
    }

    @Override
    protected int createViewLayoutId() {
        return R.layout.fragment_edit;
    }

    @Override
    protected void onCreate() {
        mvpPresenter.start();
    }

    @OnClick(R.id.bt_edit)
    void goSave() {
        mvpPresenter.saveArticle(mLocalEditText.getText().toString(), mContentLayout.getText().toString());
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
    public void getContent(String content) {
        mContentLayout.setText(content);
    }

    @Override
    public void startShare(String filePath) {
   /*     ImageView imageView = new ImageView(getActivity());
        Picasso.with(getActivity()).load(filePath).into(imageView);
        mlayout.addView(imageView);*/
    }

    @Override
    public void error(int error) {

    }
}
