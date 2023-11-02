package by.javaguru.service;

import by.javaguru.dto.CreateUserDto;
import by.javaguru.dto.UserDto;
import by.javaguru.entity.User;
import by.javaguru.entity.UserRole;
import by.javaguru.entity.UserRoleType;
import by.javaguru.exception.ValidationException;
import by.javaguru.mapper.CreateUserMapper;
import by.javaguru.mapper.UserMapper;
import by.javaguru.repository.UserRepository;
import by.javaguru.servlet.AccountServlet;
import by.javaguru.validator.CreateUserValidator;
import by.javaguru.validator.ValidatorResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class UserService {
    private static final UserService INSTANCE = new UserService();
    private final UserRepository userRepository = UserRepository.getInstance();
    private final CreateUserMapper createUserMapper = CreateUserMapper.getInstance();
    private final UserMapper userMapper = UserMapper.getInstance();
    private final CreateUserValidator createUserValidator = CreateUserValidator.getInstance();
    private final Logger logger = LoggerFactory.getLogger(AccountServlet.class);

    private UserService() {
    }

    public static UserService getInstance() {
        return INSTANCE;
    }

    public Long create(CreateUserDto createUserDto) {
        ValidatorResult validationResult = createUserValidator.validate(createUserDto);

        if (!validationResult.isValid()) {
            throw new ValidationException(validationResult.getErrors());
        }
        
        User user = createUserMapper.mapFrom(createUserDto);
        user.setUserRole(new UserRole(1, UserRoleType.USER)); // TODO : fix hardcode
        User savedUser = userRepository.save(user);

        logger.info("User {}[ID:{}] was successfully registered", user.getLogin(), user.getId());

        return savedUser.getId();
    }

    public Optional<UserDto> login(String email, String password) {
        return userRepository.getUserByEmailAndPassword(email, password)
                .map(userMapper::mapFrom);
    }
}
