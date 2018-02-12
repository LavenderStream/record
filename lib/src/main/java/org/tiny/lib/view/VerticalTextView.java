package org.tiny.lib.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.tiny.lib.R;


/**
 * Created by tiny on 2/12/2018
 */

public class VerticalTextView extends LinearLayout {
    private Context mContext;

    public VerticalTextView(Context context) {
        this(context, null);
    }

    public VerticalTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public VerticalTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initView();
    }

    private void initView() {
        setOrientation(LinearLayout.HORIZONTAL);
    }

    public void setText(String text) {
        String[] echoArray = text.split("\n");
        for (int i = echoArray.length - 1; i >= 0; i--) {
            TextView textView = (TextView) View.inflate(mContext, R.layout.vertical_textview, null);
            textView.setText(echoArray[i].replace(" ", "\n"));
            addView(textView, -1);
        }
    }
}
