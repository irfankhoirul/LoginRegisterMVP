package com.irfankhoirul.loginregistermvp.modul.login;

import android.view.View;

import com.irfankhoirul.loginregistermvp.R;
import com.irfankhoirul.mvp_core.base.BaseFragmentHolderActivity;

public class LoginActivity extends BaseFragmentHolderActivity {

    @Override
    protected void initializeFragment() {
        btBack.setVisibility(View.GONE);
        ivIcon.setImageResource(R.mipmap.ic_launcher);
        ivIcon.setVisibility(View.VISIBLE);
        LoginFragment loginFragment = new LoginFragment();
        setCurrentFragment(loginFragment, false);
    }

}
