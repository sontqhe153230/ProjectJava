package model.dao;

import model.entity.Account;
import model.entity.Customer;
import model.entity.Order;
import util.DBConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {

    static Connection connection = new DBConnect().connection;

    public static boolean createCustomer(int accountId) {
        if (connection != null) {
            try {
                String sql = "INSERT INTO Customer (AccountID, CreatedDate, CreatedBy) VALUES (" + accountId + ", GETDATE(), 'admin')";
                Statement statement = connection.createStatement();
                return statement.executeUpdate(sql) > 0;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }
    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        if (connection != null) {
            String sql = "SELECT * FROM Customer";
            try {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
                while (resultSet.next()) {
                    Customer customer = new Customer();
                    customer.setCustomerID(resultSet.getInt("CustomerID"));
                    customer.setAccountID(resultSet.getInt("AccountID"));
                    customer.setCustomerName(resultSet.getString("CustomerName"));
                    customer.setAddress(resultSet.getString("Address"));
                    customer.setEmail(resultSet.getString("Email"));
                    customer.setPhone(resultSet.getString("Phone"));
                    customer.setCreatedDate(resultSet.getDate("CreatedDate"));
                    customer.setCreatedBy(resultSet.getString("CreatedBy"));
                    customer.setUpdatedDate(resultSet.getDate("UpdatedDate"));
                    customer.setUpdatedBy(resultSet.getString("UpdatedBy"));
                    customer.setDelete(resultSet.getBoolean("IsDelete"));
                    customer.setDeletedDate(resultSet.getDate("DeletedDate"));
                    customer.setDeletedBy(resultSet.getString("DeletedBy"));

                    customers.add(customer);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return customers;
    }

    public Customer get(int accountId) {
        if (connection != null) {
            try {
                String sql = "SELECT * FROM Customer WHERE AccountID = " + accountId;
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
                if (resultSet.next()) {
                    Customer customer = new Customer();
                    customer.setCustomerID(resultSet.getInt("CustomerID"));
                    customer.setAccountID(resultSet.getInt("AccountID"));
                    customer.setCustomerName(resultSet.getString("CustomerName"));
                    customer.setAddress(resultSet.getString("Address"));
                    customer.setEmail(resultSet.getString("Email"));
                    customer.setPhone(resultSet.getString("Phone"));
                    customer.setCreatedDate(resultSet.getDate("CreatedDate"));
                    customer.setCreatedBy(resultSet.getString("CreatedBy"));
                    customer.setUpdatedDate(resultSet.getDate("UpdatedDate"));
                    customer.setUpdatedBy(resultSet.getString("UpdatedBy"));
                    customer.setDelete(resultSet.getBoolean("IsDelete"));
                    customer.setDeletedDate(resultSet.getDate("DeletedDate"));
                    customer.setDeletedBy(resultSet.getString("DeletedBy"));
                    return customer;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public boolean update(String id, String name, String email, String phone, String address) {
        try {
            String sql = "UPDATE Customer SET CustomerName = ?, Email = ?, Phone = ?, Address = ? WHERE AccountID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, phone);
            preparedStatement.setString(4, address);
            preparedStatement.setInt(5, Integer.parseInt(id));
            preparedStatement.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Order> getOrderByCid(int customerID) {
        List<Order> orderList = new ArrayList<>();
        if (connection != null) {
            String sql = "SELECT * FROM Orders WHERE CustomerID = " + customerID;
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
}
