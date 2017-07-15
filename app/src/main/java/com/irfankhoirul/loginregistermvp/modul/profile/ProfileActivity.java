package com.irfankhoirul.loginregistermvp.modul.profile;

import android.view.View;

import com.irfankhoirul.loginregistermvp.R;
import com.irfankhoirul.mvp_core.base.BaseFragmentHolderActivity;

public class ProfileActivity extends BaseFragmentHolderActivity {

    @Override
    protected void initializeFragment() {
        btBack.setVisibility(View.GONE);
        ivIcon.setImageResource(R.mipmap.ic_launcher);
        ivIcon.setVisibility(View.VISIBLE);
        ProfileFragment profileFragment = new ProfileFragment();
        setCurrentFragment(profileFragment, false);
    }

}
