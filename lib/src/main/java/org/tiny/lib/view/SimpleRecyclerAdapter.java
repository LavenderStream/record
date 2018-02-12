package org.tiny.lib.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tiny on 2/11/2018
 */

abstract public class SimpleRecyclerAdapter<T> extends RecyclerView.Adapter<SimpleRecyclerViewHolder> {
    private final List<T> mData;
    private final Context mContext;
    private LayoutInflater mInflater;

    public SimpleRecyclerAdapter(Context ctx, List<T> list) {
        mData = (list != null) ? list : new ArrayList<T>();
        mContext = ctx;
        mInflater = LayoutInflater.from(ctx);
    }

    @Override
    public SimpleRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(getItemLayoutId(viewType), parent, false);
        return new SimpleRecyclerViewHolder(mContext, view);
    }

    @Override
    public void onBindViewHolder(SimpleRecyclerViewHolder holder, int position) {
        bindData(holder, position, mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    abstract protected int getItemLayoutId(int viewType);

    abstract protected void bindData(SimpleRecyclerViewHolder holder, int position, T item);
}
