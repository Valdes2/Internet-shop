package mate.academy.internetshop.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.service.OrderService;

public class DeleteOrderController extends HttpServlet {

    @Inject
    private  static OrderService orderService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long orderId = Long.valueOf(req.getParameter("order_id"));
        orderService.deleteUserOrder(orderId);
        orderService.delete(orderId);
        resp.sendRedirect(req.getContextPath() + "/ordersList");
    }
}
