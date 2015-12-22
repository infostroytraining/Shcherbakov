package com.infostroy.shcherbakov.listeners;

import com.infostroy.shcherbakov.captcha.CaptchaManager;
import com.infostroy.shcherbakov.captcha.CaptchaManagerFactory;
import com.infostroy.shcherbakov.constants.ContextConstants;
import com.infostroy.shcherbakov.listeners.factory.ServiceFactory;
import com.infostroy.shcherbakov.model.DAO.UserDao;
import com.infostroy.shcherbakov.services.UserService;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ContextListener implements ServletContextListener {

    private static Logger logger = LogManager.getLogger(ContextListener.class);
    private static final String STORAGE_INIT_PARAMETER = "storage";

    public void contextDestroyed(ServletContextEvent event) {
        logger.debug("servlet context is destroyed");
    }

    public void contextInitialized(ServletContextEvent event) {
        ServletContext servletContext = event.getServletContext();
        String storageMode = servletContext.getInitParameter(STORAGE_INIT_PARAMETER);
        logger.debug("Try to initialize service for {} storage mode", storageMode);
        String uploadPath = servletContext.getInitParameter("uploadPath");
        servletContext.setAttribute(ContextConstants.UPLOAD_PATH, uploadPath);
        UserService userService;
        userService = ServiceFactory.getUserService(storageMode);
        logger.debug("service initialized. Service: {}", userService);
        servletContext.setAttribute(ContextConstants.USER_SERVICE, userService);

        CaptchaManager captchaManager = CaptchaManagerFactory.getCookieCaptchaManager();
        servletContext.setAttribute(ContextConstants.CAPTCHA_MANAGER, captchaManager);
    }
}