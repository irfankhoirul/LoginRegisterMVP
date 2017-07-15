package com.irfankhoirul.loginregistermvp.modul.profile;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.irfankhoirul.loginregistermvp.R;
import com.irfankhoirul.loginregistermvp.data.pojo.User;
import com.irfankhoirul.loginregistermvp.data.source.local.SessionRepositoryImpl;
import com.irfankhoirul.loginregistermvp.data.source.remote.UserRepositoryImpl;
import com.irfankhoirul.loginregistermvp.modul.login.LoginActivity;
import com.irfankhoirul.mvp_core.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends BaseFragment<ProfileActivity, ProfileContract.Presenter>
        implements ProfileContract.View {

    @BindView(R.id.iv_profile)
    ImageView ivProfile;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_email)
    TextView tvEmail;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentView = inflater.inflate(R.layout.fragment_profile, container, false);
        unbinder = ButterKnife.bind(this, fragmentView);

        mPresenter = new ProfilePresenter(this, new SessionRepositoryImpl(activity),
                new UserRepositoryImpl());
        mPresenter.start();

        return fragmentView;
    }

    @OnClick(R.id.bt_logout)
    public void setBtLogoutClick() {
        mPresenter.performLogout();
    }

    @Override
    protected void setTitle() {
        title = getString(R.string.fragment_title_profile);
    }

    @Override
    public void setPresenter(ProfileContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void setLoadingDialog(boolean isLoading, @Nullable String message) {
        super.setLoadingDialog(isLoading, message);
    }

    @Override
    public void showStatus(int type, String message) {
        super.showStatus(type, message);
    }

    @Override
    public void showProfile(User user) {
        if (user.getGender().equals("M")) {
            ivProfile.setImageResource(R.drawable.ic_male);
        } else {
            ivProfile.setImageResource(R.drawable.ic_female);
        }
        tvName.setText(user.getName());
        tvEmail.setText(user.getEmail());
    }

    @Override
    public void redirectToLogin() {
        Intent intent = new Intent(activity, LoginActivity.class);
        startActivity(intent);
    }
}
