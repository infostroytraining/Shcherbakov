package com.infostroy.shcherbakov.captcha;

import com.github.cage.Cage;

import java.awt.image.BufferedImage;
import java.util.Random;

public class Captcha {

    private String value;

    private BufferedImage image;

    public void generateCaptcha() {
        Random random = new Random();
        Cage cage = new Cage();

        value = String.valueOf(random.nextInt(10000));

        image = cage.drawImage(value);
    }

    public String getValue() {
        return value;
    }

    public BufferedImage getImage() {
        return image;
    }
}