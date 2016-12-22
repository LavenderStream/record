package org.record.tiny.component.theme;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import org.record.tiny.R;


public class ThemeTextView extends TextView implements ChangeTheme {

    public ThemeTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        setBackgroundColor(context.getResources().getColor(R.color.white));
        setTextColor(context.getResources().getColor(R.color.black));
    }

    public ThemeTextView(Context context) {
        this(context, null);
    }

    public ThemeTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    @Override
    public void themeChanged(Model themeMode) {
        if (themeMode == Model.NIGHT) {
            //setBackgroundColor(getContext().getResources().getColor(R.color.black));
            setTextColor(getContext().getResources().getColor(R.color.white));

            ObjectAnimator objectAnimator = ObjectAnimator.ofInt(this, "Background", R.color.black);
            objectAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    setBackgroundColor((Integer) animation.getAnimatedValue());
                }
            });
            objectAnimator.setDuration(2000);
            objectAnimator.start();
        } else {
            setBackgroundColor(getContext().getResources().getColor(R.color.white));
            setTextColor(getContext().getResources().getColor(R.color.black));
        }
    }
}
