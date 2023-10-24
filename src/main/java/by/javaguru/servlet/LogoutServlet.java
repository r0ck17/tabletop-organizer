package by.javaguru.servlet;

import by.javaguru.dto.UserDto;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

import static by.javaguru.util.UrlPath.LOGIN;
import static by.javaguru.util.UrlPath.LOGOUT;

@WebServlet(LOGOUT)
@Slf4j
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        UserDto user = (UserDto) req.getSession().getAttribute("user");
        log.info("User {}[ID:{}] is logged out", user.getId(), user.getLogin());

        req.getSession().invalidate();
        resp.sendRedirect(LOGIN);
    }
}
