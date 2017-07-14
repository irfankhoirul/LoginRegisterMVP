package com.irfankhoirul.loginregistermvp.modul.login;

import com.irfankhoirul.loginregistermvp.data.source.local.SessionRepository;
import com.irfankhoirul.loginregistermvp.data.source.remote.UserRepository;

/**
 * Created by Irfan Khoirul on 7/14/2017.
 */

public class LoginPresenter implements LoginContract.Presenter {

    private final LoginContract.View view;
    private final UserRepository userRepository;
    private final SessionRepository sessionRepository;

    public LoginPresenter(LoginContract.View view, UserRepository userRepository,
                          SessionRepository sessionRepository) {
        this.view = view;
        this.userRepository = userRepository;
        this.sessionRepository = sessionRepository;
        this.view.setPresenter(this);
    }

    @Override
    public void performLogin() {

    }

    @Override
    public boolean isUserLoggedIn() {
        return false;
    }

    @Override
    public void start() {

    }
}
