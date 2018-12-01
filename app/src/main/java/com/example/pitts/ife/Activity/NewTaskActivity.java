package com.example.pitts.ife.Activity;

import android.support.design.widget.FloatingActionButton;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.pitts.ife.R;
import com.example.pitts.ife.Tools.CommonTools;

public class NewTaskActivity extends BaseActivity {

    private Toolbar mToolbar;
    private FloatingActionButton mFab;
    private Spinner mTaskType;
    private EditText mTaskTitle;
    private EditText mTaskContent;
    private ImageView mTaskImage;
    private LinearLayout mTaskGift;
    private LinearLayout mTaskDate;
    private LinearLayout mTaskTime;
    private TextView mGiftText;
    private TextView mDateText;
    private TextView mTimeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);

        initView();
        setToolbar(mToolbar);
        setSpinner(mTaskType,new String[]{"类别","类型一","类型二","类型三"});
    }

    public void initView(){
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        mToolbar = (Toolbar)findViewById(R.id.toolbar);
        mFab = (FloatingActionButton)findViewById(R.id.fab);
        mTaskType = (Spinner)findViewById(R.id.task_type);
    }
}
