package com.justgo.Util;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;

import com.justgo.Connecter.API;
import com.justgo.Model.LoginResponseModel;
import com.kakao.auth.ISessionCallback;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeResponseCallback;
import com.kakao.usermgmt.response.model.UserProfile;
import com.kakao.util.exception.KakaoException;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SessionCallback implements ISessionCallback {
String uid;
String nickname;
    // 로그인 성공
    @Override
    public void onSessionOpened() {
        requestMe();
    }
    // 로그인 실패
    @Override
    public void onSessionOpenFailed(KakaoException exception) {
        Log.e("SessionCallback :: ", "onSessionOpenFailed : " + exception.getMessage());
    }
    // 사용자 정보 요청
    public void requestMe() {
        // 사용자정보 요청 결과에 대한 Callback
        UserManagement.getInstance().requestMe(new MeResponseCallback() {
            // 세션 오픈 실패. 세션이 삭제된 경우,
            @Override
            public void onSessionClosed(ErrorResult errorResult) {
                Log.e("SessionCallback :: ", "onSessionClosed : " + errorResult.getErrorMessage());
            }
            // 회원이 아닌 경우,
            @Override
            public void onNotSignedUp() {
                Log.e("SessionCallback :: ", "onNotSignedUp");
            }
            // 사용자정보 요청에 성공한 경우,
            @Override
            public void onSuccess(UserProfile userProfile) {
                Log.e("SessionCallback :: ", "onSuccess");
                nickname = userProfile.getNickname();
                uid= userProfile.getUUID();
                Intent intent=new Intent(String.valueOf(this));
                intent.putExtra("nickname",nickname);
                intent.putExtra("uuid",uid);
                Log.e("Profile : ", nickname + "");
                Log.e("Profile : ", uid + "");
                post();
            }
            // 사용자 정보 요청 실패
            @Override
            public void onFailure(ErrorResult errorResult) {
                Log.e("SessionCallback :: ", "onFailure : " + errorResult.getErrorMessage());
            }
        });
    }
    public void post() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        final API retrofit = new Retrofit.Builder()
                .baseUrl("http://ec2-52-79-240-33.ap-northeast-2.compute.amazonaws.com:7777/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
                .create(API.class);

        Call<LoginResponseModel> call = retrofit.post_user(uid);
        call.enqueue(new Callback<LoginResponseModel>() {
            @Override
            public void onResponse(@NonNull Call<LoginResponseModel> call, @NonNull Response<LoginResponseModel> response) {
                Log.e("KIMTAEYOUNGBUNGSIN", "val " + response.code());

                Log.e("HI", "val" + response.toString());
                LoginResponseModel repo = response.body();
                Log.e("repo", "val" + repo);
                if (response.code() == 418) {
                    retrofit.auth_user(uid, nickname).enqueue(new Callback<LoginResponseModel>() {
                        @Override
                        public void onResponse(Call<LoginResponseModel> call, Response<LoginResponseModel> response) {

                        }

                        @Override
                        public void onFailure(Call<LoginResponseModel> call, Throwable t) {

                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<LoginResponseModel> call, Throwable t) {
                Log.e("반성해라", "네");
            }
        });
    }
}