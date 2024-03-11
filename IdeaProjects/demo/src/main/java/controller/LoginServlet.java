package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import model.dao.AccountDao;
import model.entity.Account;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name ="LoginServlet", value = "/Login")
public class LoginServlet extends HttpServlet {
     protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("Login.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy tên người dùng và mật khẩu từ biểu mẫu
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Lấy tài khoản dựa trên tên người dùng và mật khẩu
        Account account = getAccountByUserAndPass(username, password);

        // Chuẩn bị loại nội dung phản hồi
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();

        if (account != null) {
            // Nếu tài khoản được tìm thấy, đặt nó trong session
            request.getSession().setAttribute("loggedInUser", account);
            out.print("success");
        } else {
            // Nếu không tìm thấy tài khoản, gửi thông báo thất bại
            out.print("failure");
        }
        out.flush();
        out.close();
    }

    public Account getAccountByUserAndPass(String username, String password) {
        AccountDao accountDAO = null;
        try {
            accountDAO = new AccountDao();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // Lấy tất cả các tài khoản từ cơ sở dữ liệu
        List<Account> accountList = accountDAO.getAllAccount();

        // Khởi tạo biến để lưu trữ tài khoản tìm thấy
        Account foundAccount = null;

        // Duyệt qua danh sách tài khoản để tìm tài khoản với tên người dùng và mật khẩu phù hợp
        for (Account account : accountList) {
            if (account.getUsername().equals(username) && account.getPassword().equals(password)) {
                foundAccount = account;
                break; // Dừng vòng lặp khi tìm thấy tài khoản
            }
        }

        // Trả về tài khoản đã tìm thấy hoặc null nếu không tìm thấy
        return foundAccount;
    }
}
