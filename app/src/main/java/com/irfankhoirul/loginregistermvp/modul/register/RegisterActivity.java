package com.irfankhoirul.loginregistermvp.modul.register;

import com.irfankhoirul.mvp_core.base.BaseFragmentHolderActivity;

public class RegisterActivity extends BaseFragmentHolderActivity {

    @Override
    protected void initializeFragment() {
        RegisterFragment registerFragment = new RegisterFragment();
        setCurrentFragment(registerFragment, false);
    }

}
