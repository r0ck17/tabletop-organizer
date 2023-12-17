package by.javaguru.service;

import by.javaguru.dto.CreateUserDto;
import by.javaguru.entity.User;
import by.javaguru.mapper.UserMapper;
import by.javaguru.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public void save(CreateUserDto createUserDto) {
        User user = userMapper.toModel(createUserDto);
        userRepository.save(user);
    }

    public Optional<User> findUserByEmailAndPassword(String email, String password) {
        return userRepository.findUserByEmailAndPassword(email, password);
    }
}
