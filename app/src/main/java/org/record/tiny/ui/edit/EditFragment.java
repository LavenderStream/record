package org.record.tiny.ui.edit;

import org.record.tiny.R;
import org.record.tiny.base.MvpFragment;
import org.record.tiny.component.EditViewWrapper;
import org.record.tiny.databinding.FragmentEditBinding;
import org.record.tiny.ui.preview.PreviewFragment;
import org.record.tiny.utils.EventIntent;

@SuppressWarnings("All")
public class EditFragment extends MvpFragment<FragmentEditBinding, EditPresenter> implements EditView, EditViewWrapper.OnClickListener {
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
    public void onCreateView() {
        mEditViewWrapper = new EditViewWrapper(binding);
        mEditViewWrapper.setOnClickListening(this);
        binding.setControl(mEditViewWrapper);
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
    }

    @Override
    public void preview() {
        EventIntent.getInstance()
                .put("preview_title", mEditViewWrapper.getTitleInfo())
                .put("preview_context", mEditViewWrapper.getContextInfo())
                .put("preview_local", mEditViewWrapper.getLocalInfo())
                .send();
        mvpPresenter.saveArticle(mEditViewWrapper.getLocalInfo(), mEditViewWrapper.getContextInfo());
        mActivity.addFragment(R.id.activity_main_layout, PreviewFragment.newInstance(), true);
    }

    @Override
    public void save() {
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void error(int error) {
    }
}
