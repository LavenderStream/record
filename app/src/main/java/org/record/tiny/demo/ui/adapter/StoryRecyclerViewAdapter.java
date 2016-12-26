package org.record.tiny.demo.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;

import com.bumptech.glide.Glide;

import org.record.tiny.R;
import org.record.tiny.component.GlideCircleTransformation;
import org.record.tiny.component.SimpleRecyclerAdapter;
import org.record.tiny.component.SimpleRecyclerViewHolder;
import org.record.tiny.demo.follow.FollowActivity;
import org.record.tiny.demo.model.StoryItem;
import org.record.tiny.utils.EventIntent;

import java.util.List;

@SuppressWarnings("ALl")
public class StoryRecyclerViewAdapter extends SimpleRecyclerAdapter<StoryItem> {

    public static final String S_DEFAULT_IMAGE = "http://4493bz.1985t.com/uploads/allimg/150127/4-15012G52133.jpg";
    private Context mContext;

    public StoryRecyclerViewAdapter(Context ctx, List<StoryItem> list) {
        super(ctx, list);
        mContext = ctx;
    }

    @Override
    public int getItemLayoutId(int viewType) {
        return R.layout.fragment_tab_list_item;
    }

    @Override
    public void bindData(SimpleRecyclerViewHolder holder, int position, final StoryItem item) {

        Glide.with(mContext).load(item.getUserImage()).transform(new GlideCircleTransformation(mContext)).into(holder.getImageView(R.id.essence_head_img));

        holder.getTextView(R.id.essence_author_name).setText(item.getNickName());
        holder.getTextView(R.id.tv_essence_likecount).setText(item.getLike() + "");
        holder.getTextView(R.id.essence_commentcount).setText(item.getComment() + "");
        holder.getTextView(R.id.essence_booktitle).setText(item.getTitle());

        if (TextUtils.isEmpty(item.getImage())) {
            Glide.with(mContext).load(S_DEFAULT_IMAGE).centerCrop().into(holder.getImageView(R.id.essence_booktcontent_img));
        } else {
            Glide.with(mContext).load(item.getImage()).centerCrop().into(holder.getImageView(R.id.essence_booktcontent_img));
        }

        holder.getView(R.id.essence_item_root).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventIntent.getInstance()
                        .put("web_url", item.getImage())
                        .put("article_id", item.getArticleId() + "")
                        .send();
                mContext.startActivity(new Intent(mContext, FollowActivity.class));
            }
        });
    }
}
