package ru.stacy.dto.converters;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import ru.stacy.dto.UserDTO;
import ru.stacy.entities.User;

@Service
public class UserConverter {
    public static UserDTO toUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user, userDTO);

        return userDTO;
    }
}
