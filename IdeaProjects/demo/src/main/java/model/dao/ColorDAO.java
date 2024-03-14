package model.dao;

import model.entity.Color;
import model.entity.Customer;
import util.DBConnect;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ColorDAO {

    Connection connection = new DBConnect().connection;

    public List<Color> getAllColors() {
        List<Color> colors = new ArrayList<>();
        if (connection != null) {
            String sql = "SELECT * FROM Color";
            try {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
                while (resultSet.next()) {
                    Color color = new Color();
                    color.setColorID(resultSet.getInt("ColorID"));
                    color.setColor(resultSet.getString("Color"));
                    color.setIMG(resultSet.getString("IMG"));
                    color.setProductID(resultSet.getInt("ProductID"));
                    color.setCreatedDate(resultSet.getDate("CreatedDate"));
                    color.setCreatedBy(resultSet.getString("CreatedBy"));
                    color.setUpdatedDate(resultSet.getDate("UpdatedDate"));
                    color.setUpdatedBy(resultSet.getString("UpdatedBy"));
                    color.setDelete(resultSet.getBoolean("IsDelete"));
                    color.setDeletedDate(resultSet.getDate("DeletedDate"));
                    color.setDeletedBy(resultSet.getString("DeletedBy"));

                    colors.add(color);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return colors;
    }
    public boolean addColor(Color color) {
        if (connection != null && color != null) {
            String sql = "INSERT INTO [Color] (Color, IMG, ProductID, CreatedDate, CreatedBy, UpdatedDate, UpdatedBy, IsDelete, DeletedDate, DeletedBy) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try {
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, color.getColor());
                statement.setString(2, color.getIMG());
                statement.setInt(3, color.getProductID());
                statement.setDate(4, color.getCreatedDate());
                statement.setString(5, color.getCreatedBy());
                statement.setDate(6, color.getUpdatedDate());
                statement.setString(7, color.getUpdatedBy());
                statement.setBoolean(8, color.isDelete());
                statement.setDate(9, color.getDeletedDate());
                statement.setString(10, color.getDeletedBy());

                int rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0) {
                    return true;
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    public boolean updateColorByProductId(Color color) {
        if (connection != null && color != null) {
            String sql = "UPDATE [Color] SET IMG=?, Color=?, CreatedDate=?, CreatedBy=?, UpdatedDate=?, UpdatedBy=?, IsDelete=?, DeletedDate=?, DeletedBy=? WHERE ColorID=?";
            try {
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, color.getIMG());
                statement.setString(2, color.getColor());
                statement.setDate(3, color.getCreatedDate());
                statement.setString(4, color.getCreatedBy());
                statement.setDate(5, color.getUpdatedDate());
                statement.setString(6, color.getUpdatedBy());
                statement.setBoolean(7, color.isDelete());
                statement.setDate(8, color.getDeletedDate());
                statement.setString(9, color.getDeletedBy());
                statement.setInt(10, color.getProductID());

                int rowsUpdated = statement.executeUpdate();
                if (rowsUpdated > 0) {
                    return true;
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }


}
