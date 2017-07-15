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

package com.irfankhoirul.loginregistermvp.modul.login;

import com.irfankhoirul.loginregistermvp.data.pojo.User;
import com.irfankhoirul.loginregistermvp.data.source.local.SessionRepository;
import com.irfankhoirul.loginregistermvp.data.source.remote.UserRepository;
import com.irfankhoirul.mvp_core.custom_views.ConstantStatus;
import com.irfankhoirul.mvp_core.data.DataResult;
import com.irfankhoirul.mvp_core.data.RequestResponseListener;

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
                view.setLoadingDialog(false, null);
                if (dataResult.getCode() == ConstantStatus.STATUS_SUCCESS) {
                    sessionRepository.initialize(dataResult.getData());
                    view.showStatus(ConstantStatus.STATUS_SUCCESS, dataResult.getMessage());
                    view.redirectToProfile();
                } else {
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
