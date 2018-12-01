package com.example.pitts.ife.Activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.pitts.ife.R;
import com.example.pitts.ife.Tools.CommonTools;

public class TaskActivity extends BaseActivity{

    private Toolbar mToolbar;
    private FloatingActionButton mFab;
    private TextView mTaskContent;
    private LinearLayout mTopMargin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        initView();

        updateUI();

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
    }

    public void initView(){
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mFab = (FloatingActionButton) findViewById(R.id.fab);
        mTaskContent = (TextView)findViewById(R.id.task_content);
        mTopMargin = (LinearLayout) findViewById(R.id.top_margin);

        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams)mTopMargin.getLayoutParams();
        lp.height = getApplicationContext().getResources().getDimensionPixelSize(getApplicationContext().getResources().getIdentifier("status_bar_height", "dimen", "android"));
        mTopMargin.setLayoutParams(lp);

        setToolbar(mToolbar);
    }

    public String getTaskContent(){
        return null;
    }

    public void updateUI(){
        int screenWidth = CommonTools.getScreenWidth(getApplicationContext());

        SpannableString string = new SpannableString("测试文本\n"
                +"[图片]\n"
                +"测试文本\n"
                +"测试文本\n"
                +"测试文本\n"
                +"测试文本\n"
                +"测试文本\n"
                +"测试文本\n"
                +"测试文本\n"
                +"测试文本\n"
                +"测试文本\n"
                +"测试文本\n"
                +"测试文本\n"
                +"测试文本\n"
                +"测试文本\n"
                +"测试文本\n"
                +"测试文本\n"
                +"测试文本\n"
                +"测试文本\n"
                +"测试文本\n"
                +"测试文本\n"
                +"测试文本\n"
                +"测试文本\n"
                +"测试文本\n"
                +"测试文本\n"
                +"测试文本\n");
        Drawable drawable = getResources().getDrawable(R.mipmap.test_picture);
        int drawableHeight = drawable.getIntrinsicHeight()*screenWidth/drawable.getIntrinsicWidth();
        drawable.setBounds(0,0,screenWidth,drawableHeight);
        ImageSpan imageSpan = new ImageSpan(drawable);
        string.setSpan(imageSpan,5,9,Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        mTaskContent.setText(string);
    }
}
