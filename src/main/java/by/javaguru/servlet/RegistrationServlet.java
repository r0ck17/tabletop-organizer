package by.javaguru.servlet;

import by.javaguru.dto.CreateUserDto;
import by.javaguru.exception.ValidationException;
import by.javaguru.mapper.CreateUserMapper;
import by.javaguru.service.UserService;
import by.javaguru.util.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static by.javaguru.util.UrlPath.ACCOUNT;
import static by.javaguru.util.UrlPath.REGISTRATION;

@WebServlet(REGISTRATION)
public class RegistrationServlet extends HttpServlet {
    private final UserService userService = UserService.getInstance();
    private final CreateUserMapper createUserMapper = CreateUserMapper.getInstance();

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
                .roleId(1)
                .build();

        try {
            userService.create(createUserDto);
            resp.sendRedirect(ACCOUNT);
        } catch (ValidationException e) {
            req.setAttribute("errors", e.getErrors());
            doGet(req, resp);
        }
    }
}
