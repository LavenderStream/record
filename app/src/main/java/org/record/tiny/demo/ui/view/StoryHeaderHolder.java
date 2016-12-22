package org.record.tiny.demo.ui.view;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.holder.Holder;
import com.bumptech.glide.Glide;

import org.record.tiny.demo.model.StoryHeaderItem;

@SuppressWarnings("All")
public class StoryHeaderHolder implements Holder<StoryHeaderItem> {
    private ImageView mImageView;

    @Override
    public View createView(Context context) {
        mImageView = new ImageView(context);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mImageView.setLayoutParams(params);
        return mImageView;
    }

    @Override
    public void UpdateUI(Context context, int position, StoryHeaderItem data) {
        Glide.with(context).load(data.getImage()).centerCrop().into(mImageView);
    }
}
