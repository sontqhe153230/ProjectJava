package model.dao;

import model.entity.ProductType;
import model.entity.Size;
import util.DBConnect;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SizeDAO {
    static Connection connection = new DBConnect().connection;
    public List<Size> getAllSizes() {
        List<Size> sizes = new ArrayList<>();
        if (connection != null) {
            String sql = "SELECT * FROM Size";
            try {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
                while (resultSet.next()) {
                    Size size = new Size();
                    size.setSizeID(resultSet.getInt("SizeID"));
                    size.setSize(resultSet.getString("Size"));
                    size.setProductID(resultSet.getInt("ProductID"));
                    size.setCreatedDate(resultSet.getDate("CreatedDate"));
                    size.setCreatedBy(resultSet.getString("CreatedBy"));
                    size.setUpdatedDate(resultSet.getDate("UpdatedDate"));
                    size.setUpdatedBy(resultSet.getString("UpdatedBy"));
                    size.setDelete(resultSet.getBoolean("IsDelete"));
                    size.setDeletedDate(resultSet.getDate("DeletedDate"));
                    size.setDeletedBy(resultSet.getString("DeletedBy"));

                    sizes.add(size);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sizes;
    }
}
