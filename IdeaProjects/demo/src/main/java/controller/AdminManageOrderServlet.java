package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.AccountDAO;
import model.dao.CustomerDAO;
import model.dao.OrderDAO;
import model.dao.ProductDAO;
import model.entity.Account;
import model.entity.Customer;
import model.entity.Order;
import model.entity.Product;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "AdminManageOrderServlet", urlPatterns = {"/AdminManageOrder"})
public class AdminManageOrderServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OrderDAO orderDAO = null;
        try {
            orderDAO = new OrderDAO();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        List<Order> orderList = orderDAO.getAllOrder();

        request.setAttribute("orderList", orderList);
        request.getRequestDispatcher("ManageOrder.jsp").forward(request, response);
    }
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
    public Product GetProductById(int id){
        ProductDAO productDAO = null;
        try {
            productDAO = new ProductDAO();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // Lấy tất cả sản phẩm từ cơ sở dữ liệu
        List<Product> productList = productDAO.getAllProducts();

        // Khởi tạo biến để lưu trữ sản phẩm tìm thấy
        Product foundProduct = null;

        // Lặp qua productList để tìm sản phẩm có ID phù hợp
        for (Product product : productList) {
            if (product.getProductID() == id) {
                foundProduct = product;
                break; // Dừng lặp khi tìm thấy sản phẩm
            }
        }

        // Kiểm tra xem sản phẩm có được tìm thấy không
        if (foundProduct != null) {
            // Nếu tìm thấy, đặt sản phẩm vào thuộc tính của request
            return foundProduct;
        } else {
            // Nếu không tìm thấy, xử lý trường hợp (ví dụ: hiển thị thông báo lỗi)
            // Bạn có thể chuyển hướng đến trang lỗi hoặc xử lý nó bằng bất kỳ cách nào phù hợp khác
            // Ví dụ:
            return null;
        }
    }
    public String getProductNameById(int id){
        Product p= GetProductById(id);
        if(p!=null){
            return p.getProductName();
        }
        return null;
    }
    public String getCustomerNameById(int id){
        Customer c = GetCustomerById(id);
        if(c!=null){
            return c.getCustomerName();
        }
        return null;
    }
}
