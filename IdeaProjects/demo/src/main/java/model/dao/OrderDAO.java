package model.dao;

import model.entity.Order;
import model.entity.Product;
import util.DBConnect;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {
    Connection connection = new DBConnect().connection;

    public OrderDAO() throws Exception {
    }

    public List<Order> getAllOrder() {
        List<Order> orderList = new ArrayList<>();
        if (connection != null) {
            String sql = "SELECT * FROM Orders";
            try {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
                while (resultSet.next()) {
                    Order order = new Order();
                    order.setOrderId(resultSet.getInt("OrderID"));
                    order.setProductId(resultSet.getInt("ProductID"));
                    order.setCustomerID(resultSet.getInt("CustomerID"));
                    order.setQuantity(resultSet.getInt("Quantity"));
                    order.setDescription(resultSet.getString("Description"));
                    order.setPayment(resultSet.getBoolean("IsPayment"));
                    order.setDeliveryStatus(resultSet.getString("DeliveryStatus"));
                    order.setCreatedDate(resultSet.getDate("CreatedDate"));
                    order.setCreatedBy(resultSet.getString("CreatedBy"));
                    order.setUpdatedDate(resultSet.getDate("UpdatedDate"));
                    order.setUpdatedBy(resultSet.getString("UpdatedBy"));
                    order.setDelete(resultSet.getBoolean("IsDelete"));
                    order.setDeletedDate(resultSet.getDate("DeletedDate"));
                    order.setDeletedBy(resultSet.getString("DeletedBy"));
                    order.setSizeId(resultSet.getInt("SizeId"));
                    order.setColorId(resultSet.getInt("ColorId"));
                    orderList.add(order);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return orderList;
    }
    public boolean addOrder(Order newOrder) {
        if (connection != null && newOrder != null) {
            String sql = "INSERT INTO [Orders] (ProductID, CustomerID, Quantity, Description, IsPayment, DeliveryStatus, CreatedDate, CreatedBy,UpdatedDate,UpdatedBy,IsDelete,DeletedDate,DeletedBy,SizeId,ColorId) VALUES (?, ?, ?, ?, ?, ?, ?,?,?,?,?,?,?,?,?)";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, newOrder.getProductId());
                preparedStatement.setInt(2, newOrder.getCustomerID());
                preparedStatement.setInt(3, newOrder.getQuantity());
                preparedStatement.setString(4, newOrder.getDescription());
                preparedStatement.setBoolean(5, newOrder.isPayment());
                preparedStatement.setString(6, newOrder.getDeliveryStatus());
                preparedStatement.setDate(7, newOrder.getCreatedDate());
                preparedStatement.setString(8, newOrder.getCreatedBy());
                preparedStatement.setDate(9, newOrder.getUpdatedDate());
                preparedStatement.setString(10, newOrder.getUpdatedBy());
                preparedStatement.setBoolean(11, newOrder.isDelete());
                preparedStatement.setDate(12, newOrder.getDeletedDate());
                preparedStatement.setString(13, newOrder.getDeletedBy());
                preparedStatement.setInt(14, newOrder.getSizeId());
                preparedStatement.setInt(15, newOrder.getColorId());

                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    return true; // Order added successfully
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
        return false; // Failed to add order
    }


}
