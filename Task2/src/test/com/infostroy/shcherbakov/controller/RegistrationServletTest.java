package com.infostroy.shcherbakov.controller;

import com.infostroy.shcherbakov.constants.PathConstants;
import com.infostroy.shcherbakov.constants.ServletConstants;
import com.infostroy.shcherbakov.model.bean.Converter;
import com.infostroy.shcherbakov.services.exception.ServiceException;
import org.junit.Test;

import static org.junit.Assert.*;

import com.infostroy.shcherbakov.captcha.Captcha;
import com.infostroy.shcherbakov.captcha.CaptchaManager;
import com.infostroy.shcherbakov.captcha.CaptchaManagerFactory;
import com.infostroy.shcherbakov.constants.ContextConstants;
import com.infostroy.shcherbakov.constants.EntityConstants;
import com.infostroy.shcherbakov.model.bean.RegistrationBean;
import com.infostroy.shcherbakov.model.entity.User;
import com.infostroy.shcherbakov.services.UserService;
import com.infostroy.shcherbakov.utils.AvatarUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RegistrationServletTest {
    private static final String NAME = "Andrey";
    private static final String LAST_NAME = "Shcherbakov";
    private static final String EMAIL = "qqqq@gmail.com";
    private static final String PASSWORD = "111QQQqqq";
    private static final String REPEATED_PASSWORD = "111QQQqqq";
    private static final String CAPTCHA_INPUT = "7575";
    private static final String AVATAR_NAME = "avatar";

    private RegistrationServlet registrationServlet = new RegistrationServlet();
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private UserService service;
    @Mock
    private CaptchaManager captchaManager;
    @Mock
    private ServletConfig config;
    @Mock
    private Captcha captcha;
    @Mock
    private ServletConfig servletConfig;
    @Mock
    private ServletContext servletContext;
    @Mock
    private HttpSession session;
    @Mock
    private AvatarUtils avatarUtils;
    @Mock
    private Part part;
    @Mock
    private RequestDispatcher requestDispatcher;
    @Before
    public void setUp() throws ServletException {

      when(servletConfig.getServletContext()).thenReturn(servletContext);
      when(servletContext.getAttribute(ContextConstants.USER_SERVICE)).thenReturn(service);
      when(servletContext.getAttribute(ContextConstants.CAPTCHA_MANAGER)).thenReturn(captchaManager);
      registrationServlet.init(servletConfig);
    }

    @Test
      public void testDoPost() throws Exception {
          when(request.getSession()).thenReturn(session);
          when(request.getPart(EntityConstants.USER_AVATAR)).thenReturn(part);
          when(part.getName()).thenReturn(AVATAR_NAME);
          when(captchaManager.getCaptcha(Mockito.any(), Mockito.any())).thenReturn(captcha);
          when(service.getUserByEmail(EMAIL)).thenReturn(null);
          when(captcha.getValue()).thenReturn(CAPTCHA_INPUT);
          mockGetRequestParams(NAME, LAST_NAME, EMAIL, PASSWORD, REPEATED_PASSWORD, CAPTCHA_INPUT);
          registrationServlet.doPost(request, response);
          ArgumentCaptor<User> argument = ArgumentCaptor.forClass(User.class);
          verify(service).add(argument.capture());
          User user = argument.getValue();

          assertEquals(NAME, user.getName());
          assertEquals(LAST_NAME, user.getLastName());
          assertEquals(EMAIL, user.getEmail());
          assertEquals(PASSWORD, user.getPassword());
          assertEquals(REPEATED_PASSWORD, user.getPassword());
        //  assertEquals(CAPTCHA_INPUT, captcha.getValue());
        verify(session).setAttribute(eq(ServletConstants.SESSION_CURRENT_USER), anyObject());
      }
    @Test(expected = ServletException.class)
    public void testDoPostException() throws Exception {
        when(request.getSession()).thenReturn(session);
        when(request.getPart(EntityConstants.USER_AVATAR)).thenReturn(part);
        when(part.getName()).thenReturn(AVATAR_NAME);
        when(captchaManager.getCaptcha(Mockito.any(), Mockito.any())).thenReturn(captcha);
        when(service.getUserByEmail(EMAIL)).thenReturn(null);
        when(captcha.getValue()).thenReturn(CAPTCHA_INPUT);
        mockGetRequestParams(NAME, LAST_NAME, EMAIL, PASSWORD, REPEATED_PASSWORD, CAPTCHA_INPUT);
        when(service.add(anyObject())).thenThrow(ServiceException.class);
        registrationServlet.doPost(request, response);
    }
    @Test
    public void testDoGet() throws ServletException, IOException {

        when(request.getSession()).thenReturn(session);
        when(request.getRequestDispatcher(PathConstants.REGISTRATION_PAGE_JSP)).thenReturn(requestDispatcher);
        registrationServlet.doGet(request,response);
    }

    @Test
    public void testValidateFormWrongCaptchaInput() throws Exception {
        registrationServlet.validateForm(new RegistrationBean(NAME,LAST_NAME,EMAIL,PASSWORD,REPEATED_PASSWORD,AVATAR_NAME),captcha,CAPTCHA_INPUT);
    }
    @Test
    public void testValidateFormUserExists() throws Exception {
        RegistrationBean registrationBean=new RegistrationBean(NAME,LAST_NAME,EMAIL,PASSWORD,REPEATED_PASSWORD,AVATAR_NAME);
        when(service.getUserByEmail(EMAIL)).thenReturn(Converter.createUserFromRegistrationBean(registrationBean));
        registrationServlet.validateForm(registrationBean,captcha,CAPTCHA_INPUT);
    }
    @Test
    public void testValidateFormException() throws Exception {
        RegistrationBean registrationBean=new RegistrationBean(NAME,LAST_NAME,EMAIL,PASSWORD,REPEATED_PASSWORD,AVATAR_NAME);
        when(service.getUserByEmail(EMAIL)).thenThrow(ServiceException.class);
        registrationServlet.validateForm(registrationBean,captcha,CAPTCHA_INPUT);
    }
    private void mockGetRequestParams(String name, String lastName, String email, String password, String repeatedPassword, String captchaInput){
        when(request.getParameter(EntityConstants.USER_NAME)).thenReturn(name);
        when(request.getParameter(EntityConstants.USER_LAST_NAME)).thenReturn(lastName);
        when(request.getParameter(EntityConstants.USER_EMAIL)).thenReturn(email);
        when(request.getParameter(EntityConstants.USER_PASSWORD)).thenReturn(password);
        when(request.getParameter(EntityConstants.USER_REPEATED_PASSWORD)).thenReturn(repeatedPassword);
        when(request.getParameter(EntityConstants.CAPTCHA_INPUT)).thenReturn(captchaInput);

    }
}