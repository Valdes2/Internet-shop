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

public class AddUserContrroller extends HttpServlet {

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
        User currentUser = new User(name, login, password);
        Bucket bucket = new Bucket(currentUser.getId());
        bucketService.create(bucket);
        currentUser.setBucketId(bucket.getId());
        User user = userService.create(currentUser);

        if (currentUser.getLogin().equals("admin")) {
            currentUser.addRole(Role.of("ADMIN"));
        } else {
            currentUser.addRole(Role.of("USER"));
        }

        resp.sendRedirect(req.getContextPath() + "/login");

    }
}
