package com.infostroy.shcherbakov.controller;

import com.infostroy.shcherbakov.constants.ContextConstants;
import com.infostroy.shcherbakov.constants.PathConstants;
import com.infostroy.shcherbakov.constants.ServletConstants;
import com.infostroy.shcherbakov.model.entity.User;
import com.infostroy.shcherbakov.services.UserService;
import com.infostroy.shcherbakov.services.exception.ServiceException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class UploadAvatarServlet extends HttpServlet {

    private String uploadDir;
    private UserService userService;

    @Override
    public void init() throws ServletException {
        uploadDir = (String) this.getServletContext().getAttribute(ContextConstants.UPLOAD_PATH);
        userService = (UserService) this.getServletContext().getAttribute(ContextConstants.USER_SERVICE);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute(ServletConstants.SESSION_CURRENT_USER);

        File avatarImg = new File(uploadDir + File.separator + user.getEmail() + user.getAvatarName());

        if (!avatarImg.exists()) {
            avatarImg = new File(uploadDir + File.separator + PathConstants.STANDARD_AVATAR_NAME);
        }

        BufferedImage avatar = ImageIO.read(avatarImg);

        ServletOutputStream out = resp.getOutputStream();

        ImageIO.write(avatar, "png", out);

        out.flush();
        out.close();
    }
}
