package com.infostroy.shcherbakov.captcha;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CaptchaManager {

    void setCaptcha(Captcha captcha, HttpServletRequest request, HttpServletResponse response);

    Captcha getCaptcha(HttpServletRequest request, HttpServletResponse response);

}
