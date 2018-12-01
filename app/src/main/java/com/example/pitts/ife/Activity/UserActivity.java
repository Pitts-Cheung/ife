package com.example.pitts.ife.Activity;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.example.pitts.ife.Fragment.GroupFragment;
import com.example.pitts.ife.Fragment.HomeFragment;
import com.example.pitts.ife.Fragment.QuestionFragment;
import com.example.pitts.ife.Fragment.TaskFragment;
import com.example.pitts.ife.R;

import java.util.ArrayList;
import java.util.List;

public class UserActivity extends BaseActivity {

    private Toolbar mToolbar;
    private FloatingActionButton mFab;
    private AppBarLayout mAppBar;
    private LinearLayout mUserView;
    private CollapsingToolbarLayout mToolbarLayout;
    private TabLayout mTab;
    private ViewPager mTabContent;
    private LinearLayout mTopMargin;

    private List<String> mTabIndicators;
    private List<Fragment> mTabcontents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        initView();

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        mAppBar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset < -mUserView.getHeight() * 1 / 6) {
                    mFab.hide();
                    if (verticalOffset <= -mUserView.getHeight() * 2 / 3) {
//                    mToolbar.setPadding(0,0,(int)((float)mToolbar.getTitleMarginStart()*getWindowHeightAndWeightAndDensity()[2]),0);
//                    mTopMargin.setVisibility(View.VISIBLE);
                        mToolbarLayout.setTitle("用户名");
                    } else {
//                    mToolBar.setPadding(0,getActivity().getApplicationContext().getResources().getDimensionPixelSize(getActivity().getApplicationContext().getResources().getIdentifier("status_bar_height", "dimen", "android"))+(int)((float)mToolBar.getTitleMarginTop()*getWindowHeightAndWeightAndDensity()[2]),(int)((float)mToolBar.getTitleMarginStart()*getWindowHeightAndWeightAndDensity()[2]),0);
//                    mTopMargin.setVisibility(View.GONE);
                        mToolbarLayout.setTitle(" ");
                    }
                } else {
                    mFab.show();
                }
            }
        });
    }

    public void initView(){
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mFab = (FloatingActionButton) findViewById(R.id.fab);
        mAppBar = (AppBarLayout) findViewById(R.id.app_bar);
        mUserView = (LinearLayout) findViewById(R.id.user_view);
        mToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        mTab = (TabLayout) findViewById(R.id.tab);
        mTabContent = (ViewPager) findViewById(R.id.tab_content);
        mTopMargin = (LinearLayout) findViewById(R.id.top_margin);

        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams)mTopMargin.getLayoutParams();
        lp.height = getApplicationContext().getResources().getDimensionPixelSize(getApplicationContext().getResources().getIdentifier("status_bar_height", "dimen", "android"));
        mTopMargin.setLayoutParams(lp);

        initTab();
        intTabContent();

        setToolbar(mToolbar);
    }

    public void initTab(){
        mTab.setTabTextColors(ContextCompat.getColor(getApplicationContext(), R.color.darkGrey), ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary));
        mTab.setSelectedTabIndicatorColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary));
        ViewCompat.setElevation(mTab, 5);
        mTab.setupWithViewPager(mTabContent);
    }

    public void intTabContent(){
        mTabIndicators = new ArrayList<String>();
        mTabIndicators.add("任务");
        mTabIndicators.add("问题");
        mTabIndicators.add("组队");

        mTabcontents = new ArrayList<Fragment>();
        mTabcontents.add(new TaskFragment());
        mTabcontents.add(new QuestionFragment());
        mTabcontents.add(new GroupFragment());

        ContentPagerAdapter contentAdapter = new ContentPagerAdapter(getSupportFragmentManager(), mTabIndicators, mTabcontents);
        mTabContent.setAdapter(contentAdapter);
        mTab.setTabsFromPagerAdapter(contentAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_user,menu);
        return true;
    }
}
