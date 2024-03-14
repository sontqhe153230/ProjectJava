<%@ page import="model.entity.Product" %>
<%@ page import="java.util.List" %>
<%@ page import="controller.AdminManageProductServlet" %>
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
                            <th>ProductId</th>
                            <th>Product Name </th>
                            <th>Price</th>
                            <th>Description </th>

                            <th>CreateDate</th>
                        </tr>
                        </thead>
                        <tbody>
                        <%
                            List<Product> products = (List<Product>) request.getAttribute("productList");
                            for (Product product : products) {
                               if(!product.isDelete()){
                        %>
                        <tr>
                            <td><%= product.getProductID()%></td>
                            <td><%= product.getProductName()%></td>
                            <td><%= product.getPrice()%></td>
                            <td><%= product.getDescription()%></td>
                            <td><%= product.getCreatedDate()%></td>
                            <td>
                                <a href="ViewDetailProduct?pid=<%= product.getProductID()%>" class="view" title="View" data-toggle="tooltip"><i class="material-icons">&#xE417;</i></a>
                                <a href="UpdateProduct?pid=<%= product.getProductID()%>" class="edit" title="Edit" data-toggle="tooltip"><i class="material-icons">&#xE254;</i></a>
                                <a href="#" onclick="ConfirmDelete(<%= product.getProductID()%>)" class="delete" title="Delete" data-toggle="tooltip"><i class="material-icons">&#xE872;</i></a>
                            </td>
                        </tr>
                      <%}%>
                        <%}%>
                        </tbody>
                    </table>
                    <div class="clearfix">
                        <div class="hint-text">Showing <b>5</b> out of <b>25</b> entries</div>
                        <ul class="pagination">
                            <li class="page-item disabled"><a href="#"><i class="fa fa-angle-double-left"></i></a></li>
                            <li class="page-item"><a href="#" class="page-link">1</a></li>
                            <li class="page-item"><a href="#" class="page-link">2</a></li>
                            <li class="page-item active"><a href="#" class="page-link">3</a></li>
                            <li class="page-item"><a href="#" class="page-link">4</a></li>
                            <li class="page-item"><a href="#" class="page-link">5</a></li>
                            <li class="page-item"><a href="#" class="page-link"><i class="fa fa-angle-double-right"></i></a></li>
                        </ul>
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
   function ConfirmDelete(id){
       swal({
           title: "Are you sure?",
           text: "Once deleted, you will not be able to recover this imaginary file!",
           icon: "warning",
           buttons: true,
           dangerMode: true,
       })
           .then((willDelete) => {
               if (willDelete) {
                   swal("Poof! Your product has been deleted!", {
                       icon: "success",
                   })
                       .then((value) => {
                           // Redirect to another page
                           window.location.href = "/ClothesShop/DeleteProduct?pid="+id;
                       });
                   ;
               } else {
                   swal("Your imaginary file is safe!");
               }
           });


   }

</script>

</body>
</html>