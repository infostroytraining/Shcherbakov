package com.infostroy.shcherbakov.controller;

import com.infostroy.shcherbakov.captcha.Captcha;
import com.infostroy.shcherbakov.captcha.CaptchaManager;
import com.infostroy.shcherbakov.constants.ContextConstants;
import com.infostroy.shcherbakov.constants.EntityConstants;
import com.infostroy.shcherbakov.constants.ErrorConstants;
import com.infostroy.shcherbakov.constants.PathConstants;
import com.infostroy.shcherbakov.constants.ServletConstants;
import com.infostroy.shcherbakov.model.bean.Converter;
import com.infostroy.shcherbakov.model.bean.RegistrationBean;
import com.infostroy.shcherbakov.model.bean.Validator;
import com.infostroy.shcherbakov.model.entity.User;
import com.infostroy.shcherbakov.services.UserService;
import com.infostroy.shcherbakov.services.exception.ServiceException;
import com.infostroy.shcherbakov.utils.AvatarUtils;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 100, maxFileSize = 1024 * 1024 * 10, maxRequestSize = 1024 * 1024 * 50)
public class RegistrationServlet extends HttpServlet {

    private UserService service;
    private CaptchaManager captchaManager;
    private String uploadPath;
    private static final Logger log = LogManager.getLogger();

    @Override
    public void init() throws ServletException {
        service = (UserService) this.getServletContext().getAttribute(ContextConstants.USER_SERVICE);
        captchaManager = (CaptchaManager) this.getServletContext().getAttribute(ContextConstants.CAPTCHA_MANAGER);
        uploadPath = (String) this.getServletContext().getAttribute(ContextConstants.UPLOAD_PATH);
}

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Captcha captcha = new Captcha();
        captcha.generateCaptcha();

        captchaManager.setCaptcha(captcha, req, resp);

        HttpSession session = req.getSession();

        req.getRequestDispatcher(PathConstants.REGISTRATION_PAGE_JSP).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        String name = req.getParameter(EntityConstants.USER_NAME);
        String lastName = req.getParameter(EntityConstants.USER_LAST_NAME);
        String email = req.getParameter(EntityConstants.USER_EMAIL);
        String password = req.getParameter(EntityConstants.USER_PASSWORD);
        String repeatedPassword = req.getParameter(EntityConstants.USER_REPEATED_PASSWORD);
        String captchaInput = req.getParameter(EntityConstants.CAPTCHA_INPUT);

        Captcha captcha = captchaManager.getCaptcha(req, resp);
        RegistrationBean registrationBean = new RegistrationBean(name, lastName, email,
                password, repeatedPassword, AvatarUtils.getAvatarName(req));

        Map<String,String> validationErrors = validateForm(registrationBean, captcha, captchaInput);
        resp.setCharacterEncoding("UTF-8");
        if (!validationErrors.isEmpty()) {
            resp.setStatus(400);
            resp.setHeader("Content-Type", "application/json");
            resp.getWriter().write(new Gson().toJson(validationErrors));
        }

        User user = Converter.createUserFromRegistrationBean(registrationBean);

        try {
            service.add(user);
        } catch (ServiceException e) {
            log.error("Exception in servlet", e);
            throw new ServletException(e);
        }

        AvatarUtils.uploadAvatar(req, user, uploadPath);

        session.setAttribute(ServletConstants.SESSION_CURRENT_USER, user);
        resp.sendRedirect(PathConstants.USER_INFO);
    }

    protected Map<String,String> validateForm(RegistrationBean registrationBean, Captcha captcha, String captchaInput)  {
        Map<String,String> validationErrors=Validator.validateRegistrationBean(registrationBean);
        try {
          User tempUser = service.getUserByEmail(registrationBean.getEmail());
            if (tempUser != null) {
                validationErrors.put(EntityConstants.USER_EMAIL,ErrorConstants.USER_ALREADY_EXISTS);
            }
        } catch (ServiceException e) {
            log.error("Exception in servlet", e);
        }
        if (captcha == null || (captchaInput != null && !captchaInput.equals(captcha.getValue()))) {
            validationErrors.put(EntityConstants.CAPTCHA_INPUT,ErrorConstants.WRONG_CAPTCHA_ERROR);
        }

        return validationErrors;
    }
}
