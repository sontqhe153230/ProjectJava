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
    public boolean addSize(Size size) {
        if (connection != null && size != null) {
            String sql = "INSERT INTO [Size] (Size, ProductID, CreatedDate, CreatedBy, UpdatedDate, UpdatedBy, IsDelete, DeletedDate, DeletedBy) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try {
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, size.getSize());
                statement.setInt(2, size.getProductID());
                statement.setDate(3, size.getCreatedDate() );
                statement.setString(4, size.getCreatedBy());
                statement.setDate(5, size.getUpdatedDate());
                statement.setString(6, size.getUpdatedBy());
                statement.setBoolean(7, size.isDelete());
                statement.setDate(8, size.getDeletedDate());
                statement.setString(9, size.getDeletedBy());

                int rowsInserted = statement.executeUpdate();
                return rowsInserted > 0;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }
    public boolean updateSize(Size size) {
        if (connection != null && size != null) {
            String sql = "UPDATE [Size] SET Size=?, CreatedDate=?, CreatedBy=?, UpdatedDate=?, UpdatedBy=?, IsDelete=?, DeletedDate=?, DeletedBy=? WHERE SizeID=?";
            try {
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, size.getSize());
                statement.setDate(2, size.getCreatedDate());
                statement.setString(3, size.getCreatedBy());
                statement.setDate(4, size.getUpdatedDate());
                statement.setString(5, size.getUpdatedBy());
                statement.setBoolean(6, size.isDelete());
                statement.setDate(7, size.getDeletedDate());
                statement.setString(8, size.getDeletedBy());
                statement.setInt(9, size.getProductID());

                int rowsUpdated = statement.executeUpdate();
                return rowsUpdated > 0;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

}
