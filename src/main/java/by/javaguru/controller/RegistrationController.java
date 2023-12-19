package by.javaguru.controller;

import by.javaguru.dto.CreateUserDto;
import by.javaguru.entity.User;
import by.javaguru.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
@SessionAttributes({"user"})
@RequestMapping("/registration")
@Slf4j
public class RegistrationController {

    private final UserService userService;

    @GetMapping
    public String getRegistrationPage(Model model) {
        model.addAttribute("newUser", new CreateUserDto());
        return "registration";
    }

    @PostMapping
    public String registerUser(@ModelAttribute(name = "newUser") @Valid CreateUserDto userDto,
                               BindingResult bindingResult,
                               Model model) {
        log.info("Registering a new user with email {}", userDto.getEmail());

        Optional<User> userByEmail = userService.findUserByEmail(userDto.getEmail());
        userByEmail.ifPresent((u) -> bindingResult.rejectValue("email", "error.email",
                "Указанный email уже используется"));

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        User savedUser = userService.save(userDto);
        model.addAttribute("user", savedUser);
        return "redirect:/boardgames";
    }
}
