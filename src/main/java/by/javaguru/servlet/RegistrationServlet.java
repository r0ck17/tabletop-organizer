package by.javaguru.servlet;

import by.javaguru.dto.CreateUserDto;
import by.javaguru.service.UserService;
import by.javaguru.util.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.validation.ConstraintViolationException;
import java.io.IOException;

import static by.javaguru.util.UrlPath.ACCOUNT;
import static by.javaguru.util.UrlPath.REGISTRATION;

@WebServlet(REGISTRATION)
public class RegistrationServlet extends HttpServlet {
    private final UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(JspHelper.getPath("registration"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CreateUserDto createUserDto = CreateUserDto.builder()
                .login(req.getParameter("email"))
                .name(req.getParameter("name"))
                .password(req.getParameter("password"))
                .roleId(1) // TODO : fix
                .build();

        try {
            userService.create(createUserDto);
            resp.sendRedirect(ACCOUNT);
        } catch (ConstraintViolationException e) {
            req.setAttribute("errors", e.getConstraintViolations());
            doGet(req, resp);
        }
    }
}
