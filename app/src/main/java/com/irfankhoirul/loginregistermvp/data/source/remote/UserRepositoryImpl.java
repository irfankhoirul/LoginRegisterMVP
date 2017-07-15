/*
 * Copyright 2017.  Irfan Khoirul Muhlishin
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.irfankhoirul.loginregistermvp.data.source.remote;

import com.irfankhoirul.loginregistermvp.data.pojo.User;
import com.irfankhoirul.mvp_core.base.BaseRemoteRepository;
import com.irfankhoirul.mvp_core.data.DataResult;
import com.irfankhoirul.mvp_core.data.RequestResponseListener;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;

public class UserRepositoryImpl extends BaseRemoteRepository<UserEndPoints> implements UserRepository {
    // Localhost PC
    private static final String BASE_URL = "http://10.0.2.2:8000/api/";

    @Override
    protected String setBaseUrl() {
        return BASE_URL;
    }

    @Override
    protected void setEndPoint() {
        endPoint = retrofit.create(UserEndPoints.class);
    }

    @Override
    protected boolean enableLogging() {
        return true;
    }

    @Override
    public void register(User user, RequestResponseListener listener) {
        Map<String, String> params = new HashMap<>();
        params.put("name", user.getName());
        params.put("email", user.getEmail());
        params.put("password", user.getPassword());
        params.put("gender", user.getGender());
        Call<DataResult> call = endPoint.register(params);
        execute(call, listener);
    }

    @Override
    public void login(String email, String password, RequestResponseListener<User> listener) {
        Map<String, String> params = new HashMap<>();
        params.put("email", email);
        params.put("password", password);
        Call<DataResult<User>> call = endPoint.login(params);
        execute(call, listener);
    }

    @Override
    public void logout(String token, RequestResponseListener listener) {
        Map<String, String> params = new HashMap<>();
        params.put("token", token);
        Call<DataResult> call = endPoint.logout(params);
        execute(call, listener);
    }
}
