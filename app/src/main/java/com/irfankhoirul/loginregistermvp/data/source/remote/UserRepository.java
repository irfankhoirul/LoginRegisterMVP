package com.irfankhoirul.loginregistermvp.data.source.remote;

import com.irfankhoirul.loginregistermvp.data.pojo.User;
import com.irfankhoirul.mvp_core.data.RequestResponseListener;

public interface UserRepository {
    void register(User user, RequestResponseListener listener);

    void login(String email, String password, RequestResponseListener<User> listener);

    void logout(String token, RequestResponseListener listener);
}
