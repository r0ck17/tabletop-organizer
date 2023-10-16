package by.javaguru.servlet;

import by.javaguru.dto.BoardGameDto;
import by.javaguru.service.BoardGameService;
import by.javaguru.util.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/games")
public class GamesServlet extends HttpServlet {
    private final BoardGameService boardGameService = BoardGameService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<BoardGameDto> games = boardGameService.findAll();
        req.setAttribute("games", games);
        req.getRequestDispatcher(JspHelper.getPath("games")).forward(req, resp);
    }
}
