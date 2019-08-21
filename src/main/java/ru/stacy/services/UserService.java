package ru.stacy.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.stacy.dto.UserDTO;
import ru.stacy.dto.converters.UserConverter;
import ru.stacy.entities.User;
import ru.stacy.repositories.UserRepository;
import ru.stacy.util.Delay;
import ru.stacy.util.UserStateResponse;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Long createUser(User user) {
        User result = userRepository.save(user);
        return result.getId();
    }

    public Optional<UserDTO> getUserById(Long id) {
        User user = userRepository.findUserById(id);
        UserDTO userDTO = UserConverter.toUserDTO(user);
        return Optional.of(userDTO);
    }

    public UserStateResponse updateUserState(Long id, Boolean newUserState) {
        Delay.startDelay(5);

        User user = userRepository.findUserById(id);

        Boolean oldUserState = user.getUserState();
        user.setUserState(newUserState);

        user.setTimestamp(createUserStateTimestamp());
        user.setUnixTimestamp(createUserStateUnixTimestamp());

        userRepository.save(user);

        return new UserStateResponse(id, oldUserState, newUserState);
    }

    private static Timestamp createUserStateTimestamp() {
        return new Timestamp(new Date().getTime());
    }

    private static Long createUserStateUnixTimestamp() {
        Timestamp timestamp = new Timestamp(new Date().getTime());
        return timestamp.getTime();
    }

    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(UserConverter::toUserDTO).collect(Collectors.toList());
    }

    public List<UserDTO> getAllUsersByUserState(Boolean state) {
        List<User> users = userRepository.findByUserState(state);
        return users.stream().map(UserConverter::toUserDTO).collect(Collectors.toList());
    }

    public List<UserDTO> getAllUsersByUnixTimestampAfter(Long unixTimestamp) {
        List<User> users = userRepository.findByUnixTimestampAfter(unixTimestamp);
        return users.stream().map(UserConverter::toUserDTO).collect(Collectors.toList());
    }

    public List<UserDTO> getAllUsersByUserStateAndTimestampAfter(Boolean userState, Long unixTimestamp) {
        List<User> users = userRepository.findByUserStateAndUnixTimestampAfter(userState, unixTimestamp);
        return users.stream().map(UserConverter::toUserDTO).collect(Collectors.toList());
    }
}
