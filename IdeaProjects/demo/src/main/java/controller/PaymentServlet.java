package controller;

import com.google.zxing.WriterException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.dao.OrderDAO;
import model.dao.ProductDAO;
import model.entity.Account;
import model.entity.Order;
import model.entity.Product;
import util.QRGenerate;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

@WebServlet(name ="PaymentServlet", value = "/Payment")
public class PaymentServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("Payment.jsp").forward(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Set scanned flag in session to true
        String status = request.getParameter("status");
        if(status!=""){
            HttpSession session = request.getSession(true);
            Map<Integer, Integer> cart = (Map<Integer, Integer>) session.getAttribute("cart");

            if (cart != null) {
                // Do something with the cart
                // For example, you can iterate over the cart entries

                OrderDAO orderDAO = null;
                try {
                    orderDAO = new OrderDAO();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

                for (Map.Entry<Integer, Integer> entry : cart.entrySet()) {
                    Integer productId = entry.getKey();
                    Integer quantity = entry.getValue();
                    int OrderId=RandomNumber();
                    if(checkOrderIdExist(OrderId)){
                        OrderId=RandomNumber();
                    }
                    Account account=(Account) session.getAttribute("loggedInUser");
                    int CustomerId=account.getAccountId();
                    String description="Order to Product"+productId;
                    LocalDateTime CreateDate = LocalDateTime.now();
                    Date currentDate = Date.from(CreateDate.atZone(java.time.ZoneId.systemDefault()).toInstant());
                    java.sql.Date currentDateSql = new java.sql.Date(currentDate.getTime());
                    String CreateBy="user";

                    Order newOrder=new Order(OrderId,productId,CustomerId,quantity,description,false,"Pending",currentDateSql,CreateBy,null,null,false,null,null);

                    boolean idAddSuccess=orderDAO.addOrder(newOrder);
                    response.setContentType("text/plain");
                    PrintWriter out = response.getWriter();
                    if(idAddSuccess){
                        out.print("success");

                    }
                    // Do something with productId and quantity
                     else{
                        out.print("failure");
                    }

                }
            } else {
                // Cart is empty or not yet initialized
                // Handle this case accordingly
            }

        }
    }
    public int RandomNumber(){

        Random random = new Random();

        // Generate a random integer between 0 (inclusive) and 100 (exclusive)
        int randomNumber = random.nextInt(99999) + 1;
        return randomNumber;
    }
    public boolean checkOrderIdExist(int id){
        OrderDAO orderDAO = null;
        try {
            orderDAO = new OrderDAO();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // Lấy tất cả sản phẩm từ cơ sở dữ liệu
        List<Order> orderList = orderDAO.getAllOrder();

        // Khởi tạo biến để lưu trữ sản phẩm tìm thấy
        Order foundOrder = null;

        // Lặp qua productList để tìm sản phẩm có ID phù hợp
        for (Order order : orderList) {
            if (order.getOrderId() == id) {
                foundOrder = order;
                break; // Dừng lặp khi tìm thấy sản phẩm
            }
        }

        // Kiểm tra xem sản phẩm có được tìm thấy không
        if (foundOrder != null) {
            // Nếu tìm thấy, đặt sản phẩm vào thuộc tính của request
            return true;
        } else {
            // Nếu không tìm thấy, xử lý trường hợp (ví dụ: hiển thị thông báo lỗi)
            // Bạn có thể chuyển hướng đến trang lỗi hoặc xử lý nó bằng bất kỳ cách nào phù hợp khác
            // Ví dụ:
            return false;
        }

    }
}
