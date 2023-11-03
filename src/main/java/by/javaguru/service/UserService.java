package by.javaguru.service;

import by.javaguru.dto.CreateUserDto;
import by.javaguru.dto.UserDto;
import by.javaguru.entity.User;
import by.javaguru.entity.UserRole;
import by.javaguru.entity.UserRoleType;
import by.javaguru.mapper.CreateUserMapper;
import by.javaguru.mapper.UserMapper;
import by.javaguru.repository.UserRepository;
import by.javaguru.servlet.AccountServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Optional;
import java.util.Set;

public class UserService {
    private static final UserService INSTANCE = new UserService();
    private final UserRepository userRepository = UserRepository.getInstance();
    private final CreateUserMapper createUserMapper = CreateUserMapper.getInstance();
    private final UserMapper userMapper = UserMapper.getInstance();
    private final Logger logger = LoggerFactory.getLogger(AccountServlet.class);

    private UserService() {
    }

    public static UserService getInstance() {
        return INSTANCE;
    }

    public Long create(CreateUserDto createUserDto) {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<CreateUserDto>> validationResult = validator.validate(createUserDto);

        if (!validationResult.isEmpty()) {
            throw new ConstraintViolationException(validationResult);
        }

        //ValidatorResult validationResult = createUserValidator.validate(createUserDto);

//        if (!validationResult.isValid()) {
//            throw new ValidationException(validationResult.getErrors());
//        }
        
        User user = createUserMapper.mapFrom(createUserDto);
        user.setUserRole(new UserRole(1, UserRoleType.USER));
        User savedUser = userRepository.save(user);

        logger.info("User {}[ID:{}] was successfully registered", user.getLogin(), user.getId());

        return savedUser.getId();
    }

    public Optional<UserDto> login(String email, String password) {
        return userRepository.getUserByEmailAndPassword(email, password)
                .map(userMapper::mapFrom);
    }
}
