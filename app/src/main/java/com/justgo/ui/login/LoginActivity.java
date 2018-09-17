package com.justgo.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.facebook.CallbackManager;
import com.facebook.Profile;
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
    String name,userid,image;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        callbackManager = CallbackManager.Factory.create();
        facebookLoginCallBack = new FacebookLoginCallBack();
        Button bt_kakao_login=findViewById(R.id.bt_kakao_login);
        Button bt_facebook_login=findViewById(R.id.bt_facebook_login);
        bt_kakao_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Session session = Session.getCurrentSession();
                session.addCallback(new SessionCallback());
                session.open(AuthType.KAKAO_TALK, LoginActivity.this);
            }
        });
        bt_facebook_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginManager loginManager = LoginManager.getInstance();
                loginManager.logInWithReadPermissions(LoginActivity.this, Arrays.asList("public_profile", "email"));
                loginManager.registerCallback(callbackManager,facebookLoginCallBack);
                name=String.valueOf(Profile.getCurrentProfile().getName());
                userid=String.valueOf(Profile.getCurrentProfile().getId());
                image=String.valueOf(Profile.getCurrentProfile().getProfilePictureUri(200,200));
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);

    }


}
