package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.dao.*;
import model.entity.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name ="OrderServlet", value = "/Cart")
public class OrderServlet  extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
// Check if the user is logged in, if not, redirect to the login page
        if (session.getAttribute("loggedInUser") == null || session.getAttribute("loggedInUser").equals("")) {
            request.getRequestDispatcher("Login.jsp").forward(request, response);
        }
        Account account = (Account) session.getAttribute("loggedInUser");
        CustomerDAO customerDAO = new CustomerDAO();
        Customer customer = customerDAO.get(account.getAccountId());

        if (request.getParameter("id") == null || request.getParameter("id").equals("")
                || request.getParameter("quantity") == null || request.getParameter("quantity").equals("")
                || request.getParameter("size") == null || request.getParameter("size").equals("")
                || request.getParameter("color") == null || request.getParameter("color").equals("")) {
            request.setAttribute("orderList", customerDAO.getOrderByCid(customer.getCustomerID()));
            request.getRequestDispatcher("Cart.jsp").forward(request, response);
        } else {
            // Get product ID, quantity, size, and color from the request
            int productId = Integer.parseInt(request.getParameter("id"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            int size = Integer.parseInt(request.getParameter("size"));
            int color = Integer.parseInt(request.getParameter("color"));

            // Get or create the cart from session
            Map<Integer, Map<String, Object>> cart = (Map<Integer, Map<String, Object>>) session.getAttribute("cart");
            if (cart == null) {
                cart = new HashMap<>();
                session.setAttribute("cart", cart);
            }

            // Check if the product already exists in the cart
            if (cart.containsKey(productId)) {
                // If the product already exists, update the quantity
                Map<String, Object> productInfo = cart.get(productId);
                int existingQuantity = (int) productInfo.get("quantity");
                productInfo.put("quantity", existingQuantity + quantity);
            } else {
                // If the product does not exist, add it to the cart
                Map<String, Object> productInfo = new HashMap<>();
                productInfo.put("quantity", quantity);
                productInfo.put("size", size);
                productInfo.put("color", color);
                cart.put(productId, productInfo);
            }

            // Redirect to the cart page
            try {
                OrderDAO orderDAO = new OrderDAO();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            request.setAttribute("orderList", customerDAO.getOrderByCid(customer.getCustomerID()));
            request.getRequestDispatcher("Cart.jsp").forward(request, response);
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

    public Size GetSizeById(int id){
        SizeDAO sizeDAO = null;
        try {
            sizeDAO = new SizeDAO();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // Lấy tất cả sản phẩm từ cơ sở dữ liệu
        List<Size> sizeList = sizeDAO.getAllSizes();

        // Khởi tạo biến để lưu trữ sản phẩm tìm thấy
        Size foundSize = null;

        // Lặp qua productList để tìm sản phẩm có ID phù hợp
        for (Size size : sizeList) {
            if (size.getProductID() == id) {
                foundSize = size;
                break; // Dừng lặp khi tìm thấy sản phẩm
            }
        }

        // Kiểm tra xem sản phẩm có được tìm thấy không
        if (foundSize != null) {
            // Nếu tìm thấy, đặt sản phẩm vào thuộc tính của request
            return foundSize;
        } else {
            // Nếu không tìm thấy, xử lý trường hợp (ví dụ: hiển thị thông báo lỗi)
            // Bạn có thể chuyển hướng đến trang lỗi hoặc xử lý nó bằng bất kỳ cách nào phù hợp khác
            // Ví dụ:
            return null;
        }
    }

    public Color GetColorById(int id){
        ColorDAO colorDAO = null;
        try {
            colorDAO = new ColorDAO();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // Lấy tất cả sản phẩm từ cơ sở dữ liệu
        List<Color> colorList = colorDAO.getAllColors();

        // Khởi tạo biến để lưu trữ sản phẩm tìm thấy
        Color foundColor = null;

        // Lặp qua productList để tìm sản phẩm có ID phù hợp
        for (Color color : colorList) {
            if (color.getProductID() == id) {
                foundColor = color;
                break; // Dừng lặp khi tìm thấy sản phẩm
            }
        }

        // Kiểm tra xem sản phẩm có được tìm thấy không
        if (foundColor != null) {
            // Nếu tìm thấy, đặt sản phẩm vào thuộc tính của request
            return foundColor;
        } else {
            // Nếu không tìm thấy, xử lý trường hợp (ví dụ: hiển thị thông báo lỗi)
            // Bạn có thể chuyển hướng đến trang lỗi hoặc xử lý nó bằng bất kỳ cách nào phù hợp khác
            // Ví dụ:
            return null;
        }
    }

    public String GetColorTypeById(int id){
        Color p= GetColorById(id);
        if(p!=null){
            return p.getColor();
        }
        return "";
    }

    public String GetSizeTypeById(int id){
        Size p= GetSizeById(id);
        if(p!=null){
            return p.getSize();
        }
        return "";
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


    public BigDecimal GetOfTotalCart(Map<Integer, Map<String, Object>> cart){
         BigDecimal total=BigDecimal.ZERO;
        // Lấy  giỏ hàng từ session
        if (cart != null) {
            // Lặp qua mỗi mục trong giỏ hàng và hiển thị
            for (Map.Entry<Integer, Map<String, Object>> entry : cart.entrySet()) {
                int productId = entry.getKey();
                int quantity = (int) entry.getValue().get("quantity");


                total=total.add(GetTotalPrice(productId,quantity));
            }

            return total;
        }
        return null;
    }
}
