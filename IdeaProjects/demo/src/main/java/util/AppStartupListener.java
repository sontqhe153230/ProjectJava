package util;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import model.dao.AccountDao;
import model.dao.AccountDao;
import util.PasswordUtil;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import static util.PasswordUtil.generateStrongPasswordHash;

@WebListener
public class AppStartupListener implements ServletContextListener {

    private AccountDao Account;

    private PasswordUtil passwordUtil;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            Account = new AccountDao();
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

        if (AccountDao.existsByUsername(username)) {
            AccountDao.updatePassword(username, hashedPassword, role);
        } else {
            AccountDao.createAccount(username, hashedPassword, role);
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

        if (Account.existsByUsername(username)) {
            Account.updatePassword(username, hashedPassword, role);
        } else {
            Account.createAccount(username, hashedPassword, role);
        }
    }





}