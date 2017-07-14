package com.irfankhoirul.loginregistermvp.data.source.local;

import com.irfankhoirul.loginregistermvp.data.pojo.User;

/**
 * Created by Irfan Khoirul on 5/8/2017.
 */

public interface SessionRepository {
    User initialize(User sessionData);

    User getSessionData();

    void setSessionData(User sessionData);

    void destroy();
}
