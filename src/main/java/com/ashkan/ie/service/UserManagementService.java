package com.ashkan.ie.service;

import com.ashkan.ie.dto.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by K550 VX on 6/7/2019.
 */
public interface UserManagementService {

    Page<UserDTO> listUsers(Pageable pageable);

    UserDTO enableUser(Long userId);

    UserDTO disableUser(Long userId);

    void deleteUser(Long userId);

    UserDTO activateUser(Long userId);

    Page<UserDTO> unactivatedList(Pageable pageable);

}
