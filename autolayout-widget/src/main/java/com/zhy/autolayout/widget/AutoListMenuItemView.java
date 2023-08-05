package com.zhy.autolayout.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.view.menu.ActionMenuItemView;
import android.support.v7.view.menu.ListMenuItemView;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.widget.TextView;

import com.zhy.autolayout.utils.AutoUtils;
import com.zhy.autolayout.utils.DimenUtils;

import java.lang.reflect.Field;

/**
 * Created by hupei on 2016/3/7 14:44.
 */
@SuppressLint("RestrictedApi")
public class AutoListMenuItemView extends ListMenuItemView {
    private static final int NO_VALID = -1;
    private static final String TAG = "AutoListMenuItemView";
    private int mMenuTextSize;

    public AutoListMenuItemView(Context context) {
        this(context, null);
    }

    public AutoListMenuItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AutoListMenuItemView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.AppCompatTheme,
                defStyle, R.style.ToolBarMenuStyle);
        int menuTextAppearance = a.getResourceId(R.styleable.AppCompatTheme_popupMenuStyle,
                R.style.ThemeOverlay_AppCompat_ActionBar);
        // TODO: 这波操作下来确实正确获取到了
        mMenuTextSize = loadTextSizeFromTextAppearance(menuTextAppearance);
        Log.i(TAG, "AutoListMenuItemView:   mMenuTextSize="+mMenuTextSize);
        a.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (!this.isInEditMode()) {
            setUpTitleTextSize();
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private int loadTextSizeFromTextAppearance(int textAppearanceResId) {
        TypedArray a = getContext().obtainStyledAttributes(textAppearanceResId,
                R.styleable.TextAppearance);
        try {
            if (!DimenUtils.isPxVal(a.peekValue(R.styleable.TextAppearance_android_textSize)))
                return NO_VALID;
            return a.getDimensionPixelSize(R.styleable.TextAppearance_android_textSize, NO_VALID);
        } finally {
            a.recycle();
        }
    }

    private void setUpTitleTextSize() {
        if (mMenuTextSize == -1) return;
        int autoTextSize = AutoUtils.getPercentHeightSize(mMenuTextSize);
        setUpTitleTextSize("mTitleView",autoTextSize);
    }

    private void setUpTitleTextSize(String name, int val) {
        try {
            //反射 Toolbar 的 TextView
            Field f = getClass().getSuperclass().getDeclaredField(name);
            f.setAccessible(true);
            TextView textView = (TextView) f.get(this);
            if (textView != null) {
                int autoTextSize = AutoUtils.getPercentHeightSize(val);
                textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, autoTextSize);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
