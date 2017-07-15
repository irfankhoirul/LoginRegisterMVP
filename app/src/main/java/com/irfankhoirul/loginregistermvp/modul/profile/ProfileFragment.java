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

package com.irfankhoirul.loginregistermvp.modul.profile;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
        if (user.getGender().equals(User.GENDER_MALE)) {
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
