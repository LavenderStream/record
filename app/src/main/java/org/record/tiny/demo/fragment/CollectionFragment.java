package org.record.tiny.demo.fragment;

import org.record.tiny.base.BasePresenter;
import org.record.tiny.base.SimpleFragment;

import static org.record.tiny.R.layout.fragment_collection;

public class CollectionFragment extends SimpleFragment {

    public CollectionFragment() {
    }

    public static CollectionFragment newInstance() {
        return new CollectionFragment();
    }

    @Override
    protected int createViewLayoutId() {
        return fragment_collection;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }
}
