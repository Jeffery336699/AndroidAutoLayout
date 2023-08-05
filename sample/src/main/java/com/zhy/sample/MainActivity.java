package com.zhy.sample;

import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;

import com.zhy.autolayout.AutoLayoutActivity;
import com.zhy.sample.fragment.ListFragment;
import com.zhy.sample.fragment.PayFragment;
import com.zhy.sample.fragment.RecyclerViewFragment;
import com.zhy.sample.fragment.RecyclerViewGridFragment;
import com.zhy.sample.fragment.RegisterFragment;
import com.zhy.sample.fragment.TestFragment;

import java.util.ArrayList;

public class MainActivity extends AutoLayoutActivity {

    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        setImmersionStatus();
        setContentView(R.layout.activity_main);

        initView();
        initDatas();
        // 隐藏底部导航栏,就算隐藏了,实质还是占了茅坑,这块的高度你也使用不了,针对TestFragment的效果不收影响
        /**
            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);
        */
    }

    private void setImmersionStatus() {
        if (VERSION.SDK_INT >= VERSION_CODES.KITKAT) {
            // 透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            // 透明导航栏
			// getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }

    private void initDatas() {
        ArrayList<Fragment> mList = new ArrayList<Fragment>();
        mList.add(new ListFragment());
        mList.add(new RegisterFragment());
        mList.add(new PayFragment());
        mList.add(new RecyclerViewFragment());
        mList.add(new RecyclerViewGridFragment());
        mList.add(new TestFragment());
        mViewPager.setAdapter(new MyAdapter(getSupportFragmentManager(), mList));
    }

    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.id_viewpager);
    }

    public class MyAdapter extends FragmentPagerAdapter {
        ArrayList<Fragment> tabs = null;

        public MyAdapter(FragmentManager fm, ArrayList<Fragment> tabs) {
            super(fm);
            this.tabs = tabs;
        }

        @Override
        public Fragment getItem(int pos) {
            return tabs.get(pos);
        }

        @Override
        public int getCount() {
            return tabs.size();
        }
    }

}
