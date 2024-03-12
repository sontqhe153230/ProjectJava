package model.dao;

import model.entity.ProductType;
import model.entity.Size;
import util.DBConnect;

import java.sql.*;
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
    public void addSize(Size size) {
        if (connection != null && size != null) {
            String sql = "INSERT INTO Size (Size, ProductID, CreatedDate, CreatedBy, UpdatedDate, UpdatedBy, IsDelete, DeletedDate, DeletedBy) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try {
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, size.getSize());
                statement.setInt(2, size.getProductID());
                statement.setDate(3, new java.sql.Date(size.getCreatedDate().getTime()));
                statement.setString(4, size.getCreatedBy());
                statement.setDate(5, new java.sql.Date(size.getUpdatedDate().getTime()));
                statement.setString(6, size.getUpdatedBy());
                statement.setBoolean(7, size.isDelete());
                statement.setDate(8, size.getDeletedDate() != null ? new java.sql.Date(size.getDeletedDate().getTime()) : null);
                statement.setString(9, size.getDeletedBy());

                int rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("A new size was added successfully!");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
