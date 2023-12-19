package by.javaguru.controller;

import by.javaguru.dto.UserBoardGameDto;
import by.javaguru.entity.User;
import by.javaguru.mapper.UserBoardGameMapper;
import by.javaguru.service.UserBoardGameService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/boardgames")
@Slf4j
public class UserBoardGameController {

    private final UserBoardGameService userBoardGameService;
    private final UserBoardGameMapper userBoardGameMapper;

    @GetMapping
    public String getList(Model model, @SessionAttribute("user") User user) {
        log.info("Creating a page with a list of user games for user with ID '{}'", user.getId());
        List<UserBoardGameDto> userGames = userBoardGameService.findAllByUserId(user.getId())
                .stream()
                .map(userBoardGameMapper::toDto)
                .toList();

        model.addAttribute("games", userGames);
        model.addAttribute("user", user);

        return "boardgames";
    }

    @PostMapping("/{id}")
    public String removeUserGame(@PathVariable Long id) {
        log.info("Deleting user boardgame with ID {}", id);
        userBoardGameService.deleteById(id);
        return "redirect:/boardgames";
    }
}
