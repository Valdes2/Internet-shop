package mate.academy.internetshop.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mate.academy.internetshop.db.Storage;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.service.BucketService;

public class DeleteFromBucketController extends HttpServlet {
    public static final int BUCKET_INDEX = 0;

    @Inject
    private static BucketService bucketService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String userId = req.getParameter("item_id");
        Long bucketId = Storage.buckets.get(BUCKET_INDEX).getId();
        bucketService.deleteItem(bucketId, Long.valueOf(userId));
        resp.sendRedirect(req.getContextPath() + "/bucket");

    }
}
