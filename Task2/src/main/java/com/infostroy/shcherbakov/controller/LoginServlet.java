package com.infostroy.shcherbakov.controller;
import com.infostroy.shcherbakov.constants.*;
import com.infostroy.shcherbakov.model.entity.User;
import com.infostroy.shcherbakov.services.UserService;
import com.infostroy.shcherbakov.services.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginServlet extends HttpServlet{

    private UserService userService;
    private static final Logger log = LogManager.getLogger();

    @Override
    public void init() throws ServletException {
        userService = (UserService) this.getServletContext().getAttribute(ContextConstants.USER_SERVICE);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        if (!session.isNew()) {
            req.setAttribute(ServletConstants.SESSION_WRONG_EMAIL,
                    session.getAttribute(ServletConstants.SESSION_WRONG_EMAIL));
            session.setAttribute(ServletConstants.SESSION_WRONG_EMAIL, null);

            req.setAttribute(ServletConstants.SESSION_WRONG_PASSWORD,
                    session.getAttribute(ServletConstants.SESSION_WRONG_PASSWORD));
            session.setAttribute(ServletConstants.SESSION_WRONG_PASSWORD, null);
        }

        req.getRequestDispatcher(PathConstants.LOGIN_PAGE_JSP).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter(EntityConstants.USER_EMAIL);
        String password = req.getParameter(EntityConstants.USER_PASSWORD);

        User user = null;
        try {
            user = userService.getUserByEmail(email);
        } catch (ServiceException e) {
            log.error("Exception in servlet", e);
            throw new ServletException(e);
        }
        if (user == null) {
            HttpSession session = req.getSession();
            session.setAttribute(ServletConstants.SESSION_WRONG_EMAIL, ErrorConstants.WRONG_EMAIL);
            resp.sendRedirect(PathConstants.LOGIN_PAGE);
            return;
        }

        if (!user.getPassword().equals(password)) {
            HttpSession session = req.getSession();
            session.setAttribute(ServletConstants.SESSION_WRONG_PASSWORD, ErrorConstants.WRONG_PASSWORD);
            resp.sendRedirect(PathConstants.LOGIN_PAGE);
            return;
        }

        HttpSession session = req.getSession();
        session.setAttribute(ServletConstants.SESSION_CURRENT_USER, user);
        resp.sendRedirect(PathConstants.USER_INFO);
    }
}