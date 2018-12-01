package com.example.pitts.ife.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;

import com.example.pitts.ife.Fragment.ChatFragment;
import com.example.pitts.ife.Fragment.HomeFragment;
import com.example.pitts.ife.Fragment.UserFragment;
import com.example.pitts.ife.R;
import com.example.pitts.ife.Tools.FABBehavior;

public class MainActivity extends BaseActivity {

    private BottomNavigationView mNavigation;
    private FloatingActionButton mFAB;

    private HomeFragment mHomeFragment;
    private ChatFragment mChatFragment;
    private UserFragment mUserFragment;
    private Fragment[] mFragments;

    private int mLastFragment;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mFAB.show();
                    if(mLastFragment != 0){
                        switchFragment(mLastFragment,0);
                        mLastFragment = 0;
                    }
                    return true;
                case R.id.navigation_chat:
                    mFAB.hide();
                    if(mLastFragment != 1){
                        switchFragment(mLastFragment,1);
                        mLastFragment = 1;
                    }
                    return true;
                case R.id.navigation_user:
                    mFAB.hide();
                    if(mLastFragment != 2){
                        switchFragment(mLastFragment,2);
                        mLastFragment = 2;
                    }
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        mNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    public void initView(){
        mNavigation = (BottomNavigationView) findViewById(R.id.navigation);
        mFAB = (FloatingActionButton) findViewById(R.id.fab);
        initFragment(mLastFragment);
    }

    private void initFragment(int lastFragment){
        mHomeFragment = new HomeFragment();
        mChatFragment = new ChatFragment();
        mUserFragment = new UserFragment();
        mFragments = new Fragment[]{mHomeFragment,mChatFragment,mUserFragment};
        mNavigation.setSelectedItemId(lastFragment);
        getSupportFragmentManager().beginTransaction().replace(R.id.main_view,mFragments[lastFragment]).show(mFragments[lastFragment]).commit();
        mFAB.setImageResource(R.drawable.add_icon);
        mFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,NewTaskActivity.class));
            }
        });
    }

    private void switchFragment(int lastFragment,int index){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.hide(mFragments[lastFragment]);
        if(mFragments[index].isAdded()==false)
        {
            transaction.add(R.id.main_view,mFragments[index]);
        }
        transaction.show(mFragments[index]).commitAllowingStateLoss();
    }

    public void setFABOnClickListener(View.OnClickListener listener){
        mFAB.setOnClickListener(listener);
    }
}
