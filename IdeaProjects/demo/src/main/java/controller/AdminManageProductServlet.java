package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.*;
import model.entity.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "AdminManageProductServlet", urlPatterns = {"/AdminManageProduct"})
public class AdminManageProductServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductDAO productDAO = null;
        try {
            productDAO = new ProductDAO();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        List<Product> productList = productDAO.getAllProducts();

        request.setAttribute("productList", productList);
        request.getRequestDispatcher("ManageProduct.jsp").forward(request, response);
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
    public String GetSizeByProductId(int id){
        Size s= GetSizeById(id);
        if(s!=null){
            return s.getSize();
        }
        return null;
    }
    public String GetColorByProductId(int id){
        Color c= GetColorById(id);
        if(c!=null){
            return c.getColor();
        }
        return null;
    }
    }

