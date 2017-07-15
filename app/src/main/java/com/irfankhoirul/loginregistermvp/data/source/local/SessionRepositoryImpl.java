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

package com.irfankhoirul.loginregistermvp.data.source.local;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.irfankhoirul.loginregistermvp.data.pojo.User;

public class SessionRepositoryImpl implements SessionRepository {
    private static String SESSION_DATA_KEY = "SessionData";
    private static String SHARED_PREFERENCE_NAME = "SessionSharedPreferences";

    private SharedPreferences sharedPref;

    public SessionRepositoryImpl(Context context) {
        sharedPref = context.getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
    }

    @Override
    public User initialize(User sessionData) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(SESSION_DATA_KEY, new Gson().toJson(sessionData));
        editor.apply();
        String sessionDataJson = sharedPref.getString(SESSION_DATA_KEY, null);
        return new Gson().fromJson(sessionDataJson, User.class);
    }

    @Override
    public User getSessionData() {
        String sessionDataJson = sharedPref.getString(SESSION_DATA_KEY, null);
        if (sessionDataJson != null) {
            return new Gson().fromJson(sessionDataJson, User.class);
        }
        return null;
    }

    @Override
    public void setSessionData(User newSessionData) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(SESSION_DATA_KEY, new Gson().toJson(newSessionData));
        editor.apply();
    }

    @Override
    public void destroy() {
        sharedPref.edit().clear().apply();
    }

}