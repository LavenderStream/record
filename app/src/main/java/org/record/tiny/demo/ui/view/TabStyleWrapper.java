package org.record.tiny.demo.ui.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import org.record.tiny.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

@SuppressWarnings("All")
public class TabStyleWrapper {
    @Bind(R.id.bt_left_2)
    Button mButton2;
    @Bind(R.id.bt_left_1)
    Button mButton1;

    private int mCurrentFragmentPostion = -1;
    private SparseArray<Fragment> mFragmentMap = new SparseArray<>();
    private FragmentManager mFragmentManager;
    private OnTabSelectListener mOnTabSelectListener = null;
    private TabStyle mTabStyle;
    private View mFragmentLayout;
    private Bundle mInitedBundle;

    public void setOnTabSelectListener(OnTabSelectListener tabSelectListener) {
        this.mOnTabSelectListener = tabSelectListener;
    }

    public interface OnTabSelectListener {
        void onTabSelect(int position);
    }

    public interface TabStyle {
        Class<? extends Fragment>[] getFragments();
    }


    public TabStyleWrapper(TabStyle tabStyle, FragmentManager fragmentManager, View rootView) {
        this(tabStyle, fragmentManager, rootView, null);
    }

    public TabStyleWrapper(TabStyle tabStyle, FragmentManager fragmentManager, View rootView, Bundle initedBundle) {
        View tabButtonsView = LayoutInflater.from(rootView.getContext()).inflate(R.layout.tab_layout, null);
        // add tab buttons
        ViewGroup viewGroup = (ViewGroup) rootView.findViewById(R.id.ll_tab_layout);
        viewGroup.addView(tabButtonsView);
        mFragmentLayout = rootView.findViewById(R.id.fl_fragment_layout);

        mFragmentManager = fragmentManager;
        mInitedBundle = initedBundle;

        mTabStyle = tabStyle;
        ButterKnife.bind(this, rootView);
    }


    public void setTab(int position, Bundle msg) {
        try {
            Fragment fragment = mFragmentMap.get(position);
            FragmentTransaction transaction = mFragmentManager.beginTransaction();
            // hide the old Fragment
            if (mCurrentFragmentPostion != -1) {
                transaction.hide(mFragmentMap.get(mCurrentFragmentPostion));
            }
            if (fragment == null) {
                fragment = mTabStyle.getFragments()[position].newInstance();
                Bundle bundle = msg;
                fragment.setArguments(bundle);
                mFragmentMap.put(position, fragment);
                addFragment(R.id.fl_fragment_layout, fragment);
            } else {
                transaction.show(fragment);
                transaction.commit();
            }
            if (mOnTabSelectListener != null) {
                mOnTabSelectListener.onTabSelect(position);
            }
            mCurrentFragmentPostion = position;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public void setTab(int position) {
        setTab(position, null);
    }

    @OnClick({R.id.bt_left_2, R.id.bt_left_1})
    void goClick(View v) {
        switch (v.getId()) {
            case R.id.bt_left_1:
                setTab(0);
                break;
            case R.id.bt_left_2:
                setTab(1);
                break;
        }
    }

    public Fragment getCurrentFragment() {
        return mFragmentMap.get(mCurrentFragmentPostion);
    }

    private void addFragment(int containerViewId, Fragment fragment) {
        android.support.v4.app.FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.add(containerViewId, fragment, fragment.getClass().getSimpleName());
        fragmentTransaction.commitAllowingStateLoss();
    }
}
