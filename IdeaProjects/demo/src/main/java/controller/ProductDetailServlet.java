package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.ColorDAO;
import model.dao.ProductDAO;
import model.dao.SizeDAO;
import model.entity.Color;
import model.entity.Product;
import model.entity.Size;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@WebServlet(name ="ProductDetailServlet", value = "/product-detail")

public class ProductDetailServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy tham số ID sản phẩm từ request
        String idProductStr = request.getParameter("pid");

        // Parse idProductStr to int
        int idProduct = Integer.parseInt(idProductStr);

        // Khởi tạo ProductDAO
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
            if (product.getProductID() == idProduct) {
                foundProduct = product;
                break; // Dừng lặp khi tìm thấy sản phẩm
            }
        }

        // Kiểm tra xem sản phẩm có được tìm thấy không
        if (foundProduct != null) {
            // Nếu tìm thấy, đặt sản phẩm vào thuộc tính của request
            request.setAttribute("product", foundProduct);
        } else {
            // Nếu không tìm thấy, xử lý trường hợp (ví dụ: hiển thị thông báo lỗi)
            // Bạn có thể chuyển hướng đến trang lỗi hoặc xử lý nó bằng bất kỳ cách nào phù hợp khác
            // Ví dụ:
            request.setAttribute("errorMessage", "Không tìm thấy sản phẩm");
        }

        // Chuyển tiếp request đến trang "ProductDetail.jsp"
        request.getRequestDispatcher("ProductDetail.jsp").forward(request, response);
    }

    public List<Size>getAllSizeByProductId(int id){
        SizeDAO sizeDAO = null;
        try {
            sizeDAO = new SizeDAO();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // Lấy tất cả sản phẩm từ cơ sở dữ liệu
        List<Size> sizeList = sizeDAO.getAllSizes();

        // Khởi tạo biến để lưu trữ sản phẩm tìm thấy
        List<Size> foundSize  = new ArrayList<>();;

        // Lặp qua productList để tìm sản phẩm có ID phù hợp
        for (Size size : sizeList) {
            if (size.getProductID() == id) {
                foundSize.add(size);

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

    public List<Color>getAllColorByProductId(int id){
        ColorDAO colorDAO = null;
        try {
            colorDAO = new ColorDAO();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // Lấy tất cả sản phẩm từ cơ sở dữ liệu
        List<Color> colorList = colorDAO.getAllColors();

        // Khởi tạo biến để lưu trữ sản phẩm tìm thấy
        List<Color> foundColor = new ArrayList<>();;

        // Lặp qua productList để tìm sản phẩm có ID phù hợp
        for (Color color : colorList) {
            if (color.getProductID() == id) {
                foundColor.add(color);
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
}
