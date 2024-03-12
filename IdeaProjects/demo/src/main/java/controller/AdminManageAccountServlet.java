package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.AccountDAO;
import model.dao.CustomerDAO;
import model.dao.ProductDAO;
import model.entity.Account;
import model.entity.Customer;
import model.entity.Product;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "AdminManageAccountServlet", urlPatterns = {"/Admin"})
public class AdminManageAccountServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AccountDAO accountDAO = null;
        try {
            accountDAO = new AccountDAO();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        List<Account> accountList = accountDAO.getAllAccount();

        request.setAttribute("accountList", accountList);
        request.getRequestDispatcher("ManageAccount.jsp").forward(request, response);
    }
    //hàm xử lí để get product bằng product id
    public Customer GetCustomerById(int id){
        CustomerDAO customer = null;
        try {
            customer = new CustomerDAO();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // Lấy tất cả sản phẩm từ cơ sở dữ liệu
        List<Customer> customerList = customer.getAllCustomers();

        // Khởi tạo biến để lưu trữ sản phẩm tìm thấy
        Customer foundCustomer = null;

        // Lặp qua productList để tìm sản phẩm có ID phù hợp
        for (Customer customer1 : customerList) {
            if (customer1.getAccountID() == id) {
                foundCustomer = customer1;
                break; // Dừng lặp khi tìm thấy sản phẩm
            }
        }

        // Kiểm tra xem sản phẩm có được tìm thấy không
        if (foundCustomer != null) {
            // Nếu tìm thấy, đặt sản phẩm vào thuộc tính của request
            return foundCustomer;
        } else {
            // Nếu không tìm thấy, xử lý trường hợp (ví dụ: hiển thị thông báo lỗi)
            // Bạn có thể chuyển hướng đến trang lỗi hoặc xử lý nó bằng bất kỳ cách nào phù hợp khác
            // Ví dụ:
            return null;
        }
    }

    public String GetNameById(int id){
        Customer customer=GetCustomerById(id);
        if(customer!=null){
            return customer.getCustomerName();
        }
        return "";
    }
    public String GetAddressById(int id){
        Customer customer=GetCustomerById(id);
        if(customer!=null){
            return customer.getAddress();
        }
        return "";
    }
    public String GetEmailById(int id){
        Customer customer=GetCustomerById(id);
        if(customer!=null){
            return customer.getEmail();
        }
        return "";
    }
    public String GetPhoneById(int id){
        Customer customer=GetCustomerById(id);
        if(customer!=null){
            return customer.getPhone();
        }
        return "";
    }

}
