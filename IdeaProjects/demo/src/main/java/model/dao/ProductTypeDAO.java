package model.dao;

import model.entity.Color;
import model.entity.ProductType;
import util.DBConnect;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductTypeDAO {
    static Connection connection = new DBConnect().connection;
    public List<ProductType> getAllProductTypes() {
        List<ProductType> productTypes = new ArrayList<>();
        if (connection != null) {
            String sql = "SELECT * FROM ProductType";
            try {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
                while (resultSet.next()) {
                    ProductType productType = new ProductType();
                    productType.setTypeID(resultSet.getInt("TypeID"));
                    productType.setTypeName(resultSet.getString("TypeName"));
                    productType.setProductID(resultSet.getInt("ProductID"));
                    productType.setCreatedDate(resultSet.getDate("CreatedDate"));
                    productType.setCreatedBy(resultSet.getString("CreatedBy"));
                    productType.setUpdatedDate(resultSet.getDate("UpdatedDate"));
                    productType.setUpdatedBy(resultSet.getString("UpdatedBy"));
                    productType.setDelete(resultSet.getBoolean("IsDelete"));
                    productType.setDeletedDate(resultSet.getDate("DeletedDate"));
                    productType.setDeletedBy(resultSet.getString("DeletedBy"));

                    productTypes.add(productType);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return productTypes;
    }

    public boolean addProductType(ProductType productType) {
        String sql = "INSERT INTO ProductType (TypeName, ProductID, CreatedDate, CreatedBy, UpdatedDate, UpdatedBy, IsDelete, DeletedDate, DeletedBy) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            // Setting parameters for the PreparedStatement
            preparedStatement.setString(1, productType.getTypeName());
            preparedStatement.setInt(2, productType.getProductID());
            preparedStatement.setObject(3, productType.getCreatedDate());
            preparedStatement.setString(4, productType.getCreatedBy());
            preparedStatement.setObject(5, productType.getUpdatedDate());
            preparedStatement.setString(6, productType.getUpdatedBy());
            preparedStatement.setBoolean(7, productType.isDelete());
            preparedStatement.setObject(8, productType.getDeletedDate());
            preparedStatement.setString(9, productType.getDeletedBy());

            // Executing the query
            int rowsAffected = preparedStatement.executeUpdate();

            // Checking if the query was successful
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }}
