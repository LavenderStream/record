package org.record.tiny.component.theme;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;


public class ThemeRelativeLayout extends RelativeLayout implements ChangeTheme {
    public ThemeRelativeLayout(Context context) {
        super(context);
    }

    public ThemeRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ThemeRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void themeChanged(Model themeMode) {
        for (int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);
            if (view instanceof ChangeTheme) {
                ((ChangeTheme) view).themeChanged(themeMode);
            }
        }

      /*  if (themeMode == Model.NIGHT) {
            setBackgroundColor(getContext().getResources().getColor(R.color.black));
        } else {
            setBackgroundColor(getContext().getResources().getColor(R.color.white));
        }*/
    }
}
