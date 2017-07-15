package com.irfankhoirul.loginregistermvp.modul.profile;

import com.irfankhoirul.loginregistermvp.data.pojo.User;
import com.irfankhoirul.loginregistermvp.data.source.local.SessionRepository;
import com.irfankhoirul.loginregistermvp.data.source.remote.UserRepository;
import com.irfankhoirul.mvp_core.custom_views.ConstantStatus;
import com.irfankhoirul.mvp_core.data.DataResult;
import com.irfankhoirul.mvp_core.data.RequestResponseListener;

/**
 * Created by Irfan Khoirul on 7/15/2017.
 */

public class ProfilePresenter implements ProfileContract.Presenter {

    private final ProfileContract.View view;
    private final SessionRepository sessionRepository;
    private final UserRepository userRepository;
    private User user;

    public ProfilePresenter(ProfileContract.View view, SessionRepository sessionRepository,
                            UserRepository userRepository) {
        this.view = view;
        this.userRepository = userRepository;
        this.sessionRepository = sessionRepository;
        this.view.setPresenter(this);
    }

    @Override
    public void start() {
        user = getUserData();
        view.showProfile(user);
    }

    @Override
    public void performLogout() {
        view.setLoadingDialog(true, "Logging out");
        userRepository.logout(user.getAuthToken(), new RequestResponseListener() {
            @Override
            public void onSuccess(DataResult dataResult) {
                if (dataResult.getCode() == ConstantStatus.STATUS_SUCCESS) {
                    sessionRepository.destroy();
                    view.showStatus(ConstantStatus.STATUS_SUCCESS, dataResult.getMessage());
                    view.redirectToLogin();
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

    private User getUserData() {
        return sessionRepository.getSessionData();
    }
}
