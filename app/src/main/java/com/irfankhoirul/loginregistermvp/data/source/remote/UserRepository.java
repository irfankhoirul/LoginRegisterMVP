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

package com.irfankhoirul.loginregistermvp.data.source.remote;

import com.irfankhoirul.loginregistermvp.data.pojo.User;
import com.irfankhoirul.mvp_core.data.RequestResponseListener;

public interface UserRepository {
    void register(User user, RequestResponseListener listener);

    void login(String email, String password, RequestResponseListener<User> listener);

    void logout(String token, RequestResponseListener listener);
}
