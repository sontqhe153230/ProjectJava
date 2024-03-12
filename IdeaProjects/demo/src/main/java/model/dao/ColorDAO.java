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
    public void addColor(Color color) {
        if (connection != null && color != null) {
            String sql = "INSERT INTO Color (Color, IMG, ProductID, CreatedDate, CreatedBy, UpdatedDate, UpdatedBy, IsDelete, DeletedDate, DeletedBy) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try {
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, color.getColor());
                statement.setString(2, color.getIMG());
                statement.setInt(3, color.getProductID());
                statement.setDate(4, new java.sql.Date(color.getCreatedDate().getTime()));
                statement.setString(5, color.getCreatedBy());
                statement.setDate(6, new java.sql.Date(color.getUpdatedDate().getTime()));
                statement.setString(7, color.getUpdatedBy());
                statement.setBoolean(8, color.isDelete());
                statement.setDate(9, color.getDeletedDate() != null ? new java.sql.Date(color.getDeletedDate().getTime()) : null);
                statement.setString(10, color.getDeletedBy());

                int rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("A new color was added successfully!");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
