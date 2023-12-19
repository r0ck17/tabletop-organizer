package by.javaguru.controller;

import by.javaguru.entity.User;
import by.javaguru.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
@SessionAttributes({"user"})
@Slf4j
public class LoginController {

    private final UserService userService;

    @GetMapping("/login")
    public String index() {
        return "login";
    }

    @PostMapping("/login")
    public String authUser(@RequestParam String email,
                           @RequestParam String password,
                           Model model) {
        Optional<User> user = userService.findUserByEmailAndPassword(email, password);

        return user.map(u -> {
            model.addAttribute(u);
            return "redirect:/boardgames";
        }).orElseGet(() -> {
            model.addAttribute("error", "Пользователь с такими данными не найден");
            return "login";
        });
    }

    @GetMapping("/logout")
    public String logout(SessionStatus status, HttpSession httpSession,
                         @SessionAttribute(value = "user", required = false) User user) {
        if (user != null) {
            log.info("User with ID '{}' logged out", user.getEmail());
            status.setComplete();
            httpSession.invalidate();
        }
        return "redirect:/login";
    }
}
