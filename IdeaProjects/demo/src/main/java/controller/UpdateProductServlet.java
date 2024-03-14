package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import model.dao.ProductDAO;
import model.entity.Product;
import util.ConvertIMG;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static util.ConvertIMG.saveImage;
@MultipartConfig
@WebServlet(name = "UpdateProductServlet", value = "/UpdateProduct")
public class UpdateProductServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idProductStr = request.getParameter("pid");
        // Khởi tạo ProductDAO
        int idConvert=Integer.parseInt(idProductStr);
        Product product=getProductById(idConvert);
        request.setAttribute("product", product);
        request.getRequestDispatcher("UpdateProduct.jsp").forward(request, response);

    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        ProductDAO productDAO = null;
        try {
            productDAO = new ProductDAO();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Part filePart = request.getPart("image-input");
        String productId=request.getParameter("ProductId");
        String productName=request.getParameter("ProductName");
        String description=request.getParameter("Description");
        String price=request.getParameter("Price");
        BigDecimal bigDecimalPrice=BigDecimal.ZERO;
        int idConvertProduct=Integer.parseInt(productId);

        Product product=getProductById(idConvertProduct);

        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        boolean isPriceTrue=true;
        try {
            // Convert the string to BigDecimal
            bigDecimalPrice= new BigDecimal(price);
            // Print the BigDecimal value

        } catch (NumberFormatException e) {
            // Handle NumberFormatException
            isPriceTrue=false;
            e.printStackTrace();
        }

        LocalDateTime CreateDate = LocalDateTime.now();
        Date currentDate = Date.from(CreateDate.atZone(java.time.ZoneId.systemDefault()).toInstant());
        java.sql.Date currentDateSql = new java.sql.Date(currentDate.getTime());
        String relativePath="";
        if (filePart != null) {
            // Get the name of the uploaded file
            Part filePartLocal = request.getPart("images");
            if (filePart.getSize() > 0) { // Kiểm tra xem người dùng có tải lên hình ảnh mới không
                relativePath = ConvertIMG.saveImage(filePart, request, "images"); // Nếu có, lưu hình ảnh mới
            } else {
                relativePath = product.getIMG(); // Nếu không, giữ nguyên hình ảnh hiện tại
            }
        }
        if(productName==null || productName.isEmpty() ||description==null|| description.isEmpty() ||price==null|| price.isEmpty()){
            out.print("Please fill all input");
        }
        else if(!isPriceTrue){
            out.print("Wrong input price");
        }

        else{
            Product updateProduct= new Product(idConvertProduct,productName,bigDecimalPrice,relativePath,description,product.getCreatedDate(),product.getCreatedBy(),currentDateSql,"admin",false,null,null);
            boolean isUpdateSuccess= productDAO.updateProduct(updateProduct);
            if(isUpdateSuccess){
                out.print("success");
            }

        }
        out.flush();
        out.close();

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
    public boolean checkNameExist(String productName){
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
            if (product.getProductName().equals(productName)) {
                foundProduct = product;
                break; // Dừng lặp khi tìm thấy sản phẩm
            }
        }

        // Kiểm tra xem sản phẩm có được tìm thấy không
        if (foundProduct != null) {
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
