package com.irfankhoirul.loginregistermvp.modul.register;

import com.irfankhoirul.loginregistermvp.data.pojo.User;
import com.irfankhoirul.loginregistermvp.data.source.remote.UserRepository;
import com.irfankhoirul.mvp_core.custom_views.ConstantStatus;
import com.irfankhoirul.mvp_core.data.DataResult;
import com.irfankhoirul.mvp_core.data.RequestResponseListener;

/**
 * Created by Irfan Khoirul on 7/15/2017.
 */

public class RegisterPresenter implements RegisterContract.Presenter {

    private final RegisterContract.View view;
    private final UserRepository userRepository;

    public RegisterPresenter(RegisterContract.View view, UserRepository userRepository) {
        this.view = view;
        this.userRepository = userRepository;
        this.view.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void performRegister(String name, String email, String gender, String password) {
        view.setLoadingDialog(true, "Registering");
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setGender(gender);
        user.setPassword(password);

        userRepository.register(user, new RequestResponseListener() {
            @Override
            public void onSuccess(DataResult dataResult) {
                if (dataResult.getCode() == ConstantStatus.STATUS_SUCCESS) {
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
                view.showStatus(ConstantStatus.STATUS_ERROR, "Registration Failed");
            }
        });
    }

}
