<%@ page import="model.entity.Order" %>
<%@ page import="java.util.List" %>
<%@ page import="controller.AdminManageOrderServlet" %>
<!doctype html>
<html lang="en">
<head>
    <title>Sidebar 01</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link href="https://fonts.googleapis.com/css?family=Poppins:300,400,500,600,700,800,900" rel="stylesheet">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="assets/css/admin.css">
    <link rel="stylesheet" href="assets/css/table.css">
</head>
<body>

<div class="wrapper d-flex align-items-stretch">

    <%@include file="WEB-INF/jspf/Admin/sidebar.jsp"%>
    <!-- Page Content  -->
    <div id="content" class="p-4 p-md-5">
        <div class="container-xl">
            <div class="table-responsive">
                <div class="table-wrapper">
                    <div class="table-title">
                        <div class="row">
                            <div class="col-sm-8"><h2>Manage <b>Order</b></h2></div>
                            <div class="col-sm-4">
                                <div class="search-box">
                                    <i class="material-icons">&#xE8B6;</i>
                                    <input type="text" class="form-control" placeholder="Search&hellip;">
                                </div>
                            </div>
                        </div>
                    </div>
                    <table class="table table-striped table-hover table-bordered">
                        <thead>

                        <tr>
                            <th>OrderId</th>
                            <th>Product name<i class="fa fa-sort"></i></th>
                            <th>Customer Name</th>
                            <th>Description <i class="fa fa-sort"></i></th>
                            <th>Is Payment</th>
                            <th>Delivery <i class="fa fa-sort"></i></th>
                            <th>Create Date</th>
                        </tr>
                        </thead>
                        <tbody>
                        <%
                            List<Order> orders = (List<Order>) request.getAttribute("orderList");
                            for (Order order : orders) {
                                if(!order.isDelete()){
                        %>
                        <tr>

                            <td><%= order.getOrderId()%></td>
                            <td><%= new AdminManageOrderServlet().getProductNameById(order.getProductId()) %></td>
                            <td><%= new AdminManageOrderServlet().getCustomerNameById(order.getCustomerID()) %></td>
                            <td><%= order.getDescription()%></td>
                            <td><%= order.isPayment()==true?"Done":"Not yet" %></td>
                            <td><%= order.getDeliveryStatus()%></td>
                            <td><%= order.getCreatedDate()%></td>

                            <td>
                                <a href="#" onclick="sendData(<%= order.getOrderId()%>)" class="edit" title="Edit" data-toggle="modal" data-target="#order-modal"><i class="material-icons">&#xE254;</i></a>
                                <a href="#" class="delete" title="Delete" data-toggle="tooltip"><i class="material-icons">&#xE872;</i></a>
                            </td>
                        </tr>
                        <%}%>
                        <%}%>
                        </tbody>
                    </table>
                    <div id="order-modal" class="modal fade" role="dialog">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">

                                    <h3>Update Order</h3>
                                </div>
                                <form id="orderForm">
                                    <div class="modal-body">
                                        <div class="form-group">

                                            <label>Is Payment</label>
                                            <select name="isPayment" >
                                                <option value="true">yes</option>
                                                <option value="false">no</option>

                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <label>Size</label>
                                            <select name="Delivery-Status" id="cars">
                                                <option value="Pending">Pending</option>
                                                <option value="Delivered">Delivered</option>

                                            </select>
                                        </div>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                        <input type="submit" class="btn btn-success">
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<script src="js/jquery.min.js"></script>
<script src="js/popper.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/main.js"></script>
<script>
    let idUpdateOrder=0;
    function sendData(id){
        idUpdateOrder=id;
        return idUpdateOrder;
    }
    $(document).ready(function(){
        $("#orderForm").submit(function(event){
            // Prevent default form submission
            event.preventDefault();

            // Get form data
            var formData = $(this).serialize();
            formData += '&IdUpdate=' + idUpdateOrder;
            // Submit form data using AJAX
            $.ajax({
                type: "POST",
                url: "AdminManageOrder",
                data: formData,
                contentType: 'application/x-www-form-urlencoded',
                success: function(response){
                    if(response.trim() === "success") {
                        // Show success message
                        swal("Good job!", "Update successful!", "success")
                            .then((value) => {
                                // Redirect to another page
                                window.location.href = "/ClothesShop/AdminManageOrder";
                            });
                    }

                    else {
                        swal("", "Please try again.", "error");
                    }
                },
                error: function(xhr, status, error){
                    console.error("Error:", error);
                    swal("Error!", "Something went wrong. Please try again later.", "error");
                }
            });
        });
    });
</script>
</body>
</html>