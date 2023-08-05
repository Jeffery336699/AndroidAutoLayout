package com.zhy.autolayout.attr;

import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

/**
 * Created by zhy on 15/12/4.
 */
public class TextSizeAttr extends AutoAttr
{

    public TextSizeAttr(int pxVal, int baseWidth, int baseHeight)
    {
        super(pxVal, baseWidth, baseHeight);
    }

    @Override
    protected int attrVal()
    {
        return Attrs.TEXTSIZE;
    }

    @Override
    protected boolean defaultBaseWidth()
    {
        return false;
    }

    @Override
    protected void execute(View view, int val)
    {
        if (!(view instanceof TextView))
            return;
        /**
         * 设置了setIncludeFontPadding虽然TextView自带的上下padding去除了,但也仅仅是视觉上(存在背景的情况较明显)
         * 还是总体View占据着茅坑,意义不是太大,可以参考TestActivity中的布局,有对setIncludeFontPadding设置与否的对比
         */
        ((TextView) view).setIncludeFontPadding(false);
        ((TextView) view).setTextSize(TypedValue.COMPLEX_UNIT_PX, val);
    }

    public static TextSizeAttr generate(int val, int baseFlag)
    {
        TextSizeAttr attr = null;
        switch (baseFlag)
        {
            case AutoAttr.BASE_WIDTH:
                attr = new TextSizeAttr(val, Attrs.TEXTSIZE, 0);
                break;
            case AutoAttr.BASE_HEIGHT:
                attr = new TextSizeAttr(val, 0, Attrs.TEXTSIZE);
                break;
            case AutoAttr.BASE_DEFAULT:
                attr = new TextSizeAttr(val, 0, 0);
                break;
        }
        return attr;
    }


}
