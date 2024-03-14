package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import model.dao.*;
import model.entity.Color;
import model.entity.Product;
import model.entity.ProductType;
import model.entity.Size;
import org.json.JSONArray;
import org.json.JSONObject;
import util.ConvertIMG;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
@MultipartConfig
@WebServlet(name = "AddNewProductServlet", value = "/AddNewProduct")
public class AddNewProductServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("AddNewProduct.jsp").forward(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
         List<String> ListSizeOption=new ArrayList<>();
        List<String> ListColorOption=new ArrayList<>();
        List<String> ListProductTypeOption=new ArrayList<>();

        ProductDAO productDAO = null;
        try {
            productDAO = new ProductDAO();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        // Parse the JSON data sent from the client

        // Parse JSON data into two lists of options
        String optionsSizeJson = request.getParameter("optionsSize");
        String optionsColorJson = request.getParameter("optionsColor");
        String optionsProductTypeJson = request.getParameter("optionsProductType");

// Decode URL-encoded JSON strings and parse them into arrays
        JSONArray optionsSize = new JSONArray(optionsSizeJson);
        JSONArray optionsColor = new JSONArray(optionsColorJson);
        JSONArray optionsProductType = new JSONArray(optionsProductTypeJson);

        // Now you can process both lists of options as needed
        for (int i = 0; i < optionsSize.length(); i++) {
            String optionSize = optionsSize.getString(i);
            ListSizeOption.add(optionSize);
            // Do something with optionSize
        }

        for (int i = 0; i < optionsColor.length(); i++) {
            String optionColor = optionsColor.getString(i);
           ListColorOption.add(optionColor);
            // Do something with optionColor
        }
        for (int i = 0; i < optionsProductType.length(); i++) {
            String optionProductType = optionsProductType.getString(i);
            ListProductTypeOption.add(optionProductType);
            // Do something with optionColor
        }
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();

        Part filePart = request.getPart("image-input");
        String filename="";
        String productName=request.getParameter("ProductName");
        String description=request.getParameter("Description");
        String price=request.getParameter("Price");
        BigDecimal bigDecimalPrice=BigDecimal.ZERO;
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
        // Process the uploaded file
        if (filePart != null) {
            // Get the name of the uploaded file
            Part filePartLocal = request.getPart("images");
            if (filePart.getSize() > 0) { // Kiểm tra xem người dùng có tải lên hình ảnh mới không
                filename = ConvertIMG.saveImage(filePart, request, "images"); // Nếu có, lưu hình ảnh mới
            }
            out.print("Please fill all input");
        }
        if(productName==null || productName.isEmpty() ||description==null|| description.isEmpty() ||price==null|| price.isEmpty() || Objects.equals(filename, "") || optionsSizeJson.isEmpty()||optionsProductTypeJson.isEmpty()){
            out.print("Please fill all input");
        }
        else if(!isPriceTrue){
            out.print("Wrong input price");
        }
        else if(checkNameExist(productName)){
            out.print("Product name is already exist");
        }
        else if(ListSizeOption.isEmpty() || ListColorOption.isEmpty() || ListProductTypeOption.isEmpty()){
            out.print("Please fill all input");
        }
        else{
            Product newProduct= new Product(0,productName,bigDecimalPrice,filename,description,currentDateSql,"admin",null,null,false,null,null);
            boolean isAddProductSuccess=productDAO.addProduct(newProduct);
            if(isAddProductSuccess){
                Product lastProduct=productDAO.getAllProducts().get(productDAO.getAllProducts().size()-1);
                boolean isAddProductTypeSuccess=addProductType(lastProduct.getProductID(),ListProductTypeOption);
                boolean isAddColorSuccess=addColor(lastProduct.getProductID(), ListColorOption);
                boolean isAddSizeSuccess=addSize(lastProduct.getProductID(), ListSizeOption);
                if(!isAddProductTypeSuccess||!isAddColorSuccess||!isAddSizeSuccess){
                    out.print("failure");
                }
                  else {
                    out.print("success");
                }

            }
            // Do something with productId and quantity
            else{
                out.print("failure");
            }
        }
        out.flush();
        out.close();
    }

    public boolean addProductType(int id,List<String>optionListProductType){
        ProductTypeDAO productTypeDAO = null;
        boolean checkSuccess=true;
        try {
            productTypeDAO = new ProductTypeDAO();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        LocalDateTime CreateDate = LocalDateTime.now();
        Date currentDate = Date.from(CreateDate.atZone(java.time.ZoneId.systemDefault()).toInstant());
        java.sql.Date currentDateSql = new java.sql.Date(currentDate.getTime());
        for(String p : optionListProductType){
            ProductType newProductType=new ProductType(0,p,id,currentDateSql,"admin",null,null,false,null,null);
            boolean isAddProductTypeSuccess= productTypeDAO.addProductType(newProductType);
            if(!isAddProductTypeSuccess){
                checkSuccess=false;
            }
        }
        return checkSuccess;
    }

    public boolean addSize(int id,List<String>optionListSize){
        SizeDAO sizeDAO = null;
        boolean checkSuccess=true;
        try {
            sizeDAO = new SizeDAO();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        LocalDateTime CreateDate = LocalDateTime.now();
        Date currentDate = Date.from(CreateDate.atZone(java.time.ZoneId.systemDefault()).toInstant());
        java.sql.Date currentDateSql = new java.sql.Date(currentDate.getTime());
        for(String p : optionListSize){
            Size newSize=new Size(0,p,id,currentDateSql,"admin",null,null,false,null,null);
            boolean isAddSize= sizeDAO.addSize(newSize);
            if(!isAddSize){
                checkSuccess=false;
            }
        }
        return checkSuccess;
    }

    public boolean addColor(int id,List<String>optionListColor){
        ColorDAO colorDAO = null;
        boolean checkSuccess=true;
        try {
            colorDAO = new ColorDAO();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        LocalDateTime CreateDate = LocalDateTime.now();
        Date currentDate = Date.from(CreateDate.atZone(java.time.ZoneId.systemDefault()).toInstant());
        java.sql.Date currentDateSql = new java.sql.Date(currentDate.getTime());
        for(String p : optionListColor){
            String[] result = p.split(",");
            Color newColor=new Color(0,result[0],result[1],id,currentDateSql,"admin",null,null,false,null,null);
            boolean isAddColor= colorDAO.addColor(newColor);
            if(!isAddColor){
                checkSuccess=false;
            }
        }
        return checkSuccess;
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
