package com.infostroy.shcherbakov.controller;

import com.infostroy.shcherbakov.constants.ContextConstants;
import com.infostroy.shcherbakov.constants.PathConstants;
import com.infostroy.shcherbakov.constants.ServletConstants;
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

public class UserInfoServlet extends HttpServlet{

    private UserService userService;
    private static final Logger log = LogManager.getLogger();

    @Override
    public void init() throws ServletException {
        userService = (UserService) this.getServletContext().getAttribute(ContextConstants.USER_SERVICE);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        String email = (String) session.getAttribute(ServletConstants.SESSION_CURRENT_USER_EMAIL);

        User user = null;
        try {
            user = userService.getUserByEmail(email);
        } catch (ServiceException e) {
            log.error("Exception in servlet", e);
            throw new ServletException(e);
        }

        req.setAttribute(ServletConstants.SESSION_CURRENT_USER, user);
        req.getRequestDispatcher(PathConstants.USER_INFO_JSP).forward(req, resp);
    }
}
