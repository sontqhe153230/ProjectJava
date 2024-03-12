package model.dao;

import model.entity.Account;
import model.entity.Customer;
import util.DBConnect;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {

    Connection connection = new DBConnect().connection;
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
}
