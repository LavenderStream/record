package org.record.tiny.demo.ui.view;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.holder.Holder;
import com.bumptech.glide.Glide;

import org.record.tiny.demo.favorite.FavoriteActivity;
import org.record.tiny.demo.model.StoryHeaderItem;

@SuppressWarnings("All")
public class StoryHeaderHolder implements Holder<StoryHeaderItem> {
    private ImageView mImageView;

    @Override
    public View createView(Context context) {
        mImageView = new ImageView(context);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mImageView.setLayoutParams(params);
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.getContext().startActivity(new Intent(v.getContext(), FavoriteActivity.class));
            }
        });
        return mImageView;
    }

    @Override
    public void UpdateUI(Context context, int position, StoryHeaderItem data) {
        Glide.with(context).load(data.getImage()).centerCrop().into(mImageView);
    }
}
