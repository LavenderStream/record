package org.record.tiny.component;

import android.app.Service;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.record.tiny.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

@SuppressWarnings("All")
public class EditViewWrapper implements SoftKeyboard.SoftKeyboardChanged {

    enum STATE {
        SAVE,
        PREVIEW
    }

    public interface OnClickListener {
        void preview();

        void save();
    }

    OnClickListener mOnClickListener = null;

    public void setOnClickListening(OnClickListener l) {
        mOnClickListener = l;
    }

    private STATE mSaveButtonState = STATE.SAVE;

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
    private Unbinder unbinder;
    private SoftKeyboard mSoftKeyboard;

    public EditViewWrapper(ViewGroup rootView) {
        unbinder = ButterKnife.bind(this, rootView);
        InputMethodManager im = (InputMethodManager) rootView.getContext().getSystemService(Service.INPUT_METHOD_SERVICE);

        mSoftKeyboard = new SoftKeyboard(rootView, im);
        mSoftKeyboard.setSoftKeyboardCallback(this);
        mSoftKeyboard.openSoftKeyboard();
    }

    public String getLocalInfo() {
        return mLocalEditText.getText().toString().trim();
    }

    public void setLocalInfo(String local) {
        mLocalEditText.setText(local);
    }

    public String getContextInfo() {
        return mContentLayout.getText().toString().trim();
    }

    public void setContextInfo(String context) {
        mContentLayout.setText(context);
    }

    public void setTitleInfo(String title) {
        mTitleTextView.setText(title);
    }

    public void hideKeyBorard() {
        mSoftKeyboard.closeSoftKeyboard();
    }

    @Override
    public void onSoftKeyboardHide() {
        mSaveButton.setText("看");
        mSaveButtonState = STATE.PREVIEW;
    }

    @Override
    public void onSoftKeyboardShow() {
        mSaveButton.setText("存");
        mSaveButtonState = STATE.SAVE;
    }

    @OnClick(R.id.bt_edit)
    void goSave() {
        if (mSaveButtonState == STATE.SAVE) {
            mSoftKeyboard.closeSoftKeyboard();
            mSaveButtonState = STATE.PREVIEW;
            if (mOnClickListener != null)
                mOnClickListener.save();
        } else {
            mSaveButtonState = STATE.SAVE;

            if (mOnClickListener != null)
                mOnClickListener.preview();
        }
    }

    public void detachView() {
        if (unbinder != null) {
            unbinder.unbind();
        }
        mSoftKeyboard.unRegisterSoftKeyboardCallback();
    }
}
