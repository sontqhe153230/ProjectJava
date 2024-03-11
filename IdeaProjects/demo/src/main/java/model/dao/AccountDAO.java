package model.dao;

import model.entity.Account;
import util.DBConnect;
import util.PasswordUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO {
    static Connection connection = new DBConnect().connection;

    PasswordUtil passwordUtil = new PasswordUtil();
    public AccountDAO() throws Exception {
    }
    public static boolean createAccount(String username, String password) {
        if (connection != null) {
            try {
                String sql = "INSERT INTO Account (username, password, role, createdDate, createdBy) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);
                preparedStatement.setString(3, "user");
                preparedStatement.setTimestamp(4, new java.sql.Timestamp(System.currentTimeMillis()));
                preparedStatement.setString(5, username);

                preparedStatement.executeUpdate();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public boolean existsByUsernameOrEmail(String username, String email) {
        if (connection != null) {
            try {
                String sql = "SELECT * FROM Account a LEFT JOIN Customer c ON a.CustomerID = c.CustomerID WHERE a.Username = ? OR c.Email = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, email);
                return preparedStatement.executeQuery().next();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static boolean updatePassword(String username, String password, String role) {
        if (connection != null) {
            try {
                String sql = "UPDATE Account SET password = ?, role = ? WHERE username = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, password);
                preparedStatement.setString(2, role);
                preparedStatement.setString(3, username);
                preparedStatement.executeUpdate();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public Account login(String username, String password) {
        if (connection != null) {
            try {
                String sql = "SELECT * FROM Account WHERE username = ? AND isDelete = 0";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, username);
                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    String storedPassword = resultSet.getString("password");
                    if (PasswordUtil.validatePassword(password, storedPassword)) {
                        Account account = new Account();
                        account.setAccountId(resultSet.getInt("accountID"));
                        account.setUsername(resultSet.getString("username"));
                        account.setPassword(resultSet.getString("password"));
                        account.setRole(resultSet.getString("role"));
                        account.setIMG(resultSet.getString("IMG"));
                        account.setCreatedDate(resultSet.getDate("createdDate"));
                        return account;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static boolean existsByUsername(String username) {
        if (connection != null) {
            try {
                String sql = "SELECT * FROM Account WHERE username = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, username);
                return preparedStatement.executeQuery().next();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public List<Account> getAllAccount() {
        List<Account> AccountList = new ArrayList<>();
        if (connection != null) {
            String sql = "SELECT * FROM Account";
            try {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
                while (resultSet.next()) {
                    Account Account = new Account();
                    Account.setAccountId(resultSet.getInt("AccountID"));
                    Account.setUsername(resultSet.getString("Username"));
                    Account.setPassword(resultSet.getString("Password"));
                    Account.setRole(resultSet.getString("Role"));
                    Account.setIMG(resultSet.getString("IMG"));
                    Account.setCreatedDate(resultSet.getDate("CreatedDate"));
                    Account.setCreateBy(resultSet.getString("CreatedBy"));
                    Account.setUpdatedDate(resultSet.getDate("UpdatedDate"));
                    Account.setUpdatedBy(resultSet.getString("UpdatedBy"));
                    Account.setIdDelete(resultSet.getBoolean("IsDelete"));
                    Account.setDeleteDate(resultSet.getDate("DeletedDate"));
                    Account.setDeletedBy(resultSet.getString("DeletedBy"));
                    AccountList.add(Account);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return AccountList;
    }
}

