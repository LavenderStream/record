package org.record.tiny.component.adapter;

import android.content.Context;
import android.view.View;

import org.record.tiny.R;
import org.record.tiny.base.BaseActivity;
import org.record.tiny.component.SimpleRecyclerAdapter;
import org.record.tiny.component.SimpleRecyclerViewHolder;
import org.record.tiny.ui.fragment.EditFragment;
import org.record.tiny.ui.model.Article;
import org.record.tiny.utils.EventIntent;

import java.util.List;

@SuppressWarnings("All")
public class DisplayRecyclerAdapter extends SimpleRecyclerAdapter<Article> {

    private Context mContext;
    private List<Article> nDatas;

    public DisplayRecyclerAdapter(Context ctx, List<Article> list) {
        super(ctx, list);
        mContext = ctx;
        nDatas = list;
    }

    @Override
    public int getItemLayoutId(int viewType) {
        return R.layout.display_item;
    }

    @Override
    public void bindData(final SimpleRecyclerViewHolder holder, final int position, final Article item) {
        holder.setText(R.id.tv_title, item.getYear() + mContext.getString(R.string.s_year));
        holder.setText(R.id.tv_subtitle, item.getMonth() + mContext.getString(R.string.s_month) + " " + item.getDay()
                + mContext.getString(R.string.s_day));
        holder.getView(R.id.display_item_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((BaseActivity) mContext).addFragment(R.id.activity_main_layout, EditFragment.newInstance(), true);
                EventIntent.getInstance().put("intent_article", item).send();
            }
        });
    }
}

