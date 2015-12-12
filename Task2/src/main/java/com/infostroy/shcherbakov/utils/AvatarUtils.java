package com.infostroy.shcherbakov.utils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import com.infostroy.shcherbakov.constants.EntityConstants;
import com.infostroy.shcherbakov.model.entity.User;

import java.io.File;
import java.io.IOException;

public final class AvatarUtils {

    private AvatarUtils() {

    }

    public static String getAvatarName(HttpServletRequest req) throws IOException, ServletException {
        Part avatar = req.getPart(EntityConstants.USER_AVATAR);
        
        return avatar.getName();
    }

    public static void uploadAvatar(HttpServletRequest req, User user, String uploadPath) throws IOException, ServletException {
        Part avatar = req.getPart(EntityConstants.USER_AVATAR);

        String path = uploadPath + File.separator + user.getEmail() + user.getAvatarName();
        avatar.write(path);
    }
}
