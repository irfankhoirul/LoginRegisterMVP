package com.irfankhoirul.loginregistermvp.modul.profile;

import com.irfankhoirul.loginregistermvp.data.pojo.User;
import com.irfankhoirul.mvp_core.base.BasePresenter;
import com.irfankhoirul.mvp_core.base.BaseView;

/**
 * Created by Irfan Khoirul on 7/15/2017.
 */

public interface ProfileContract {
    interface View extends BaseView<Presenter> {
        void showProfile(User user);

        void redirectToLogin();
    }

    interface Presenter extends BasePresenter {
        void performLogout();
    }
}
