package model.entity;

import java.util.Date;

public class Order {
    private int OrderId;
    private int ProductId;
    private int CustomerID;
    private int Quantity;
    private  String Description;
    private boolean IsPayment;
    private String DeliveryStatus;
    private Date CreatedDate;
    private String CreatedBy;
    private Date UpdatedDate;
    private String UpdatedBy;
    private boolean IsDelete;
    private Date DeletedDate;
    private String DeletedBy;

    public Order(){}
    public Order(int orderId, int productId, int customerID, int quantity, String description, boolean isPayment, String deliveryStatus, Date createdDate, String createdBy, Date updatedDate, String updatedBy, boolean isDelete, Date deletedDate, String deletedBy) {
        OrderId = orderId;
        ProductId = productId;
        CustomerID = customerID;
        Quantity = quantity;
        Description = description;
        IsPayment = isPayment;
        DeliveryStatus = deliveryStatus;
        CreatedDate = createdDate;
        CreatedBy = createdBy;
        UpdatedDate = updatedDate;
        UpdatedBy = updatedBy;
        IsDelete = isDelete;
        DeletedDate = deletedDate;
        DeletedBy = deletedBy;
    }

    public int getOrderId() {
        return OrderId;
    }

    public void setOrderId(int orderId) {
        OrderId = orderId;
    }

    public int getProductId() {
        return ProductId;
    }

    public void setProductId(int productId) {
        ProductId = productId;
    }

    public int getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(int customerID) {
        CustomerID = customerID;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public boolean isPayment() {
        return IsPayment;
    }

    public void setPayment(boolean payment) {
        IsPayment = payment;
    }

    public String getDeliveryStatus() {
        return DeliveryStatus;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        DeliveryStatus = deliveryStatus;
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

    public boolean isDelete() {
        return IsDelete;
    }

    public void setDelete(boolean delete) {
        IsDelete = delete;
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
