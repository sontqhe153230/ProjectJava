package util;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import model.dao.AccountDAO;
import model.dao.AccountDAO;
import util.PasswordUtil;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@WebListener
public class AppStartupListener implements ServletContextListener {

    private AccountDAO accountDAO;

    private PasswordUtil passwordUtil;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            accountDAO = new AccountDAO();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        ensureAdminAccount();
        ensureUserAccount();
    }

    private void ensureAdminAccount() {
        String username = "admin";
        String defaultPassword = "123456";
        String role = "admin";
        String hashedPassword = "";
        try {
            hashedPassword = passwordUtil.generateStrongPasswordHash(defaultPassword);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }

        if (accountDAO.existsByUsername(username)) {
            accountDAO.updatePassword(username, hashedPassword, role);
        } else {
            AccountDAO.createAccount(username, hashedPassword,"admin");
        }
    }

    private void ensureUserAccount() {
        String username = "user";
        String defaultPassword = "123456";
        String role = "user";
        String hashedPassword = "";
        try {
            hashedPassword = passwordUtil.generateStrongPasswordHash(defaultPassword);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }

        if (accountDAO.existsByUsername(username)) {
            accountDAO.updatePassword(username, hashedPassword, role);
        } else {
            accountDAO.createAccount(username, hashedPassword,"user");
        }
    }





}