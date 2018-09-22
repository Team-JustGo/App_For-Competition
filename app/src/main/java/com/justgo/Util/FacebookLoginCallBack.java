package com.justgo.Util;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;

import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.justgo.Connecter.API;
import com.justgo.Model.LoginResponseModel;

import org.json.JSONObject;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class FacebookLoginCallBack implements FacebookCallback<LoginResult> {
    String uid;
    String name;

    // 로그인 성공 시 호출 됩니다. Access Token 발급 성공.
    @Override
    public void onSuccess(LoginResult loginResult) {
        Log.d("getAccessToken()", String.valueOf(loginResult.getAccessToken()));
        Log.d("getId()", String.valueOf(Profile.getCurrentProfile().getId()));
        Log.d("getName()", String.valueOf(Profile.getCurrentProfile().getName())); // 이름
        Log.d("getProfilePictureUri", String.valueOf(Profile.getCurrentProfile().getProfilePictureUri(200, 200)));//프로필 사진
        uid = Profile.getCurrentProfile().getId();
        name = Profile.getCurrentProfile().getName();
        requestMe(loginResult);
        post();
    }

    // 로그인 창을 닫을 경우, 호출됩니다.
    @Override
    public void onCancel() {
        Log.e("Callback :: ", "onCancel");
    }

    // 로그인 실패 시에 호출됩니다.
    @Override
    public void onError(FacebookException error) {
        Log.e("Callback :: ", "onError : " + error.getMessage());
    }


    // 사용자 정보 요청
    public void requestMe(LoginResult loginResult) {
        GraphRequest graphRequest = GraphRequest.newMeRequest(loginResult.getAccessToken(),
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {

                    }
                });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,image");
        graphRequest.setParameters(parameters);
        graphRequest.executeAsync();
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
                    retrofit.auth_user(uid, name).enqueue(new Callback<LoginResponseModel>() {
                        @Override
                        public void onResponse(Call<LoginResponseModel> call, Response<LoginResponseModel> response) {
                            Log.d("전송성공", "ok");
                        }

                        @Override
                        public void onFailure(Call<LoginResponseModel> call, Throwable t) {
                            Log.d("전송성공", "fail");
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
