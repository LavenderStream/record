package org.record.tiny.ui.fragment;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.record.tiny.R;
import org.record.tiny.base.SimpleFragment;
import org.record.tiny.component.CircleButton;
import org.record.tiny.utils.callback.KeyboardChangeListener;

import butterknife.Bind;
import butterknife.OnClick;

@SuppressWarnings("All")
public class EditFragment extends SimpleFragment<EditPresenter> implements EditView {

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

    private KeyboardChangeListener mKeyboardChangeListener = null;

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
    public void onCreateView() {
        super.onCreateView();
        mKeyboardChangeListener = new KeyboardChangeListener(getActivity());
        mKeyboardChangeListener.setKeyBoardListener(new KeyboardChangeListener.KeyBoardListener() {
            @Override
            public void onKeyboardChange(boolean isShow, int keyboardHeight) {
                if (isShow && isAdded())
                    mSaveButton.setText(getString(R.string.s_ok));
                else
                    mSaveButton.setText(getString(R.string.s_save));
            }
        });
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mvpPresenter.start();
    }

    @OnClick(R.id.bt_edit)
    void goSave() {
        if (mSaveButton.getText().equals(getString(R.string.s_save))) {
            mvpPresenter.saveArticle(mLocalEditText.getText().toString(), mContentLayout.getText().toString());
            mActivity.addFragment(R.id.activity_main_layout, DisplayFragment.newInstance());
        } else {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getActivity().getWindow().getDecorView().getWindowToken(), 0);
        }
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
    public void onDestroy() {
        super.onDestroy();
        mKeyboardChangeListener.destroy();
        mKeyboardChangeListener = null;
    }

    @Override
    public void error(int error) {
    }
}
