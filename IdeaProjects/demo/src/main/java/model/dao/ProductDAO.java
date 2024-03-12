package model.dao;

import model.entity.Product;
import util.DBConnect;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    Connection connection = new DBConnect().connection;

    public ProductDAO() throws Exception {
    }

    public List<Product> getAllProducts() {
        List<Product> productList = new ArrayList<>();
        if (connection != null) {
            String sql = "SELECT * FROM Product";
            try {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
                while (resultSet.next()) {
                    Product product = new Product();
                    product.setProductID(resultSet.getInt("ProductID"));
                    product.setProductName(resultSet.getString("ProductName"));
                    product.setPrice(resultSet.getBigDecimal("Price"));
                    product.setIMG(resultSet.getString("IMG"));
                    product.setDescription(resultSet.getString("Description"));
                    product.setCreatedDate(resultSet.getDate("CreatedDate"));
                    product.setCreatedBy(resultSet.getString("CreatedBy"));
                    product.setUpdatedDate(resultSet.getDate("UpdatedDate"));
                    product.setUpdatedBy(resultSet.getString("UpdatedBy"));
                    product.setDeleted(resultSet.getBoolean("IsDelete"));
                    product.setDeletedDate(resultSet.getDate("DeletedDate"));
                    product.setDeletedBy(resultSet.getString("DeletedBy"));
                    productList.add(product);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return productList;
    }

    public boolean addProduct(Product product) {
        String sql = "INSERT INTO Product (ProductName, Price, IMG, Description, CreatedDate, CreatedBy,UpdatedDate,UpdateBy,IsDelete,DeletedDate,DeletedBy) VALUES (?, ?, ?, ?, ?, ?,?,?,?,?,?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            // Setting parameters for the PreparedStatement
            preparedStatement.setString(1, product.getProductName());
            preparedStatement.setBigDecimal(2, product.getPrice());
            preparedStatement.setString(3, product.getIMG());
            preparedStatement.setString(4, product.getDescription());
            preparedStatement.setDate(5, product.getCreatedDate());
            preparedStatement.setString(6, product.getCreatedBy());
            preparedStatement.setDate(7, product.getUpdatedDate());
            preparedStatement.setString(8, product.getUpdatedBy());
            preparedStatement.setBoolean(9, product.isDelete());
            preparedStatement.setDate(10, product.getDeletedDate());
            preparedStatement.setString(11, product.getDeletedBy());
            // Executing the query
            int rowsAffected = preparedStatement.executeUpdate();

            // Checking if the query was successful
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}