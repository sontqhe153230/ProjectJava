/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.entity;

import java.sql.Date;

/**
 *
 * @author ADMIN
 */
public class Account {


    private int AccountId;
    private String Username;
    private  String Password;
    private String Role;
    private  String IMG;
    private Date CreatedDate;
    private String CreateBy;
    private Date UpdatedDate;
    private String UpdatedBy;
    private boolean idDelete;
    private Date DeleteDate;
    private String DeletedBy;
    public Account() {

    }
    public Account(int accountId, String username, String password, String role, String IMG, Date createdDate, String createBy, Date updatedDate, String updatedBy, boolean idDelete, Date deleteDate, String deletedBy) {
        AccountId = accountId;
        Username = username;
        Password = password;
        Role = role;
        this.IMG = IMG;
        CreatedDate = createdDate;
        CreateBy = createBy;
        UpdatedDate = updatedDate;
        UpdatedBy = updatedBy;
        this.idDelete = idDelete;
        DeleteDate = deleteDate;
        DeletedBy = deletedBy;
    }



    public int getAccountId() {
        return AccountId;
    }

    public void setAccountId(int accountId) {
        AccountId = accountId;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public void setRole(String role) {
        Role = role;
    }

    public String getIMG() {
        return IMG;
    }

    public void setIMG(String IMG) {
        this.IMG = IMG;
    }

    public Date getCreatedDate() {
        return CreatedDate;
    }

    public void setCreatedDate(Date createdDate) {
        CreatedDate = createdDate;
    }

    public String getCreateBy() {
        return CreateBy;
    }

    public void setCreateBy(String createBy) {
        CreateBy = createBy;
    }

    public Date getUpdatedDate() {
        return UpdatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        UpdatedDate = updatedDate;
    }

    public String getUpdatedBy() {
        return UpdatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        UpdatedBy = updatedBy;
    }

    public boolean isIdDelete() {
        return idDelete;
    }

    public void setIdDelete(boolean idDelete) {
        this.idDelete = idDelete;
    }

    public Date getDeleteDate() {
        return DeleteDate;
    }

    public void setDeleteDate(Date deleteDate) {
        DeleteDate = deleteDate;
    }

    public String getDeletedBy() {
        return DeletedBy;
    }

    public void setDeletedBy(String deletedBy) {
        DeletedBy = deletedBy;
    }

    public String getRole() {
        return Role;
    }
}
