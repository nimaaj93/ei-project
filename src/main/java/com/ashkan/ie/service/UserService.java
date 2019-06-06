package com.ashkan.ie.service;

import com.ashkan.ie.dto.ProfileDTO;
import com.ashkan.ie.model.input.ResetPassModel;
import com.ashkan.ie.model.input.UserRegistrationModel;

/**
 * Created by K550 VX on 6/6/2019.
 */
public interface UserService {

    ProfileDTO getProfile();

    void register(UserRegistrationModel model);

}
