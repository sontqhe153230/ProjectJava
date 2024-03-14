<%@ page import="model.entity.Product" %>
<!doctype html>
<html lang="en">
<head>
    <title>Sidebar 01</title>
    <meta charset="UTF-8">
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
    <link rel="stylesheet" href="assets/css/Form.css">
</head>
<body>

<div class="wrapper d-flex align-items-stretch">

    <%@include file="WEB-INF/jspf/Admin/sidebar.jsp"%>
    <!-- Page Content  -->
    <div class="container-fluid">

        <div class="container">
            <!-- Title -->
            <div class="d-flex justify-content-between align-items-lg-center py-3 flex-column flex-lg-row">
                <h2 class="h5 mb-3 mb-lg-0"><a href="../../pages/admin/customers.html" class="text-muted"><i class="bi bi-arrow-left-square me-2"></i></a> Update Account</h2>

            </div>

            <form id="FormAdd" enctype="multipart/form-data">
                <!-- Main content -->
                <div class="row">
                    <!-- Left side -->

                    <div class="col-lg-8">
                        <!-- Basic information -->

                        <!-- Address -->
                        <div class="card mb-4">
                            <div class="card-body">
                                <h3 class="h6 mb-4">Account Detail</h3>
                                <div class="mb-3">
                                    <label class="form-label">UserName</label>
                                    <input type="text" class="form-control" value="" name="username">
                                </div>
                                <div class="mb-3">
                                    <label class="form-label">Password</label>
                                    <input type="text" class="form-control" value="" name="password" readonly>
                                </div>
                                <div class="mb-3">
                                    <label class="form-label">Email</label>
                                    <input type="text" class="form-control"name="email" value="">
                                </div>
                                <div class="mb-3">
                                    <label class="form-label">Phone</label>
                                    <input type="text" class="form-control"name="phone" value="">
                                </div>
                                <div class="mb-3">
                                    <label class="form-label">Address</label>
                                    <input type="text" class="form-control"name="address" value="">
                                </div>
                                <div class="mb-3">
                                    <label class="form-label">Name</label>
                                    <input type="text" class="form-control"name="name" value="">
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- Right side -->
                    <div class="col-lg-4">
                        <!-- Status -->
                        <!-- Avatar -->
                        <!-- Notes -->
                        <div class="card mb-4">
                            <div class="card-body">
                                <div class="hstack gap-3">
                                    <button class="btn btn-light btn-sm btn-icon-text"><i class="bi bi-x"></i> <span class="text">Cancel</span></button>
                                    <button id="sendData" class="btn btn-primary btn-sm btn-icon-text"><i class="bi bi-save"></i> <span class="text">Save</span></button>
                                </div>
                            </div>
                        </div>

                    </div>


                </div>
            </form>

        </div>

    </div>
</div>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>

<script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<script src="assets/vendor/jquery/jquery-3.2.1.min.js"></script>
<script src="assets/js/popper.js"></script>
<script src="assets/js/bootstrap.min.js"></script>
<script src="assets/js/main.js"></script>
<script>
    $(document).ready(function(){
        $("#FormAdd").submit(function(event){
            // Prevent default form submission
            event.preventDefault();

            // Get form data
            var formData = $(this).serialize();

            // Submit form data using AJAX
            $.ajax({
                type: "POST",
                url: "AddNewAccount",
                data: formData,
                success: function(response){
                    if(response.trim() === "user") {
                        // Show success message
                        swal("Good job!", "Login successful!", "success")
                            .then((value) => {
                                // Redirect to another page
                                window.location.href = "/ClothesShop";
                            });
                    }
                    else if(response.trim() === "admin") {
                        // Show success message
                        swal("Good job!", "Login successful!", "success")
                            .then((value) => {
                                // Redirect to another page
                                window.location.href = "Admin";

                            });
                    }
                    else {
                        swal("Wrong username or password!", "Please try again.", "error");
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