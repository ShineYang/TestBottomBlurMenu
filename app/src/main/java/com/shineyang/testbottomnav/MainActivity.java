package com.shineyang.testbottomnav;

import android.annotation.TargetApi;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.shineyang.testbottomnav.fragment.Fragment1;
import com.shineyang.testbottomnav.fragment.Fragment2;
import com.shineyang.testbottomnav.fragment.Fragment3;
import com.shineyang.testbottomnav.fragment.Fragment4;

import eightbitlab.com.blurview.BlurView;
import eightbitlab.com.blurview.RenderScriptBlur;

public class MainActivity extends FragmentActivity implements View.OnClickListener {


    private Fragment1 fragment1 = new Fragment1();
    private Fragment2 fragment2 = new Fragment2();
    private Fragment3 fragment3 = new Fragment3();
    private Fragment4 fragment4 = new Fragment4();

    private FragmentManager fragmentManager = getSupportFragmentManager();
    private FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
    private Fragment mfrom = fragment1;// 默认显示控制第一个默认的界面 为 第一个 为后面 交换界面初始化 参数

    private ImageView iv_quick_buy;//快速购买
    private ImageView iv_map;//地铁图
    private ImageView iv_history;//购票记录
    private ImageView iv_me;//我

    private LinearLayout ly_quick_buy;//快速购买
    private LinearLayout ly_map;//地铁图
    private LinearLayout ly_history;//购票记录
    private LinearLayout ly_me;//我


    private BlurView blurView;
    final float radius = 16f;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initTabBarView();
        initBlurMenu();
        addDefaultFragment();
    }

    public void onSaveInstanceState(Bundle outState) {
        //注释掉以防view重叠
        //super.onSaveInstanceState(outState, outPersistentState);
    }

    public void initBlurMenu(){
        blurView = (BlurView) findViewById(R.id.menu_main);
        final View decorView = getWindow().getDecorView();
        //Activity's root View. Can also be root View of your layout
        final View rootView = decorView.findViewById(android.R.id.content);
        //set background, if your root layout doesn't have one
        final Drawable windowBackground = decorView.getBackground();

        blurView.setupWith(rootView)
                .windowBackground(windowBackground)
                .blurAlgorithm(new RenderScriptBlur(this, true)) //Preferable algorithm, needs RenderScript support mode enabled
                .blurRadius(radius);
    }

    /**
     * 初始化视图
     */
    public void initTabBarView() {
        iv_quick_buy = (ImageView) findViewById(R.id.iv_quick_buy);
        iv_map = (ImageView) findViewById(R.id.iv_map);
        iv_history = (ImageView) findViewById(R.id.iv_buy_history);
        iv_me = (ImageView) findViewById(R.id.iv_me);

        ly_quick_buy = (LinearLayout) findViewById(R.id.ly_btn_quick_buy);
        ly_map = (LinearLayout) findViewById(R.id.ly_btn_map);
        ly_history = (LinearLayout) findViewById(R.id.ly_btn_buy_history);
        ly_me = (LinearLayout) findViewById(R.id.ly_btn_me);

        ly_quick_buy.setOnClickListener(this);
        ly_map.setOnClickListener(this);
        ly_history.setOnClickListener(this);
        ly_me.setOnClickListener(this);
    }

    /**
     * 添加默认显示的Fragment
     */
    public void addDefaultFragment() {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.frag_content, fragment1);
        fragmentTransaction.commit();

    }


    /**
     * 控制从activity
     */
    public void switchContent(Fragment from, Fragment to) {

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        if (!to.isAdded()) { // 先判断是否被add过
            fragmentTransaction.hide(from).add(R.id.frag_content, to).commit(); // 隐藏当前的fragment，add下一个到Activity中
           // Log.i("status", "===没有添加过===");
        } else {
            fragmentTransaction.hide(from).show(to).commit(); // 隐藏当前的fragment，显示下一个
           // Log.i("status", "===添加过===");
        }
    }

    /**
     * 修改快速购票按钮样式
     */

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void changeQuickMenu() {
        iv_quick_buy.setBackground(getResources().getDrawable(R.drawable.menu_main_pressed));
        iv_map.setBackground(getResources().getDrawable(R.drawable.menu_search_unpress));
        iv_history.setBackground(getResources().getDrawable(R.drawable.menu_list_unpress));
        iv_me.setBackground(getResources().getDrawable(R.drawable.menu_user_unpress));

//        tv_quick_buy.setTextColor(getResources().getColor(R.color.selected_blue));
//        tv_map.setTextColor(getResources().getColor(R.color.grey_800));
//        tv_history.setTextColor(getResources().getColor(R.color.grey_800));
//        tv_me.setTextColor(getResources().getColor(R.color.grey_800));


    }

    /**
     * 修改地铁图按钮样式
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void changeMapMenu() {
        iv_quick_buy.setBackground(getResources().getDrawable(R.drawable.menu_main_unpress));
        iv_map.setBackground(getResources().getDrawable(R.drawable.menu_search_pressed));
        iv_history.setBackground(getResources().getDrawable(R.drawable.menu_list_unpress));
        iv_me.setBackground(getResources().getDrawable(R.drawable.menu_user_unpress));
//
//        tv_quick_buy.setTextColor(getResources().getColor(R.color.grey_800));
//        tv_map.setTextColor(getResources().getColor(R.color.selected_blue));
//        tv_history.setTextColor(getResources().getColor(R.color.grey_800));
//        tv_me.setTextColor(getResources().getColor(R.color.grey_800));
    }

    /**
     * 修改购票记录按钮样式
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void changeHistoryMenu() {
        iv_quick_buy.setBackground(getResources().getDrawable(R.drawable.menu_main_unpress));
        iv_map.setBackground(getResources().getDrawable(R.drawable.menu_search_unpress));
        iv_history.setBackground(getResources().getDrawable(R.drawable.menu_list_pressed));
        iv_me.setBackground(getResources().getDrawable(R.drawable.menu_user_unpress));

//        tv_quick_buy.setTextColor(getResources().getColor(R.color.grey_800));
//        tv_map.setTextColor(getResources().getColor(R.color.grey_800));
//        tv_history.setTextColor(getResources().getColor(R.color.selected_blue));
//        tv_me.setTextColor(getResources().getColor(R.color.grey_800));
    }

    /**
     * 修改我的按钮样式
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void changeMeMenu() {
        iv_quick_buy.setBackground(getResources().getDrawable(R.drawable.menu_main_unpress));
        iv_map.setBackground(getResources().getDrawable(R.drawable.menu_search_unpress));
        iv_history.setBackground(getResources().getDrawable(R.drawable.menu_list_unpress));
        iv_me.setBackground(getResources().getDrawable(R.drawable.menu_user_pressed));

//        tv_quick_buy.setTextColor(getResources().getColor(R.color.grey_800));
//        tv_map.setTextColor(getResources().getColor(R.color.grey_800));
//        tv_history.setTextColor(getResources().getColor(R.color.grey_800));
//        tv_me.setTextColor(getResources().getColor(R.color.selected_blue));
    }

    /**
     * 底部菜单栏点击事件
     */

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ly_btn_quick_buy:
                changeQuickMenu();
                switchContent(mfrom, fragment1);
                mfrom = fragment1;
                break;
            case R.id.ly_btn_map:
                changeMapMenu();
                switchContent(mfrom, fragment2);
                mfrom = fragment2;
                break;
            case R.id.ly_btn_buy_history:
                changeHistoryMenu();
                switchContent(mfrom, fragment3);
                mfrom = fragment3;
                break;
            case R.id.ly_btn_me:
                changeMeMenu();
                switchContent(mfrom, fragment4);
                mfrom = fragment4;
                break;
            default:
                break;

        }
    }
}
