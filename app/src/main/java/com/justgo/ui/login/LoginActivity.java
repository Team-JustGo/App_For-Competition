package com.justgo.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import com.facebook.CallbackManager;
import com.facebook.login.LoginManager;
import com.justgo.R;
import com.justgo.Util.FacebookLoginCallBack;
import com.justgo.Util.SessionCallback;
import com.kakao.auth.AuthType;
import com.kakao.auth.Session;

import java.util.Arrays;

public class LoginActivity extends AppCompatActivity {
    private FacebookLoginCallBack facebookLoginCallBack;
    private CallbackManager callbackManager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        callbackManager = CallbackManager.Factory.create();
        facebookLoginCallBack = new FacebookLoginCallBack();
        LinearLayout layout_kakao_button=findViewById(R.id.login_kakaoLogin_btn);
        LinearLayout layout_facebook_button=findViewById(R.id.login_fbLogin_btn);
        layout_kakao_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Session session = Session.getCurrentSession();
                session.addCallback(new SessionCallback());
                session.open(AuthType.KAKAO_TALK, LoginActivity.this);
            }
        });
        layout_facebook_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginManager loginManager = LoginManager.getInstance();
                loginManager.logInWithReadPermissions(LoginActivity.this, Arrays.asList("public_profile", "email"));
                loginManager.registerCallback(callbackManager,facebookLoginCallBack);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }


}
