package org.tiny.lib.core;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.SparseArray;

/**
 * 首页为tab标签栏切换fragment的布局的封装
 * 1、mainactivity添加fragment
 * 2、黑色模式切换
 * <p>
 * wrapper中存在两个监听
 * OnTabSelectListener 用户点击tab标签之后就会回调，手动调用setTab方法不会回调
 * 当fragment可以成功添加到actcivity中时
 */
@SuppressWarnings("All")
public class BaseTabStyleWrapper {

    public interface OnTabSelectListener {
        void onTabSelect(int position);
    }

    // 当前fragment索引
    private int mCurrentFragmentPostion = -1;
    // 存放mainactivity持有的fragment
    private SparseArray<Fragment> mFragmentMap = new SparseArray<>();
    // 创建fragment的接口，activity实现
    private Fragments mTabFragmentInterface;
    // fragment manager
    private FragmentManager mFragmentManager;
    private int mFragmentLayoutId = -1;

    /**
     * @param tabStyle Fragments interface
     */
    public void addParams(Fragments tabStyle, FragmentManager fragmentManager, int layoutId) {
        mTabFragmentInterface = tabStyle;
        mFragmentManager = fragmentManager;
        mFragmentLayoutId = layoutId;

    }

    /**
     * 设置fragment显示，fragment不存在时会创建之后再显示
     *
     * @param position 显示位置
     * @param msg      创建fragment时传值
     */
    public void setTab(int position, Bundle msg, boolean addBackStack) {
        try {
            Fragment fragment = mFragmentMap.get(position);
            FragmentTransaction transaction = mFragmentManager.beginTransaction();
            // hide the old Fragment
            if (mCurrentFragmentPostion != -1) {
                // transaction.remove(mFragmentMap.get(mCurrentFragmentPostion));
            }

            if (fragment == null) {
                // 看这个framgent是否已经被保存过
                Fragment saveFragment = mFragmentManager.findFragmentByTag(mTabFragmentInterface
                        .getFragments()[position].getSimpleName());
                if (saveFragment != null) {
                    fragment = saveFragment;
                } else {
                    fragment = mTabFragmentInterface.getFragments()[position].newInstance();
                    fragment.setArguments(msg);
                    transaction.add(mFragmentLayoutId, fragment,
                            fragment.getClass().getSimpleName());
                    if (addBackStack) {
                        transaction.addToBackStack(fragment.getClass().getSimpleName());
                    }
                }
                // 自己的保存的framgent
                mFragmentMap.put(position, fragment);
            }

            transaction.show(fragment);
            transaction.commitAllowingStateLoss();
            mCurrentFragmentPostion = position;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置fragment显示，fragment不存在时会创建之后再显示
     *
     * @param position 显示位置
     */
    public void setTab(int position) {
        setTab(position, null, false);
    }

    public void setTab(int position, Bundle msg) {
        setTab(position, msg, false);
    }

    public void setTab(int position, boolean addBackStack) {
        setTab(position, null, addBackStack);
    }

    /**
     * 创建一个fragment但不显示，可用于加速显示
     */
    public void addTab(int position, Bundle msg, boolean addBackStack) {
        try {
            // 先在系统中查找framgnet
            FragmentTransaction transaction = mFragmentManager.beginTransaction();
            Fragment fragment = mFragmentManager.findFragmentByTag(mTabFragmentInterface
                    .getFragments()[position].getSimpleName());
            // 如果为空再创建
            if (fragment == null) {
                fragment = mTabFragmentInterface.getFragments()[position].newInstance();
                if (msg != null) {
                    fragment.setArguments(msg);
                }
                mFragmentMap.put(position, fragment);
                transaction.add(mFragmentLayoutId, fragment, fragment.getClass().getSimpleName());
                if (addBackStack) {
                    transaction.addToBackStack(fragment.getClass().getSimpleName());
                }
            }

            transaction.hide(fragment);
            transaction.commitAllowingStateLoss();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public void addTab(int postion) {
        addTab(postion, null, false);
    }

    public void addTab(int postion, boolean addBackStack) {
        addTab(postion, null, addBackStack);
    }

    public void addTab(int postion, Bundle msg) {
        addTab(postion, msg, false);
    }

    public Fragment getCurrentFragment() {
        return mFragmentMap.get(mCurrentFragmentPostion);
    }

    /**
     * 释放资源，退出后view逻辑
     */
    public void release() {
        // 清除存储fragment的map
        mFragmentMap.clear();
        mFragmentMap = null;
    }

    protected SparseArray<Fragment> getFragment() {
        return this.mFragmentMap;
    }

    public interface Fragments {
        Class<? extends Fragment>[] getFragments();
    }
}
