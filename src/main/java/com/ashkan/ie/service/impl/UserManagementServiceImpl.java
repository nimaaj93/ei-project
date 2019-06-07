package com.ashkan.ie.service.impl;

import com.ashkan.ie.domain.User;
import com.ashkan.ie.dto.UserDTO;
import com.ashkan.ie.enumeration.UserStatus;
import com.ashkan.ie.exception.UserNotFoundException;
import com.ashkan.ie.mapper.UserMapper;
import com.ashkan.ie.repository.UserAuthenticationRepository;
import com.ashkan.ie.repository.UserAuthorityRepository;
import com.ashkan.ie.repository.UserRepository;
import com.ashkan.ie.service.UserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by K550 VX on 6/7/2019.
 */
@Service
public class UserManagementServiceImpl implements UserManagementService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserAuthenticationRepository userAuthenticationRepository;
    @Autowired
    private UserAuthorityRepository userAuthorityRepository;

    @Override
    public Page<UserDTO> listUsers(Pageable pageable) {
        return userRepository.findAll(pageable)
                .map(userMapper::toDto);
    }

    @Override
    public UserDTO enableUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);
        user.setUserStatus(UserStatus.ENABLED);
        return userMapper.toDto(userRepository.save(user));
    }

    @Override
    public UserDTO disableUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);
        user.setUserStatus(UserStatus.DISABLED);
        return userMapper.toDto(userRepository.save(user));
    }

    @Override
    @Transactional
    public void deleteUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);
        userAuthenticationRepository.delete(user.getUserAuthentication());
        userAuthorityRepository.delete(user.getUserAuthority());
        userRepository.delete(user);
    }

    @Override
    public UserDTO activateUser(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        if (user.isActivated()) {
            throw new IllegalStateException("User is already active!");
        }

        user.setActivated(true);
        return userMapper.toDto(userRepository.save(user));
    }

    @Override
    public Page<UserDTO> unactivatedList(Pageable pageable) {
        return userRepository.findAllByActivatedFalse(pageable)
                .map(userMapper::toDto);
    }
}
