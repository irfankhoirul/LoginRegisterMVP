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

package com.irfankhoirul.loginregistermvp.modul.register;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.irfankhoirul.loginregistermvp.R;
import com.irfankhoirul.loginregistermvp.data.pojo.User;
import com.irfankhoirul.loginregistermvp.data.source.remote.UserRepositoryImpl;
import com.irfankhoirul.mvp_core.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.basgeekball.awesomevalidation.ValidationStyle.TEXT_INPUT_LAYOUT;
import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

public class RegisterFragment extends BaseFragment<RegisterActivity, RegisterContract.Presenter>
        implements RegisterContract.View {

    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_email)
    EditText etEmail;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.rb_male)
    RadioButton rbMale;
    @BindView(R.id.rb_female)
    RadioButton rbFemale;

    public RegisterFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentView = inflater.inflate(R.layout.fragment_register, container, false);
        unbinder = ButterKnife.bind(this, fragmentView);

        mPresenter = new RegisterPresenter(this, new UserRepositoryImpl());
        mPresenter.start();

        return fragmentView;
    }

    @OnClick(R.id.bt_register)
    public void setBtRegisterClick() {
        if (validateRegistrationForm()) {
            String name = etName.getText().toString();
            String email = etEmail.getText().toString();
            String password = etPassword.getText().toString();
            String gender;
            if (rbMale.isChecked()) {
                gender = User.GENDER_MALE;
            } else {
                gender = User.GENDER_FEMALE;
            }
            mPresenter.performRegister(name, email, gender, password);
        }
    }

    private boolean validateRegistrationForm() {
        AwesomeValidation formValidation = new AwesomeValidation(TEXT_INPUT_LAYOUT);
        formValidation.addValidation(activity, R.id.til_email, Patterns.EMAIL_ADDRESS,
                R.string.validation_email_must_be_valid);
        formValidation.addValidation(activity, R.id.til_email, RegexTemplate.NOT_EMPTY,
                R.string.validation_email_should_not_empty);
        formValidation.addValidation(activity, R.id.til_name, RegexTemplate.NOT_EMPTY,
                R.string.validation_name_should_not_empty);
        formValidation.addValidation(activity, R.id.til_password, RegexTemplate.NOT_EMPTY,
                R.string.validation_password_should_not_empty);

        return formValidation.validate();
    }

    @Override
    protected void setTitle() {
        title = getString(R.string.fragment_title_register);
    }

    @Override
    public void setPresenter(RegisterContract.Presenter presenter) {
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
    public void redirectToLogin() {
        activity.finish();
    }

}
