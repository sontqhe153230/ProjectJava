package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.ProductDAO;
import model.entity.Order;
import model.entity.Product;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@WebServlet(name = "DeleteProductServlet", urlPatterns = {"/DeleteProduct"})
public class DeleteProductServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductDAO productDAO = null;
        try {
            productDAO = new ProductDAO();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        String id=request.getParameter("pid");
        int idConvert=Integer.parseInt((id));
        Product updateProduct=getProductById(idConvert);
        LocalDateTime now = LocalDateTime.now();
        java.sql.Date sqlDate = java.sql.Date.valueOf(now.toLocalDate());
        Product update= new Product(idConvert,updateProduct.getProductName(),updateProduct.getPrice(),updateProduct.getIMG(),updateProduct.getDescription(),updateProduct.getCreatedDate(),updateProduct.getCreatedBy(),updateProduct.getUpdatedDate(),updateProduct.getUpdatedBy(),true,sqlDate,"admin");
        boolean isCheckComplete=productDAO.updateProduct(update);
        response.sendRedirect("AdminManageProduct");
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    public Product getProductById(int id){
        ProductDAO productDAO = null;
        try {
            productDAO = new ProductDAO();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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
            return  null;
        }
    }

}
