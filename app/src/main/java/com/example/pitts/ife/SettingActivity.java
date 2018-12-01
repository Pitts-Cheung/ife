package com.example.pitts.ife;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Switch;

import com.example.pitts.ife.Activity.BaseActivity;

public class SettingActivity extends BaseActivity {

    private Toolbar mToolbar;
    private Switch mVoiceSwitch;
    private Switch mVibrationSwitch;
    private Switch mNewSwitch;
    private Switch mTaskSwitch;
    private Switch mAnswerSwitch;
    private Switch mGroupSwitch;
    private Switch mReceiverSwitch;
    private Switch mImageSwitch;
    private Switch mVideoSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        initView();
    }

    public void initView(){
        mToolbar = (Toolbar)findViewById(R.id.toolbar);
        mVoiceSwitch = (Switch)findViewById(R.id.voice_switch);
        mVibrationSwitch = (Switch)findViewById(R.id.vibration_switch);
        mNewSwitch = (Switch)findViewById(R.id.new_switch);
        mTaskSwitch = (Switch)findViewById(R.id.task_switch);
        mAnswerSwitch = (Switch)findViewById(R.id.answer_switch);
        mGroupSwitch = (Switch)findViewById(R.id.group_switch);
        mReceiverSwitch = (Switch)findViewById(R.id.receiver_switch);
        mImageSwitch = (Switch)findViewById(R.id.image_switch);
        mVideoSwitch = (Switch)findViewById(R.id.video_switch);

        setToolbar(mToolbar);
    }
}
