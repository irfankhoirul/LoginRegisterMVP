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
