package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.ProductDAO;
import model.entity.Product;

import java.io.IOException;
import java.util.List;

@WebServlet(name ="OrderServlet", value = "/Cart")
public class OrderServlet  extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("Cart.jsp").forward(request, response);
    }
}
