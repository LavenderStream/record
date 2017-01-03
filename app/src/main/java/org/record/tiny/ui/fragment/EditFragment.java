package org.record.tiny.ui.fragment;

import android.view.ViewGroup;

import com.apkfuns.logutils.LogUtils;

import org.record.tiny.R;
import org.record.tiny.base.SimpleFragment;
import org.record.tiny.component.EditViewWrapper;

@SuppressWarnings("All")
public class EditFragment extends SimpleFragment<EditPresenter> implements EditView, EditViewWrapper.OnClickListener {
    private EditViewWrapper mEditViewWrapper;

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
        // 并不保证父类的rootview 为 ViewGroup 还要看具体布
        mEditViewWrapper = new EditViewWrapper((ViewGroup) mRootView);
        mvpPresenter.start();
    }

    @Override
    public void getLocal(String local) {
        mEditViewWrapper.setLocalInfo(local);
    }

    @Override
    public void getTitle(String title) {
        mEditViewWrapper.setTitleInfo(title);
    }

    @Override
    public void getContent(String content) {
        mEditViewWrapper.setContextInfo(content);
    }

    @Override
    public void startShare(String filePath) {
   /*     ImageView imageView = new ImageView(getActivity());
        Picasso.with(getActivity()).load(filePath).into(imageView);
        mlayout.addView(imageView);*/
    }

    @Override
    public void preview() {
    }

    @Override
    public void save() {
        LogUtils.d("EditFragment -> save: ");
        //mvpPresenter.saveArticle(mEditViewWrapper.getLocalInfo(), mEditViewWrapper.getContextInfo());
    }

    @Override
    public void error(int error) {

    }
}
