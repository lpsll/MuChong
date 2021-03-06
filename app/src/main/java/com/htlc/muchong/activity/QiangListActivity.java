package com.htlc.muchong.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Pair;

import com.htlc.muchong.App;
import com.htlc.muchong.R;
import com.htlc.muchong.adapter.QiangListPagerAdapter;
import com.htlc.muchong.base.BaseActivity;
import com.htlc.muchong.fragment.QiangListFragment;
import com.htlc.muchong.util.DateFormat;
import com.larno.util.ToastUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by sks on 2016/5/23.
 * 限时抢购列表Activity
 */
public class QiangListActivity extends BaseActivity {
    private ViewPager mViewPager;
    private TabLayout mTabLayout;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_qiang_list;
    }

    @Override
    protected void setupView() {
        mTitleTextView.setText(R.string.first_header_qiang);
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        mViewPager.setOffscreenPageLimit(3);
        mTabLayout = (TabLayout) findViewById(R.id.tabLayout);

        initData();
    }

    @Override
    protected void initData() {
        //获取抢购日期
        App.app.appAction.qiangTimeList(new BaseActionCallbackListener<List<Pair<String, String>>>() {
            @Override
            public void onSuccess(List<Pair<String, String>> data) {
                refreshView(data);
            }

            @Override
            public void onIllegalState(String errorEvent, String message) {
                ToastUtil.showToast(App.app, message);
            }
        });

    }

    private void refreshView() {
        ArrayList<QiangListFragment> pageFragments = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();

        pageFragments.add(QiangListFragment.newInstance(DateFormat.getMonthAndDay(calendar.getTime()), QiangListFragment.TYPE_1));
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        pageFragments.add(QiangListFragment.newInstance(DateFormat.getMonthAndDay(calendar.getTime()), QiangListFragment.TYPE_2));
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        pageFragments.add(QiangListFragment.newInstance(DateFormat.getMonthAndDay(calendar.getTime()), QiangListFragment.TYPE_3));
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        pageFragments.add(QiangListFragment.newInstance(DateFormat.getMonthAndDay(calendar.getTime()), QiangListFragment.TYPE_4));


        QiangListPagerAdapter pagerAdapter = new QiangListPagerAdapter(getSupportFragmentManager(), pageFragments);
        mViewPager.setAdapter(pagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }


    /*根据返回的日期，创建对应的Fragment key为Fragment类型，value为日期*/
    private void refreshView(List<Pair<String, String>> data) {
        ArrayList<QiangListFragment> pageFragments = new ArrayList<>();
        for (Pair<String, String> pair : data) {
            pageFragments.add(QiangListFragment.newInstance(pair.second, pair.first));
        }
        QiangListPagerAdapter pagerAdapter = new QiangListPagerAdapter(getSupportFragmentManager(), pageFragments);
        mViewPager.setAdapter(pagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }
}
