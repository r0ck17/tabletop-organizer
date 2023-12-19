package by.javaguru.service;

import by.javaguru.dto.CreateUserDto;
import by.javaguru.entity.User;
import by.javaguru.entity.UserRole;
import by.javaguru.mapper.UserMapper;
import by.javaguru.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public User save(CreateUserDto userDto) {
        log.info("Saving new user");

//        userRepository.findUserByEmail(userDto.getEmail())
//                .ifPresent(u -> {
//                    throw new UserNotCreatedException(format("A user with %s email already exists", u.getEmail()));
//                });

        User user = userMapper.toModel(userDto);
        user.setUserRole(UserRole.USER);

        return userRepository.save(user);
    }

    public Optional<User> findUserByEmailAndPassword(String email, String password) {
        log.info("Search for a user by email and password");
        return userRepository.findUserByEmailAndPassword(email, password);
    }

    public Optional<User> findUserByEmail(String email) {
        log.info("Search for a user by email");
        return userRepository.findUserByEmail(email);
    }
}
