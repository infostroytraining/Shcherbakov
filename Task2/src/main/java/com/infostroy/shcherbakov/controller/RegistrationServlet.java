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
import com.infostroy.shcherbakov.model.entity.User;
import com.infostroy.shcherbakov.services.UserService;
import com.infostroy.shcherbakov.utils.AvatarUtils;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 100, maxFileSize = 1024 * 1024 * 10, maxRequestSize = 1024 * 1024 * 50)
public class RegistrationServlet extends HttpServlet {

    private UserService service;
    private CaptchaManager captchaManager;
    private String uploadPath;

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

        if (!session.isNew()) {
            req.setAttribute(ErrorConstants.VALIDATION_ERRORS,
                    session.getAttribute(ErrorConstants.VALIDATION_ERRORS));
            req.setAttribute(EntityConstants.REGISTRATION_BEAN,
                    session.getAttribute(EntityConstants.REGISTRATION_BEAN));
            session.setAttribute(ErrorConstants.VALIDATION_ERRORS, null);
            session.setAttribute(EntityConstants.REGISTRATION_BEAN, null);
        }

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

        List<String> validationErrors = service.addNewUser(registrationBean, captcha, captchaInput);

        if (!validationErrors.isEmpty()) {
            session.setAttribute(ErrorConstants.VALIDATION_ERRORS, validationErrors);
            session.setAttribute(EntityConstants.REGISTRATION_BEAN, registrationBean);
            resp.sendRedirect(PathConstants.REGISTRATION_PAGE);
            return;
        }

        User user = Converter.createUserFromRegistrationBean(registrationBean);
        AvatarUtils.uploadAvatar(req, user, uploadPath);

        session.setAttribute(ServletConstants.SESSION_CURRENT_USER_EMAIL, user.getEmail());
        resp.sendRedirect(PathConstants.USER_INFO);
    }
}
