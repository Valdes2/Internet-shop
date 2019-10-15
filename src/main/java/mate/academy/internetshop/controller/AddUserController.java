package mate.academy.internetshop.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.model.Role;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.BucketService;
import mate.academy.internetshop.service.UserService;
import mate.academy.internetshop.util.HashUtil;

public class AddUserController extends HttpServlet {

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
        if (newUser.getLogin().equals("admin")) {
            newUser.addRole(Role.of("ADMIN"));
        } else {
            newUser.addRole(Role.of("USER"));
        }
        newUser = userService.create(newUser);
        Bucket bucket = new Bucket();
        bucket.setUser(newUser);
        bucketService.create(bucket);
        newUser.setBucket(bucket);
        userService.update(newUser);

        resp.sendRedirect(req.getContextPath() + "/login");

    }
}
