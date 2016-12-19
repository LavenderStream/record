package org.record.tiny.component.adapter;

import android.content.Context;
import android.view.View;

import org.record.tiny.R;
import org.record.tiny.component.SimpleRecyclerAdapter;
import org.record.tiny.component.SimpleRecyclerViewHolder;
import org.record.tiny.ui.model.Article;

import java.util.List;

@SuppressWarnings("All")
public class DisplayRecyclerAdapter extends SimpleRecyclerAdapter<Article> {

    OnItemListener mOnItemListener = null;
    private Context mContext;
    private List<Article> nDatas;

    public DisplayRecyclerAdapter(Context ctx, List<Article> list) {
        super(ctx, list);
        mContext = ctx;
        nDatas = list;
    }

    public void setOnItemListening(OnItemListener l) {
        mOnItemListener = l;
    }

    @Override
    public int getItemLayoutId(int viewType) {
        return R.layout.display_item;
    }

    @Override
    public void bindData(final SimpleRecyclerViewHolder holder, final int position, Article item) {
        holder.setText(R.id.tv_title, item.getYear());
        holder.setText(R.id.tv_subtitle, item.getMonth() + " " + item.getDay());
        holder.getView(R.id.display_item_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemListener != null)
                    mOnItemListener.onItemClick(position);
            }
        });
    }

    public interface OnItemListener {
        void onItemClick(int postion);
    }
}

