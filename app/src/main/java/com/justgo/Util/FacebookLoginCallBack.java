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
import com.justgo.Connecter.RetrofitRepo;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class FacebookLoginCallBack implements FacebookCallback<LoginResult> {
    // 로그인 성공 시 호출 됩니다. Access Token 발급 성공.
    @Override
    public void onSuccess(LoginResult loginResult) {
        Log.d("getAccessToken()", String.valueOf(loginResult.getAccessToken()));
        Log.d("getId()", String.valueOf(Profile.getCurrentProfile().getId()));
        Log.d("getName()", String.valueOf(Profile.getCurrentProfile().getName())); // 이름
        Log.d("getProfilePictureUri", String.valueOf(Profile.getCurrentProfile().getProfilePictureUri(200, 200)));//프로필 사진
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
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://ec2-52-79-240-33.ap-northeast-2.compute.amazonaws.com:7777/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        API connecter = retrofit.create(API.class);
        Call<RetrofitRepo> call = connecter.post_user("userId");
        call.enqueue(new Callback<RetrofitRepo>() {
            @Override
            public void onResponse(@NonNull Call<RetrofitRepo> call, @NonNull Response<RetrofitRepo> response) {
                Log.d("KIMTAEYOUNGBUNGSIN", "val " + response.code());
                Log.d("HI","val"+response.toString());
                RetrofitRepo repo = response.body();
                repo.setUserId(String.valueOf(Profile.getCurrentProfile().getId()));
//                Log.d("KIMTAEYOUNGBUNGSIN", repo.getName() + " " + repo.getPicture() + " " + repo.getUserId());
            }

            @Override
            public void onFailure(Call<RetrofitRepo> call, Throwable t) {

            }
        });
    }
}
