package com.example.pitts.ife.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pitts.ife.Activity.NewTaskActivity;
import com.example.pitts.ife.R;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private TabLayout mTab;
    private ViewPager mTabContent;

    private List<String> mTabIndicators;
    private List<Fragment> mTabcontents;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_home,container,false);

        mTab = v.findViewById(R.id.tab);
        mTabContent = v.findViewById(R.id.tab_content);

        initTab();
        initTabContent();

        return v;
    }

    public void initTab(){
        mTab.setTabTextColors(ContextCompat.getColor(getContext(), R.color.darkGrey), ContextCompat.getColor(getContext(), R.color.colorPrimary));
        mTab.setSelectedTabIndicatorColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
        mTab.setupWithViewPager(mTabContent);
    }

    class ContentPagerAdapter extends FragmentPagerAdapter {
        private List<String> titleList;
        private List<Fragment> fragmentList;

        public ContentPagerAdapter(FragmentManager fm, List<String> titleList, List<Fragment> fragmentList) {
            super(fm);
            this.titleList = titleList;
            this.fragmentList = fragmentList;
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titleList.get(position);
        }
    }

    public void initTabContent(){
        mTabIndicators = new ArrayList<String>();
        mTabIndicators.add("任务");
        mTabIndicators.add("问题");
        mTabIndicators.add("组队");

        mTabcontents = new ArrayList<Fragment>();
        mTabcontents.add(new TaskFragment());
        mTabcontents.add(new QuestionFragment());
        mTabcontents.add(new GroupFragment());

        ContentPagerAdapter contentAdapter = new ContentPagerAdapter(getActivity().getSupportFragmentManager(), mTabIndicators, mTabcontents);
        mTabContent.setAdapter(contentAdapter);
        mTabContent.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {

            }

            @Override
            public void onPageScrollStateChanged(int i) {
                switch (i) {
                    case 0:
                        getActivity().findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                startActivity(new Intent(getActivity(),NewTaskActivity.class));
                            }
                        });
                        return;
                    case 1:
                        return;
                    case 2:
                        return;
                }
            }
        });
        mTab.setTabsFromPagerAdapter(contentAdapter);
    }
}
