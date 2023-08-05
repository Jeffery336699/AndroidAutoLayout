package com.zhy.autolayout;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

;

/**
 * Created by zhy on 15/11/19.
 */
public class AutoLayoutActivity extends AppCompatActivity {

    private static final String LAYOUT_LINEARLAYOUT = "LinearLayout";
    private static final String LAYOUT_FRAMELAYOUT = "FrameLayout";
    private static final String LAYOUT_RELATIVELAYOUT = "RelativeLayout";
    private static final String TAG = "AutoLayoutActivity";

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        Log.i(TAG, "onCreateView: name="+name);

        // TODO: 这里几个Auto包裹容器下去意义重大,在xml被inflate填充时内部会调用该容器的generateLayoutParams方法,
        //  同时方法给子View添加Auto系列的LayoutParams,并且解析attrs生成AutoLayoutInfo对象,所有属性相关的东西都在这里面
        //  注意整个ViewTree是走系统的迭代下去,就是嵌套层也会被设置进去(eg ViewPager-->ListView-->RelationLayout),具体看AutoLayoutHelper log日志
        View view = null;
        if (name.equals(LAYOUT_FRAMELAYOUT)) {
            view = new AutoFrameLayout(context, attrs);
        }

        if (name.equals(LAYOUT_LINEARLAYOUT)) {
            view = new AutoLinearLayout(context, attrs);
        }

        if (name.equals(LAYOUT_RELATIVELAYOUT)) {
            view = new AutoRelativeLayout(context, attrs);
        }

        if (view != null) return view;

        return super.onCreateView(name, context, attrs);
    }


}
