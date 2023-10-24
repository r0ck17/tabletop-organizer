package by.javaguru.servlet;

import by.javaguru.dto.BoardGameDto;
import by.javaguru.service.BoardGameService;
import by.javaguru.util.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.List;

import static by.javaguru.util.UrlPath.GAMES;

@WebServlet(GAMES)
@Slf4j
public class GamesServlet extends HttpServlet {
    private final BoardGameService boardGameService = BoardGameService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<BoardGameDto> games = boardGameService.findAll();
        req.setAttribute("games", games);
        req.getRequestDispatcher(JspHelper.getPath("games")).forward(req, resp);
    }
}
