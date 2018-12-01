package com.example.pitts.ife.Activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.pitts.ife.Bean.UserJson;
import com.example.pitts.ife.R;
import com.example.pitts.ife.Tools.HTTPRequest;
import com.google.gson.Gson;

import java.io.IOException;

public class LoginActivity extends BaseActivity {

    private static final String TAG = "LoginActivity";

    private Button mRegisterButton;
    private EditText mLoginStunoEditText;
    private EditText mLoginPasswordEditText;
    private Button mLoginButton;
    private ProgressBar mLoginProgress;
    private LoginTask mAuthTask = null;
    private SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        mSharedPreferences = this.getSharedPreferences("userInfo", MODE_PRIVATE);
        if(mSharedPreferences.getBoolean("ISLOADED",false)){
            Intent i = new Intent(this,MainActivity.class);
            startActivity(i);
            this.finish();
        }

        initView();

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login();
            }
        });
    }

    public void initView(){
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        mRegisterButton = (Button)findViewById(R.id.register_button);
        mLoginStunoEditText = (EditText)findViewById(R.id.login_stuno_input);
        mLoginPasswordEditText = (EditText)findViewById(R.id.login_password_input);
        mLoginButton = (Button)findViewById(R.id.login_button);
        mLoginProgress = (ProgressBar)findViewById(R.id.login_progress);
    }

    public void Login(){
        if(mAuthTask != null){
            return;
        }

        mLoginStunoEditText.setError(null);
        mLoginPasswordEditText.setError(null);

        String stuno = mLoginStunoEditText.getText().toString();
        String password = mLoginPasswordEditText.getText().toString();

        boolean cancel = false;
        View focusView = null;

        if (TextUtils.isEmpty(stuno)) {
            mLoginStunoEditText.setError("请输入学号");
            focusView = mLoginStunoEditText;
            cancel = true;
        }

        if (TextUtils.isEmpty(password)) {
            mLoginPasswordEditText.setError("请输入密码");
            focusView = mLoginPasswordEditText;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true);
            mAuthTask = new LoginTask(stuno, password);
            mAuthTask.execute((Void) null);
        }
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginProgress.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginButton.setText(show ? " " : "登录");
            mLoginProgress.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginProgress.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mLoginProgress.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginButton.setText(show ? " " : "登录");
        }
    }

    public class LoginTask extends AsyncTask<Void,Void,Integer> {
        private String mStuno;
        private String mPassword;
        private Long mId;

        public LoginTask(String Stuno,String Password){
            mStuno = Stuno;
            mPassword = Password;
        }

        @Override
        protected Integer doInBackground(Void... params){
            try{
                String url = Uri.parse("http://129.204.3.173:8080/ibe_war/user/login")
                        .buildUpon()
                        .appendQueryParameter("stuno",mStuno)
                        .appendQueryParameter("password",mPassword)
                        .build().toString();
                String jsonString = HTTPRequest.GET(url);
                Gson gson = new Gson();
                UserJson userJson = gson.fromJson(jsonString,UserJson.class);
                mId = userJson.getUser().getId();

                return userJson.getCode();
            }
            catch (IOException ioe){
                Log.e(TAG,"Failed to fetch weather",ioe);
            }

            return -1;
        }

        @Override
        protected void onPostExecute(final Integer result){
            mAuthTask = null;
            showProgress(false);

            if(result.equals(0)){
                uiHandle.sendMessage(new Message());
                finish();
            } else if(result.equals(1)){
                mLoginStunoEditText.setError("该用户不存在");
                mLoginStunoEditText.requestFocus();
            } else if(result.equals(2)){
                mLoginPasswordEditText.setError("密码错误");
                mLoginPasswordEditText.requestFocus();
            } else{
                mLoginStunoEditText.setError("未知错误");
                mLoginPasswordEditText.setError("未知错误");
                mLoginPasswordEditText.requestFocus();
                mLoginStunoEditText.requestFocus();
            }
        }

        @Override
        protected void onCancelled(){
            mAuthTask = null;
            showProgress(false);
        }

        private Handler uiHandle = new Handler(){
            @Override
            public void handleMessage(Message msg){
                SharedPreferences.Editor editor = mSharedPreferences.edit();
                editor.putString("USER_ID", mId.toString());
                editor.putBoolean("ISLOADED",true);
                editor.commit();

                Intent i = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(i);
                Toast.makeText(LoginActivity.this,"登陆成功",Toast.LENGTH_SHORT).show();
            }
        };
    }
}