package com.irfankhoirul.loginregistermvp.modul.login;

import com.irfankhoirul.loginregistermvp.data.pojo.User;
import com.irfankhoirul.loginregistermvp.data.source.local.SessionRepository;
import com.irfankhoirul.loginregistermvp.data.source.remote.UserRepository;
import com.irfankhoirul.mvp_core.custom_views.ConstantStatus;
import com.irfankhoirul.mvp_core.data.DataResult;
import com.irfankhoirul.mvp_core.data.RequestResponseListener;

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
    public void start() {
        if (isUserLoggedIn()) {
            view.redirectToProfile();
        }
    }

    @Override
    public void performLogin(String email, String password) {
        view.setLoadingDialog(true, "Logging In");
        userRepository.login(email, password, new RequestResponseListener<User>() {
            @Override
            public void onSuccess(DataResult<User> dataResult) {
                if (dataResult.getCode() == ConstantStatus.STATUS_SUCCESS) {
                    sessionRepository.initialize(dataResult.getData());
                    view.showStatus(ConstantStatus.STATUS_SUCCESS, dataResult.getMessage());
                    view.redirectToProfile();
                } else {
                    view.setLoadingDialog(false, null);
                    view.showStatus(ConstantStatus.STATUS_ERROR, dataResult.getMessage());
                }
            }

            @Override
            public void onFailure(Throwable throwable) {
                view.setLoadingDialog(false, null);
                view.showStatus(ConstantStatus.STATUS_ERROR, "Login gagal");
            }
        });
    }

    private boolean isUserLoggedIn() {
        if (sessionRepository.getSessionData() != null) {
            return true;
        }
        return false;
    }

}
