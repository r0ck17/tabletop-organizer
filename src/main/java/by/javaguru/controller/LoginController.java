package by.javaguru.controller;

import by.javaguru.entity.User;
import by.javaguru.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
@SessionAttributes({"user"})
public class LoginController {

    private final UserService userService;

    @GetMapping("/login")
    public String getIndex(Model model) {
        model.addAttribute(new User());
        return "index";
    }

    @PostMapping("/login")
    public String authUser(@RequestParam(name = "username") String email,
                           @RequestParam String password, Model model) {
        Optional<User> user = userService.findUserByEmailAndPassword(email, password);
        return user.map(u -> {
            model.addAttribute(u);
            return "redirect:/boardgames";
        }).orElse("redirect:/login");
    }

    @GetMapping("/logout")
    public String logout(SessionStatus status, HttpSession httpSession) {
        status.setComplete();
        httpSession.invalidate();
        return "redirect:/login";
    }
}
