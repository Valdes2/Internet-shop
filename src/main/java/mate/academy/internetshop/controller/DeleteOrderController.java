package mate.academy.internetshop.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.model.Order;
import mate.academy.internetshop.service.OrderService;
import mate.academy.internetshop.service.UserService;

public class DeleteOrderController extends HttpServlet {

    @Inject
    private  static OrderService orderService;

    @Inject
    private static UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String orderId = req.getParameter("order_id");
        Long userId = orderService.get(Long.valueOf(orderId)).getUserId();
        Order order = orderService.get(Long.valueOf(orderId));
        orderService.delete(Long.valueOf(orderId));
        userService.getOrders(userId).remove(order);
        resp.sendRedirect(req.getContextPath() + "/ordersList");
    }
}
