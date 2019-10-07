package mate.academy.internetshop.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.BucketService;
import mate.academy.internetshop.service.UserService;
import mate.academy.internetshop.util.HashUtil;

public class AddUserContrroller extends HttpServlet {
    private static final Long ADMIN = 1L;
    private static final Long USER = 2L;

    @Inject
    public static UserService userService;

    @Inject
    public static BucketService bucketService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/addUser.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String password = req.getParameter("pass");
        byte[] salt = HashUtil.getSalt();
        String hashPass = HashUtil.hashPassword(password, salt);
        User newUser = new User(name, login, hashPass);
        newUser.setSalt(salt);
        newUser = userService.create(newUser);
        if (newUser.getLogin().equals("admin")) {
            userService.addRole(newUser, ADMIN);
        } else {
            userService.addRole(newUser, USER);
        }

        Bucket bucket = bucketService.create(new Bucket(newUser.getId()));
        newUser.setBucketId(bucket.getId());
        userService.update(newUser);

        resp.sendRedirect(req.getContextPath() + "/login");

    }
}
