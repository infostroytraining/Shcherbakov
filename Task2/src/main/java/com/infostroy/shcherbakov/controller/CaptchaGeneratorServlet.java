package com.infostroy.shcherbakov.controller;

import com.infostroy.shcherbakov.captcha.Captcha;
import com.infostroy.shcherbakov.captcha.CaptchaManager;
import com.infostroy.shcherbakov.constants.ContextConstants;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import java.io.IOException;

public class CaptchaGeneratorServlet extends HttpServlet {

    private CaptchaManager captchaManager;

    @Override
    public void init() throws ServletException {
        captchaManager = (CaptchaManager) this.getServletContext().getAttribute(ContextConstants.CAPTCHA_MANAGER);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        execute(req, resp);
    }

    private void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Captcha captcha;
        captcha = captchaManager.getCaptcha(req, resp);

        ServletOutputStream out = resp.getOutputStream();

        ImageIO.write(captcha.getImage(), "png", out);

        out.flush();
        out.close();
    }
}
