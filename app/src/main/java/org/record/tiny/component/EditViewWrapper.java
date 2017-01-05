package org.record.tiny.component;

import android.app.Service;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import org.record.tiny.R;
import org.record.tiny.base.BaseViewWrapper;
import org.record.tiny.databinding.FragmentEditBinding;

@SuppressWarnings("All")
public class EditViewWrapper extends BaseViewWrapper<FragmentEditBinding> {

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

    private Context mContext;

    public EditViewWrapper(FragmentEditBinding binding) {
        super(binding);

        mContext = binding.getRoot().getContext();
        InputMethodManager im = (InputMethodManager) mContext.getSystemService(Service.INPUT_METHOD_SERVICE);

        binding.etContentLayout.requestFocus();
        InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);

        binding.activityEdit.setListener(new IMMListenerRelativeLayout.InputWindowListener() {
            @Override
            public void show() {
                onSoftKeyboardShow();
            }

            @Override
            public void hidden() {
                onSoftKeyboardHide();
            }
        });
    }

    public String getLocalInfo() {
        return binding.tvLocalEdit.getText().toString().trim();
    }

    public void setLocalInfo(String local) {
        binding.tvLocalEdit.setText(local);
    }

    public String getContextInfo() {
        return binding.etContentLayout.getText().toString().trim();
    }

    public void setContextInfo(String context) {
        binding.etContentLayout.setText(context);
    }

    public void setTitleInfo(String title) {
        binding.tvTitle.setText(title);
    }

    public String getTitleInfo() {
        return binding.tvTitle.getText().toString();
    }

    public void onSoftKeyboardHide() {
        binding.btEdit.setText(mContext.getString(R.string.preview));
        mSaveButtonState = STATE.PREVIEW;
    }

    public void onSoftKeyboardShow() {
        binding.btEdit.setText(mContext.getString(R.string.end));
        mSaveButtonState = STATE.SAVE;
    }

    public void goSave(View view) {
        if (mSaveButtonState == STATE.SAVE) {
            InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(binding.etContentLayout.getWindowToken(), 0);
            mSaveButtonState = STATE.PREVIEW;
            if (mOnClickListener != null)
                mOnClickListener.save();
        } else {
            mSaveButtonState = STATE.SAVE;

            if (mOnClickListener != null)
                mOnClickListener.preview();
        }
    }
}
