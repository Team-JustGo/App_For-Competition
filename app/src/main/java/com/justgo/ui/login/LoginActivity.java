package com.justgo.ui.login;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.login.LoginManager;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import com.justgo.R;
import com.justgo.Util.FacebookLoginCallBack;
import com.justgo.Util.SessionCallback;
import com.justgo.ui.main.MainActivity;
import com.kakao.auth.AuthType;
import com.kakao.auth.Session;

import java.security.MessageDigest;
import java.util.Arrays;
import java.util.List;

import static com.justgo.Util.PrefManagerKt.saveToken;

public class LoginActivity extends AppCompatActivity {
    private FacebookLoginCallBack facebookLoginCallBack;
    private CallbackManager callbackManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getAppKeyHash();
        callbackManager = CallbackManager.Factory.create();
        facebookLoginCallBack = new FacebookLoginCallBack(this);
        LinearLayout layout_kakao_button = findViewById(R.id.login_kakaoLogin_btn);
        LinearLayout layout_facebook_button = findViewById(R.id.login_fbLogin_btn);
        layout_kakao_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), MainActivity.class));
                finish();
            }
        });
        layout_facebook_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginManager loginManager = LoginManager.getInstance();
                loginManager.logInWithReadPermissions(LoginActivity.this, Arrays.asList("public_profile", "email"));
                loginManager.registerCallback(callbackManager, facebookLoginCallBack);
            }
        });
        PermissionListener permissionListener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {

            }

            @Override
            public void onPermissionDenied(List<String> deniedPermissions) {

            }
        };
        TedPermission.with(getBaseContext())
                .setPermissionListener(permissionListener)
                .setRationaleMessage("서비스를 이용하기 위해서 GPS 권한이 필요합니다.")
                .setPermissions(Manifest.permission.ACCESS_FINE_LOCATION)
                .check();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void getAppKeyHash() {
        try {
            PackageInfo info = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md;
                md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String something = new String(Base64.encode(md.digest(), 0));
                Log.d("Hash key", something);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            Log.e("name not found", e.toString());
        }
    }
}
