package com.ashkan.ie.mapper;

import com.ashkan.ie.domain.User;
import com.ashkan.ie.dto.UserDTO;
import org.springframework.stereotype.Component;

/**
 * Created by K550 VX on 6/7/2019.
 */
@Component
public class UserMapper implements EntityMapper<UserDTO, User> {

    @Override
    public UserDTO toDto(User entity) {

        UserDTO userDTO = new UserDTO();

        userDTO.setId(entity.getId());
        userDTO.setActivated(entity.isActivated());
        userDTO.setEmail(entity.getEmail());
        userDTO.setFullname(entity.getFullname());
        userDTO.setUserStatus(entity.getUserStatus());
        userDTO.setUserType(entity.getUserAuthority().getAuthorityVal());

        return userDTO;
    }

    @Override
    public User toEntity(UserDTO dto) {

        User user = new User();

        user.setId(dto.getId());
        user.setFullname(dto.getFullname());
        user.setActivated(dto.isActivated());
        user.setUserStatus(dto.getUserStatus());
        user.setEmail(dto.getEmail());

        return user;
    }
}
