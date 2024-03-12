package model.dao;

import model.entity.Color;
import model.entity.Customer;
import util.DBConnect;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
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

}
