package by.javaguru.servlet;

import by.javaguru.dto.UserDto;
import by.javaguru.service.UserService;
import by.javaguru.util.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;

import java.io.IOException;

import static by.javaguru.util.UrlPath.ACCOUNT;
import static by.javaguru.util.UrlPath.LOGIN;

@WebServlet(LOGIN)
public class LoginServlet extends HttpServlet {
    private final UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(JspHelper.getPath(LOGIN))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        userService.login(req.getParameter("email"),req.getParameter("password"))
                .ifPresentOrElse(userDto -> onLoginSuccess(userDto, req, resp), () -> onLoginFail(req, resp));
    }

    @SneakyThrows
    private void onLoginSuccess(UserDto userDto, HttpServletRequest req, HttpServletResponse resp) {
        req.getSession().setAttribute("user", userDto);
        resp.sendRedirect(ACCOUNT);
    }

    @SneakyThrows
    private void onLoginFail(HttpServletRequest req, HttpServletResponse resp) {
        resp.sendRedirect(LOGIN + "?error&email=" + req.getParameter("email"));
    }
}
