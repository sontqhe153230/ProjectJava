package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.dao.ProductDAO;
import model.entity.Product;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name ="OrderServlet", value = "/Cart")
public class OrderServlet  extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        //check nếu chưa đăng nhap ,quay lai trang dang nhap
        if(session.getAttribute("loggedInUser")==""||session.getAttribute("loggedInUser")==null){
            request.getRequestDispatcher("Login.jsp").forward(request, response);
        }

        if(request.getParameter("id")==null||request.getParameter("id")==""
        ||request.getParameter("quantity")==null||request.getParameter("quantity")==""
        ){
            response.sendRedirect("Cart.jsp");
        }
        else{
            // Lấy id sản phẩm và số lượng từ request
            int productId = Integer.parseInt(request.getParameter("id"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));

            // Lấy hoặc tạo giỏ hàng từ session
            Map<Integer, Integer> cart = (Map<Integer, Integer>) session.getAttribute("cart");
            if (cart == null) {
                cart = new HashMap<>();
                session.setAttribute("cart", cart);
            }

            // Kiểm tra xem sản phẩm đã tồn tại trong giỏ hàng chưa
            if (cart.containsKey(productId)) {
                // Nếu đã tồn tại, cập nhật số lượng
                int existingQuantity = cart.get(productId);
                cart.put(productId, existingQuantity + quantity);
            } else {
                // Nếu chưa tồn tại, thêm sản phẩm mới vào giỏ hàng
                cart.put(productId, quantity);
            }

            // Chuyển hướng đến trang giỏ hàng
            response.sendRedirect("Cart.jsp"); // Chuyển hướng đến trang giỏ hàng
        }


    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String pId = request.getParameter("productId");
        int productId = Integer.parseInt(pId);
        HttpSession session = request.getSession(true);
        Map<Integer, Integer> cart = (Map<Integer, Integer>) session.getAttribute("cart");
        if (cart.containsKey(productId)) {
            cart.remove(productId);
            // Update the session after removing the item
            session.setAttribute("cart", cart);

        } else {

        }

    }

    //hàm xử lí để get product bằng product id
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

//hàm xử lí khi fe chuyển id về để get name( new OrderServlet().GetNameById(productId)--> bên FE)
    public String GetNameById(int id) {
      Product p= GetProductById(id);
      if(p!=null){
          return p.getProductName();
      }
      return "";
    }
    //hàm xử lí khi fe chuyển id về để get price ( new OrderServlet().GetPrice(productId)--> bên FE)
    public BigDecimal GetPrice(int id){
        Product p= GetProductById(id);
        if(p!=null){
            return p.getPrice();
        }
        return null;
    }
    //hàm xử lí khi fe chuyển id về để get price ( new OrderServlet().GetTotalPrice(productId,quantity)--> bên FE)
    public BigDecimal GetTotalPrice(int id,int quantity){
        Product p= GetProductById(id);
        if(p!=null){
            BigDecimal quantityBigDecimal = BigDecimal.valueOf(quantity);

            // Perform multiplication
            BigDecimal total = p.getPrice().multiply(quantityBigDecimal);
            return total;
        }
        return null;
    }

    //hàm xử lí khi fe chuyển id về để get url bang ProductId( new OrderServlet().GetUrlByProductId(productId,quantity)--> bên FE)
    public String GetUrlByProductId(int id){
        Product p= GetProductById(id);
        if(p!=null){
            return "assets/images/"+p.getIMG();
        }
        return null;
    }


    public BigDecimal GetOfTotalCart(Map<Integer, Integer> cart){
         BigDecimal total=BigDecimal.ZERO;
        // Lấy  giỏ hàng từ session
        if (cart != null) {
            // Lặp qua mỗi mục trong giỏ hàng và hiển thị
            for (Map.Entry<Integer, Integer> entry : cart.entrySet()) {
                int productId = entry.getKey();
                int quantity = entry.getValue();
              total=total.add(GetTotalPrice(productId,quantity));
            }
            return total;
        }
        return null;
    }
}
