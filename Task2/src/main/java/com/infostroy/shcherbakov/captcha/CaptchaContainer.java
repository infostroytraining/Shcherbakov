package com.infostroy.shcherbakov.captcha;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class CaptchaContainer {

    private static final long LIVE_TIME = 60 * 60 * 30;

    private Map<Integer, Captcha> container = new HashMap<Integer, Captcha>();

    public synchronized int addCaptcha(Captcha captcha) {
        int id = generateId();
        container.put(id, captcha);
        return id;
    }

    public synchronized Captcha getCaptcha(int key) {
        return container.get(key);
    }

    private int generateId() {
        Random random = new Random();
        int id;

        do {
            id = random.nextInt();
        } while (container.containsKey(id));

        return id;
    }

}
