package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import model.dao.ProductDAO;
import model.entity.Product;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
@WebServlet(name ="ProductListServlet", value = "/productList")
public class ProductListServlet extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<h1>Hello, World!</h1>");



//        ProductDAO productDAO = null;
//        try {
//            productDAO = new ProductDAO();
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//        List<Product> productList = productDAO.getAllProducts();
//
//        request.setAttribute("productList", productList);
//        request.getRequestDispatcher("views/products.jsp").forward(request, response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}