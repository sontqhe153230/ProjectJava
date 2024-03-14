package controller;

import model.dao.AccountDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.dao.CustomerDAO;
import util.PasswordUtil;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@WebServlet(name = "AccountRegisterServlet", value = "/register")
public class AccountRegisterServlet extends HttpServlet {

    AccountDAO accountDAO = new AccountDAO();

    CustomerDAO customerDAO = new CustomerDAO();

    public AccountRegisterServlet() throws Exception {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("Register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String ReEnterPassword = request.getParameter("ReEnterPassword");
        String hassPass = "";

        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();

        if (password.equals(ReEnterPassword)) {
            if(accountDAO.existsByUsername(username)){
                out.print("failure");
                out.flush();
                out.close();
                return;
            }
            try {
                hassPass = PasswordUtil.generateStrongPasswordHash(password);
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            } catch (InvalidKeySpecException e) {
                throw new RuntimeException(e);
            }
            accountDAO.createAccount(username, hassPass, "user");
            out.print("user");
        } else {
            out.print("failure");
        }

        out.flush();
        out.close();
    }
}
