package model.dao;

import model.entity.Color;
import model.entity.ProductType;
import util.DBConnect;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
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
}
