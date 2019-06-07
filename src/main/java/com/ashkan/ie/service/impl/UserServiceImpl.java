package com.ashkan.ie.service.impl;

import com.ashkan.ie.domain.User;
import com.ashkan.ie.domain.UserAuthentication;
import com.ashkan.ie.domain.UserAuthority;
import com.ashkan.ie.dto.ProfileDTO;
import com.ashkan.ie.enumeration.UserStatus;
import com.ashkan.ie.enumeration.UserType;
import com.ashkan.ie.exception.DuplicateUserException;
import com.ashkan.ie.exception.InvalidOldPasswordException;
import com.ashkan.ie.exception.UnknownException;
import com.ashkan.ie.exception.UserNotFoundException;
import com.ashkan.ie.model.input.ResetPassModel;
import com.ashkan.ie.model.input.UserRegistrationModel;
import com.ashkan.ie.repository.UserAuthenticationRepository;
import com.ashkan.ie.repository.UserAuthorityRepository;
import com.ashkan.ie.repository.UserRepository;
import com.ashkan.ie.security.SecurityUtils;
import com.ashkan.ie.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

        String userEmail = SecurityUtils.getCurrentUserLogin()
                .orElseThrow(UnknownException::new);

        User user = userRepository.findOneByEmail(userEmail)
                .orElseThrow(UserNotFoundException::new);

        return mapToProfile(user);
    }

    private ProfileDTO mapToProfile(User user) {
        ProfileDTO profileDTO = new ProfileDTO();
        profileDTO.setId(user.getId());
        profileDTO.setActivated(user.isActivated());
        profileDTO.setEmail(user.getEmail());
        profileDTO.setFullname(user.getFullname());
        profileDTO.setUserType(user.getUserAuthority().getAuthorityVal());
        return profileDTO;
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
        user.setUserStatus(UserStatus.ENABLED);

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

    @Override
    public void resetPassword(ResetPassModel model) {
        String userName = SecurityUtils.getCurrentUserLogin()
                .orElseThrow(() -> new IllegalStateException("No username found in security context!"));
        User user = userRepository.findOneByEmail(userName)
                .orElseThrow(() -> new IllegalStateException("User not found!"));

        UserAuthentication userAuthentication = user.getUserAuthentication();
        if (!userAuthentication.getPassword().equals(model.getCurrentPassword())) {
            throw new InvalidOldPasswordException();
        }

        userAuthentication.setPassword(model.getNewPassword());
        userAuthenticationRepository.save(userAuthentication);
    }
}
