<%@ page import="model.entity.Product" %>
<%@ page import="java.util.List" %>
<%@ page import="controller.AdminManageProductServlet" %>
<%@ page import="model.entity.Size" %>
<%@ page import="model.entity.Color" %>
<%@ page import="model.entity.ProductType" %>
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
                            <div class="col-sm-8"><h2>Manage <b>Product</b></h2></div>
                            <div class="col-sm-4">
                                <div class="search-box">
                                    <a href="AddNewProduct" class="add-button">
                                        Add new </a>
                                </div>
                            </div>
                        </div>
                    </div>
                    <table class="table table-striped table-hover table-bordered">
                        <thead>
                        <tr>
                            <th>Size</th>
                            <th>Create Date</th>
                            <th>Update Date</th>
                            <th></th>

                        </tr>
                        </thead>
                        <tbody>
                        <%
                            List<Size> sizes = (List<Size>) request.getAttribute("sizeList");
                            for (Size size : sizes) {
                                if(!size.isDelete()){
                        %>
                        <tr>
                            <td><%= size.getSize()%></td>
                            <td><%= size.getCreatedDate()%></td>
                            <td><%= size.getUpdatedDate()==null?"Haven't update yet" : size.getUpdatedDate() %></td>
                            <td>
                                <a href="#" onclick="sendDataSize(<%= size.getSizeID()%>)" class="edit" title="Edit" data-toggle="modal" data-target="#size-modal"><i class="material-icons">&#xE254;</i></a>
                                <a href="#" class="delete" title="Delete" data-toggle="tooltip"><i class="material-icons">&#xE872;</i></a>
                            </td>
                        </tr>
                            <%}%>
                        <%}%>
                        </tbody>
                    </table>
                    <table class="table table-striped table-hover table-bordered">
                        <thead>
                        <tr>

                            <th>Color </th>
                            <th></th>

                        </tr>
                        </thead>
                        <tbody>
                        <%
                            List<Color> colors = (List<Color>) request.getAttribute("colorList");
                            for (Color color : colors) {
                                if(!color.isDelete()){
                        %>
                        <tr>
                            <td><%= color.getColor()%></td>


                            <td>
                                <a href="#" onclick="sendDataColor(<%= color.getColorID()%>)" class="edit" title="Edit" data-toggle="modal" data-target="#color-modal"><i class="material-icons">&#xE254;</i></a>
                                <a href="#" class="delete" title="Delete" data-toggle="tooltip"><i class="material-icons">&#xE872;</i></a>
                            </td>
                        </tr>
                        <%}%>
                        <%}%>
                        </tbody>
                    </table>
                    <table class="table table-striped table-hover table-bordered">
                        <thead>
                        <tr>

                            <th>Product Type </th>
                            <th></th>
                        </tr>
                        </thead>
                        <tbody>
                        <%
                            List<ProductType> productTypes = (List<ProductType>) request.getAttribute("productTypeList");
                            for (ProductType productType : productTypes) {
                                if(!productType.isDelete()){
                        %>

                        <tr>
                            <td><%= productType.getTypeName()%></td>


                            <td>
                                <a href="#" onclick="sendDataProductType(<%= productType.getTypeID()%>)" class="edit" title="Edit" data-toggle="modal" data-target="#product-type-modal"><i class="material-icons">&#xE254;</i></a>
                                <a href="#" class="delete" title="Delete" data-toggle="tooltip"><i class="material-icons">&#xE872;</i></a>
                            </td>
                        </tr>
                        <%}%>
                        <%}%>
                        </tbody>
                    </table>

                    <div id="size-modal" class="modal fade" role="dialog">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">

                                    <h3>Update Size Form</h3>
                                </div>
                                <form id="sizeForm" enctype="multipart/form-data">
                                    <div class="modal-body">
                                        <div class="form-group">
                                            <label>Size</label>
                                            <input  type="text" name="size" class="form-control">
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
                    <div id="color-modal" class="modal fade" role="dialog">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">

                                    <h3>Update Size Form</h3>
                                </div>
                                <form id="colorForm" enctype="multipart/form-data">
                                    <div class="modal-body">
                                        <div class="form-group">
                                            <label>color</label>
                                            <input  type="text" name="color" class="form-control">
                                        </div>

                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                        <input type="submit" class="btn btn-success" >
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                    <div id="product-type-modal" class="modal fade" role="dialog">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">

                                    <h3>Update Size Form</h3>
                                </div>
                                <form id="ProductTypeForm" enctype="multipart/form-data">
                                    <div class="modal-body">
                                        <div class="form-group">
                                            <label>productType</label>
                                            <input  type="text" name="productType" class="form-control">
                                        </div>

                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                        <input type="submit" class="btn btn-success" >
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
<script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<script src="assets/vendor/jquery/jquery-3.2.1.min.js"></script>
<script src="assets/js/popper.js"></script>
<script src="assets/js/bootstrap.min.js"></script>
<script src="assets/js/main.js"></script>
<script>
    let idUpdateSize=0;
    function sendDataSize(id){
        idUpdateSize=id;
        return idUpdateSize;
    }
    let idUpdateColor=0;
    function sendDataColor(id){
        idUpdateColor=id;
        return idUpdateColor;
    }
    let idUpdateProductType=0;
    function sendDataProductType(id){
        idUpdateProductType=id;
        return idUpdateProductType;
    }
    $(document).ready(function(){

        $("#sizeForm").submit(function(event) {
            event.preventDefault();

            var url = window.location.href;
            var searchParams = new URLSearchParams(new URL(url).search);
            var pid = searchParams.get("pid");
            // Create a FormData object
            var formData = $(this).serialize();
            formData += '&ProductId=' + pid;
            formData += '&IdUpdate=' + idUpdateSize;

            console.log(formData);
            $.ajax({
                type: "POST",
                url: "ViewDetailProduct",
                data: formData,
                  // Prevent jQuery from processing the data
                contentType: 'application/x-www-form-urlencoded',  // Prevent jQuery from setting the content type
                success: function(response){
                    if(response.trim() === "success") {
                        // Show success message
                        swal("Good job!", "Update Success", "success")
                            .then((value) => {
                                // Redirect to another page
                                window.location.href = "/ClothesShop/AdminManageProduct";
                            });
                    } else {
                        let message = response.trim();
                        swal('${message}', "Please try again.", "error");
                    }
                },
                error: function(xhr, status, error){
                    console.error("Error:", error);
                    swal("Error!", "Something went wrong. Please try again later.", "error");
                }
            });
        });
        $("#colorForm").submit(function(event) {
            event.preventDefault();
            var url = window.location.href;
            var searchParams = new URLSearchParams(new URL(url).search);
            var pid = searchParams.get("pid");
            var id= pid.replace("#","");

            // Create a FormData object
            var formData = $(this).serialize();
            formData += '&ProductId=' + pid;
            formData += '&IdUpdate=' + idUpdateColor;
            $.ajax({
                type: "POST",
                url: "ViewDetailProduct",
                data: formData,
                contentType: false,
                success: function(response){
                    if(response.trim() === "success") {
                        // Show success message
                        swal("Good job!", "Update Success", "success")
                            .then((value) => {
                                // Redirect to another page
                                window.location.href = "/Shop-Clothes/AdminManageProduct";
                            });
                    } else {
                        let message = response.trim();
                        swal(''+message, "Please try again.", "error");
                    }
                },
                error: function(xhr, status, error){
                    console.error("Error:", error);
                    swal("Error!", "Something went wrong. Please try again later.", "error");
                }
            });
        });
        $("#ProductTypeForm").submit(function(event) {
            event.preventDefault();
            var url = window.location.href;
            var searchParams = new URLSearchParams(new URL(url).search);
            var pid = searchParams.get("pid");
            var id= pid.replace("#","");

            // Create a FormData object
            var formData = $(this).serialize();
            formData += '&ProductId=' + pid;
            formData += '&IdUpdate=' + idUpdateProductType;
            $.ajax({
                type: "POST",
                url: "ViewDetailProduct",
                data: formData,
                contentType: false,
                success: function(response){
                    if(response.trim() === "success") {
                        // Show success message
                        swal("Good job!", "Update Success", "success")
                            .then((value) => {
                                // Redirect to another page
                                window.location.href = "/Shop-Clothes/AdminManageProduct";
                            });
                    } else {
                        let message = response.trim();
                        swal(''+message, "Please try again.", "error");
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