package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.AccountDAO;
import util.PasswordUtil;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@WebServlet(name = "AddNewAccountServlet", value = "/AddNewAccount")
public class AddNewAccountServlet extends HttpServlet {
    AccountDAO accountDAO = new AccountDAO();

    public AddNewAccountServlet() throws Exception {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("AddNewAccount.jsp").forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String ReEnterPassword = request.getParameter("ReEnterPassword");
        String hassPass = "";
        String name=request.getParameter("name");
        String address=request.getParameter("address");
        String email=request.getParameter("email");
        String phone=request.getParameter("phone");

        if (password.equals(ReEnterPassword)) {
            if(accountDAO.existsByUsername(username)){
                request.setAttribute("error", "Username already exists");
                request.getRequestDispatcher("view/account-register.jsp").forward(request, response);
                return;
            }
            try {
                hassPass = PasswordUtil.generateStrongPasswordHash(password);
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            } catch (InvalidKeySpecException e) {
                throw new RuntimeException(e);
            }
            accountDAO.createAccount(username, hassPass, "admin");
            response.sendRedirect("login");
        } else {
            request.setAttribute("error", "Password does not match");
            request.getRequestDispatcher("AddNewAccount.jsp").forward(request, response);

        }

    }

}
