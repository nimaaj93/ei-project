package com.ashkan.ie.service.impl;

import com.ashkan.ie.domain.User;
import com.ashkan.ie.domain.UserAuthentication;
import com.ashkan.ie.domain.UserAuthority;
import com.ashkan.ie.dto.ProfileDTO;
import com.ashkan.ie.enumeration.UserType;
import com.ashkan.ie.exception.DuplicateUserException;
import com.ashkan.ie.model.input.ResetPassModel;
import com.ashkan.ie.model.input.UserRegistrationModel;
import com.ashkan.ie.repository.UserAuthenticationRepository;
import com.ashkan.ie.repository.UserAuthorityRepository;
import com.ashkan.ie.repository.UserRepository;
import com.ashkan.ie.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.jws.soap.SOAPBinding;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Created by K550 VX on 6/6/2019.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserAuthenticationRepository userAuthenticationRepository;
    @Autowired
    private UserAuthorityRepository userAuthorityRepository;

    @Override
    public ProfileDTO getProfile() {
        return null;
    }

    @Override
    @Transactional
    public void register(UserRegistrationModel model) {

        userRepository.findOneByEmail(model.getUsername())
                .ifPresent(user -> {
                    throw new DuplicateUserException();
                });

        User user = mapToEntity(model);
        user = userRepository.save(user);
        userAuthenticationRepository.save(user.getUserAuthentication());
        userAuthorityRepository.save(user.getUserAuthority());
    }

    private User mapToEntity(UserRegistrationModel model) {
        User user = new User();
        user.setFullname(model.getFullname());
        user.setEmail(model.getUsername());
        if (model.getUserType() == UserType.STUDENT) {
            user.setActivated(true);
        } else {
            user.setActivated(false);
        }

        UserAuthentication userAuthentication = new UserAuthentication();
        userAuthentication.setUser(user);
        userAuthentication.setPassword(model.getPassword());
        user.setUserAuthentication(userAuthentication);

        UserAuthority userAuthority = new UserAuthority();
        userAuthority.setAuthorityVal(model.getUserType());
        userAuthority.setUser(user);
        user.setUserAuthority(userAuthority);

        return user;
    }

}
