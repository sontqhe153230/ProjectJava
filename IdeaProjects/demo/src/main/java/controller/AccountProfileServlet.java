package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.dao.AccountDAO;
import model.dao.CustomerDAO;
import model.entity.Account;
import model.entity.Customer;
import util.ConvertIMG;

import java.io.File;
import java.io.IOException;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10,      // 10MB
        maxRequestSize = 1024 * 1024 * 50)   // 50MB
@WebServlet(name = "AccountProfileServlet", value = "/profile")
public class AccountProfileServlet extends HttpServlet {
    AccountDAO accountDAO = new AccountDAO();

    CustomerDAO customerDAO = new CustomerDAO();

    public AccountProfileServlet() throws Exception {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("id");
        HttpSession session = request.getSession();
        Account loggedInUser = (Account) session.getAttribute("loggedInUser");
        if (loggedInUser != null && userId == null || userId.isEmpty()) {
            request.setAttribute("account", accountDAO.get(loggedInUser.getAccountId()));
            request.setAttribute("customer", customerDAO.get(loggedInUser.getAccountId()));
            request.getRequestDispatcher("Profile.jsp").forward(request, response);
        }  else {
            response.sendRedirect("Login");
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        Part img = request.getPart("img");
        String oldImg = request.getParameter("oldIMG");

        if(img != null && img.getSize() > 0){
            String relativePath = ConvertIMG.saveImage(img, request, "assets/images");
            if (customerDAO.update(id, name, email, phone, address)) {
                accountDAO.updateIMG(id, relativePath);
                response.sendRedirect("profile");
            } else {
                response.sendRedirect("profile");
            }
        } else {
            if (customerDAO.update(id, name, email, phone, address)) {
                accountDAO.updateIMG(id, oldImg);
                response.sendRedirect("profile");
            } else {
                response.sendRedirect("profile");
            }
        }

    }
}
