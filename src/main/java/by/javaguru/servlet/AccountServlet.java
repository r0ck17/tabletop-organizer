package by.javaguru.servlet;

import by.javaguru.dto.UserBoardGameDto;
import by.javaguru.dto.UserDto;
import by.javaguru.service.BoardGameService;
import by.javaguru.util.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.List;

import static by.javaguru.util.UrlPath.ACCOUNT;

@WebServlet(ACCOUNT)
@Slf4j
public class AccountServlet extends HttpServlet {
    private static final BoardGameService boardGameService = BoardGameService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        UserDto user = (UserDto) session.getAttribute("user");

        List<UserBoardGameDto> games = boardGameService.findUserGamesById(user.getId());

        log.info("User {}[ID:{}] navigated to the '{}']", user.getLogin(), user.getId(), ACCOUNT);
        req.setAttribute("games", games);
        req.getRequestDispatcher(JspHelper.getPath("account"))
                .forward(req, resp);
    }
}
