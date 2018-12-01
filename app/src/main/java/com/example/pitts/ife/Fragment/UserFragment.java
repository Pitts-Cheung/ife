package com.example.pitts.ife.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.pitts.ife.Activity.UserActivity;
import com.example.pitts.ife.R;
import com.example.pitts.ife.SettingActivity;

public class UserFragment extends Fragment {

    private LinearLayout UserHome;
    private ImageView UserIcon;
    private TextView UserName;
    private Button UserFollowed;
    private Button UserFollowMe;
    private LinearLayout UserInfoEdit;
    private LinearLayout UserTokenTask;
    private LinearLayout UserAnswer;
    private LinearLayout Payment;
    private LinearLayout Setting;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_user,container,false);

        UserHome = (LinearLayout) v.findViewById(R.id.user_home);
        UserIcon = (ImageView)v.findViewById(R.id.user_icon);
        UserName = (TextView)v.findViewById(R.id.user_name);
        UserFollowed = (Button)v.findViewById(R.id.user_followed);
        UserFollowMe = (Button)v.findViewById(R.id.user_follow_me);
        UserInfoEdit = (LinearLayout)v.findViewById(R.id.user_info_edit);
        UserTokenTask = (LinearLayout)v.findViewById(R.id.user_token_task);
        UserAnswer = (LinearLayout)v.findViewById(R.id.user_answer);
        Payment = (LinearLayout)v.findViewById(R.id.payment);
        Setting = (LinearLayout)v.findViewById(R.id.setting);

        UserHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().startActivity(new Intent(getActivity(),UserActivity.class));
            }
        });

        Setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().startActivity(new Intent(getActivity(),SettingActivity.class));
            }
        });

        return v;
    }
}
