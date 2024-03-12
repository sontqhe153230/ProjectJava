package model.entity;

import java.math.BigDecimal;
import java.util.Date;

public class Product {
    private int ProductID;
    private String ProductName;
    private BigDecimal Price;

    private String IMG;
    private String Description;
    private Date CreatedDate;
    private String CreatedBy;
    private Date UpdatedDate;
    private String UpdatedBy;
    private boolean IsDelete;
    private Date DeletedDate;
    private String DeletedBy;

    public Product() {
    }

    public Product(int productID, String productName, BigDecimal price, String img, String description, Date createdDate, String createdBy, Date updatedDate, String updatedBy, boolean isDelete, Date deletedDate, String deletedBy) {
        ProductID = productID;
        ProductName = productName;
        Price = price;
        IMG=img;
        Description = description;
        CreatedDate = createdDate;
        CreatedBy = createdBy;
        UpdatedDate = updatedDate;
        UpdatedBy = updatedBy;
        IsDelete = isDelete;
        DeletedDate = deletedDate;
        DeletedBy = deletedBy;
    }

    public String getIMG() {
        return IMG;
    }

    public void setIMG(String img) {
        IMG = img;
    }

    public boolean isDelete() {
        return IsDelete;
    }

    public void setDelete(boolean delete) {
        IsDelete = delete;
    }

    public int getProductID() {
        return ProductID;
    }

    public void setProductID(int productID) {
        ProductID = productID;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public BigDecimal getPrice() {
        return Price;
    }

    public void setPrice(BigDecimal price) {
        Price = price;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public java.sql.Date getCreatedDate() {
        return (java.sql.Date) CreatedDate;
    }

    public void setCreatedDate(Date createdDate) {
        CreatedDate = createdDate;
    }

    public String getCreatedBy() {
        return CreatedBy;
    }

    public void setCreatedBy(String createdBy) {
        CreatedBy = createdBy;
    }

    public java.sql.Date getUpdatedDate() {
        return (java.sql.Date) UpdatedDate;
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

    public boolean isDeleted() {
        return IsDelete;
    }

    public void setDeleted(boolean deleted) {
        IsDelete = deleted;
    }

    public java.sql.Date getDeletedDate() {
        return (java.sql.Date) DeletedDate;
    }

    public void setDeletedDate(Date deletedDate) {
        DeletedDate = deletedDate;
    }

    public String getDeletedBy() {
        return DeletedBy;
    }

    public void setDeletedBy(String deletedBy) {
        DeletedBy = deletedBy;
    }
}
