package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.OrderDAO;
import model.dao.ProductDAO;

import java.io.IOException;

@WebServlet(name = "UpdateOrderServlet", value = "/UpdateOrder")
public class UpdateOrderServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OrderDAO orderDAO = null;
        try {
            orderDAO = new OrderDAO();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        String IsPayment=request.getParameter("IsPayment");
        String DeliveryStatus=request.getParameter("DeliveryStatus");

    }
}
