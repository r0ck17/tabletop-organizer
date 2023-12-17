package by.javaguru.controller;

import by.javaguru.dto.CreateUserDto;
import by.javaguru.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public String createUser(@ModelAttribute CreateUserDto createUserDto) {
        userService.save(createUserDto);
        return "redirect:/boardgames";
    }
}
