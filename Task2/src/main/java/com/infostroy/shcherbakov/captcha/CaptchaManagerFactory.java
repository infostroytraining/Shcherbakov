package com.infostroy.shcherbakov.captcha;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public final class CaptchaManagerFactory {

    private static final String CAPTCHA_ID = "captcha_id";

    private static CaptchaContainer captchaContainer = new CaptchaContainer();

    public static CaptchaManager getCookieCaptchaManager() {
        return new CaptchaManager() {
            @Override
            public void setCaptcha(Captcha captcha, HttpServletRequest request, HttpServletResponse response) {
                int id = captchaContainer.addCaptcha(captcha);
                Cookie cookie = new Cookie(CAPTCHA_ID, String.valueOf(id));
                cookie.setMaxAge(24 * 60 * 60);
                response.addCookie(cookie);
            }

            @Override
            public Captcha getCaptcha(HttpServletRequest request, HttpServletResponse response) {
                Cookie[] cookies = request.getCookies();

                String id = null;
                for (Cookie cookie : cookies) {
                    if (CAPTCHA_ID.equals(cookie.getName())) {
                       id = cookie.getValue();
                    }
                }

                if (id == null) {
                    return null;
                }

                return captchaContainer.getCaptcha(Integer.valueOf(id));
            }
        };
    }
}
