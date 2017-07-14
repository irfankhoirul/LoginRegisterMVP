package com.irfankhoirul.loginregistermvp.data.source.remote;

import com.irfankhoirul.loginregistermvp.data.pojo.User;
import com.irfankhoirul.mvp_core.data.DataResult;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Irfan Khoirul on 7/14/2017.
 */

public interface UserEndPoints {
    String REGISTER_END_POINT = "register";
    String LOGIN_END_POINT = "login";
    String LOGOUT_END_POINT = "logout";

    @FormUrlEncoded
    @POST(REGISTER_END_POINT)
    Call<DataResult> register(@FieldMap Map<String, String> param);

    @FormUrlEncoded
    @POST(LOGIN_END_POINT)
    Call<DataResult<User>> login(@FieldMap Map<String, String> param);

    @FormUrlEncoded
    @POST(LOGOUT_END_POINT)
    Call<DataResult> logout(@FieldMap Map<String, String> param);

}
