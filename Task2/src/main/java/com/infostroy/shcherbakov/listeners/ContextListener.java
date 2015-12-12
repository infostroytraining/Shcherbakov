package com.infostroy.shcherbakov.listeners;

import com.infostroy.shcherbakov.captcha.CaptchaManager;
import com.infostroy.shcherbakov.captcha.CaptchaManagerFactory;
import com.infostroy.shcherbakov.constants.ContextConstants;
import com.infostroy.shcherbakov.model.DAO.UserDao;
import com.infostroy.shcherbakov.services.UserService;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextListener implements ServletContextListener {


    public void contextDestroyed(ServletContextEvent event) {
    }

    public void contextInitialized(ServletContextEvent event) {
        ServletContext servletContext = event.getServletContext();

        String uploadPath = servletContext.getInitParameter("uploadPath");
        servletContext.setAttribute(ContextConstants.UPLOAD_PATH, uploadPath);

        UserDao userDao = new UserDao();

        UserService userService = new UserService(userDao);
        servletContext.setAttribute(ContextConstants.USER_SERVICE, userService);

        CaptchaManager captchaManager = CaptchaManagerFactory.getCookieCaptchaManager();
        servletContext.setAttribute(ContextConstants.CAPTCHA_MANAGER, captchaManager);
    }
}