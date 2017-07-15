package com.irfankhoirul.loginregistermvp.modul.register;

import com.irfankhoirul.mvp_core.base.BasePresenter;
import com.irfankhoirul.mvp_core.base.BaseView;

/**
 * Created by Irfan Khoirul on 7/15/2017.
 */

public interface RegisterContract {
    interface View extends BaseView<Presenter> {
        void redirectToLogin();
    }

    interface Presenter extends BasePresenter {
        void performRegister(String name, String email, String gender, String password);
    }
}
