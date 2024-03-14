package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.ColorDAO;
import model.dao.ProductDAO;
import model.dao.ProductTypeDAO;
import model.dao.SizeDAO;
import model.entity.Color;
import model.entity.Product;
import model.entity.ProductType;
import model.entity.Size;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet(name = "ViewDetailProductServlet", value = "/ViewDetailProduct")
public class ViewDetailProductServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String idProductStr = request.getParameter("pid");
        int id=Integer.parseInt(idProductStr);
        List<Size> sizeList = getAllSizeByProductId(id);
        List<Color> colorList = getAllColorByProductId(id);
        List<ProductType> productTypeList = getAllProductTypeByProductId(id);


        request.setAttribute("sizeList", sizeList);
        request.setAttribute("colorList", colorList);
        request.setAttribute("productTypeList", productTypeList);

        request.getRequestDispatcher("ViewDetailProduct.jsp").forward(request, response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        response.setContentType("text/html");

         String id=request.getParameter("ProductId");
         String size=request.getParameter("size");
        String color=request.getParameter("color");
        String productType=request.getParameter("productType");
        String IdUpdate=request.getParameter("IdUpdate");
        int idConvert=Integer.parseInt(id);
        int idUpdateConvert=Integer.parseInt(IdUpdate);


        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();

        if(size!=null&& size!=""){
            SizeDAO sizeDAO = null;
            try {
                sizeDAO = new SizeDAO();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            LocalDateTime UpdateDate = LocalDateTime.now();
            Date currentDate = Date.from(UpdateDate.atZone(java.time.ZoneId.systemDefault()).toInstant());
            java.sql.Date currentDateSql = new java.sql.Date(currentDate.getTime());

            Size sizeFind=FindSizeBySizeId(idUpdateConvert,idConvert);
            Size sizeUpdate=new Size(idUpdateConvert,size,idConvert,sizeFind.getCreatedDate(),sizeFind.getCreatedBy(),currentDateSql,"admin",false,null,null);
            boolean checkIsUpdated=sizeDAO.updateSize(sizeUpdate);
            if(checkIsUpdated){
                out.print("success");
            }
            else {
                out.print("failure");
            }
        }

        if(color!=null&& color!=""){
            ColorDAO colorDAO = null;
            try {
                colorDAO = new ColorDAO();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            LocalDateTime UpdateDate = LocalDateTime.now();
            Date currentDate = Date.from(UpdateDate.atZone(java.time.ZoneId.systemDefault()).toInstant());
            java.sql.Date currentDateSql = new java.sql.Date(currentDate.getTime());

            Color colorFind=FindColorByColorId(idUpdateConvert,idConvert);
            Color colorUpdate=new Color(idUpdateConvert,color,colorFind.getIMG(),idConvert,colorFind.getCreatedDate(),colorFind.getCreatedBy(),currentDateSql,"admin",false,null,null);
            boolean checkIsUpdated=colorDAO.updateColorByProductId(colorUpdate);
            if(checkIsUpdated){
                out.print("success");
            }
            else {
                out.print("failure");
            }
        }

        if(productType!=null&& productType!=""){
            ProductTypeDAO productTypeDAO = null;
            try {
                productTypeDAO = new ProductTypeDAO();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            LocalDateTime UpdateDate = LocalDateTime.now();
            Date currentDate = Date.from(UpdateDate.atZone(java.time.ZoneId.systemDefault()).toInstant());
            java.sql.Date currentDateSql = new java.sql.Date(currentDate.getTime());

            ProductType productTypeFind=FindProductTypeByColorId(idUpdateConvert,idConvert);
            ProductType productTypeUpdate=new ProductType(idUpdateConvert,productType,idConvert,productTypeFind.getCreatedDate(),productTypeFind.getCreatedBy(),currentDateSql,"admin",false,null,null);
            boolean checkIsUpdated=productTypeDAO.updateProductType(productTypeUpdate);
            if(checkIsUpdated){
                out.print("success");
            }
            else {
                out.print("failure");
            }
        }
    }
    public List<Size> getAllSizeByProductId(int id){
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
        if (!foundSize.isEmpty()) {
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
        if (!foundColor.isEmpty()) {
            // Nếu tìm thấy, đặt sản phẩm vào thuộc tính của request
            return foundColor;
        } else {
            // Nếu không tìm thấy, xử lý trường hợp (ví dụ: hiển thị thông báo lỗi)
            // Bạn có thể chuyển hướng đến trang lỗi hoặc xử lý nó bằng bất kỳ cách nào phù hợp khác
            // Ví dụ:
            return null;
        }
    }
    public Size FindSizeBySizeId(int idSize,int idProduct ){
        Size sizeFind=null;
        List<Size>sizeList=getAllSizeByProductId(idProduct);
        for(Size size :sizeList){
            if(size.getSizeID()==idSize){
                sizeFind=size;
            }
        }
        return sizeFind;
    }
    public Color FindColorByColorId(int idColor,int idProduct ){
        Color colorFind=null;
        List<Color>colorList=getAllColorByProductId(idProduct);
        for(Color color :colorList){
            if(color.getColorID()==idColor){
                colorFind=color;
            }
        }
        return colorFind;
    }

    public ProductType FindProductTypeByColorId(int idProductType,int idProduct ){
        ProductType productTypeFind=null;
        List<ProductType>productTypeList=getAllProductTypeByProductId(idProduct);
        for(ProductType productType :productTypeList){
            if(productType.getTypeID()==idProductType){
                productTypeFind=productType;
            }
        }
        return productTypeFind;
    }
    public List<ProductType>getAllProductTypeByProductId(int id){
        ProductTypeDAO productTypeDAO = null;
        try {
            productTypeDAO = new ProductTypeDAO();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // Lấy tất cả sản phẩm từ cơ sở dữ liệu
        List<ProductType> productTypeList = productTypeDAO.getAllProductTypes();

        // Khởi tạo biến để lưu trữ sản phẩm tìm thấy
        List<ProductType> foundProductType = new ArrayList<>();;

        // Lặp qua productList để tìm sản phẩm có ID phù hợp
        for (ProductType productType : productTypeList) {
            if (productType.getProductID() == id) {
                foundProductType.add(productType);
                break; // Dừng lặp khi tìm thấy sản phẩm
            }
        }
        // Kiểm tra xem sản phẩm có được tìm thấy không
        if (!foundProductType.isEmpty()) {
            // Nếu tìm thấy, đặt sản phẩm vào thuộc tính của request
            return foundProductType;
        } else {
            // Nếu không tìm thấy, xử lý trường hợp (ví dụ: hiển thị thông báo lỗi)
            // Bạn có thể chuyển hướng đến trang lỗi hoặc xử lý nó bằng bất kỳ cách nào phù hợp khác
            // Ví dụ:
            return null;
        }
    }
}
