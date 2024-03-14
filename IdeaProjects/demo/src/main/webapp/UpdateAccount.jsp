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
            <%
                Product product=(Product)request.getAttribute("product");

            %>
            <form id="FormUpdate" enctype="multipart/form-data">
                <!-- Main content -->
                <div class="row">
                    <!-- Left side -->

                    <div class="col-lg-8">
                        <!-- Basic information -->

                        <!-- Address -->
                        <div class="card mb-4">
                            <div class="card-body">
                                <h3 class="h6 mb-4">Product Detail</h3>
                                <div class="mb-3">
                                    <label class="form-label">product Name</label>
                                    <input type="text" class="form-control" value="<%=product.getProductName()%>" name="ProductName">
                                </div>
                                <div class="mb-3">
                                    <label class="form-label">Id</label>
                                    <input type="text" class="form-control" value="<%=product.getProductID()%>" name="ProductId" readonly>
                                </div>
                                <div class="mb-3">
                                    <label class="form-label">Description</label>
                                    <input type="text" class="form-control"name="Description" value="<%=product.getDescription()%>">
                                </div>
                                <div class="row">
                                    <div class="col-lg-6">
                                        <div class="mb-3">
                                            <label class="form-label">Price</label>
                                            <input type="text" class="form-control" value="<%=product.getPrice()%>" name="Price">
                                        </div>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>
                    <!-- Right side -->
                    <div class="col-lg-4">
                        <!-- Status -->

                        <!-- Avatar -->
                        <div class="card mb-4">
                            <div class="card-body">
                                <h3 class="h6">Product Image</h3>
                                <input type="file" id="image-input" name="image-input" class="form-control" accept="image/*" onchange="previewImage()">
                                <img  id="image-previous" src="assets/images/<%=product.getIMG()%>" width="200px" height="300px">
                                <div id="image-preview"></div>
                            </div>
                        </div>
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
        $("#FormUpdate").submit(function(event){
            // Prevent default form submission
            event.preventDefault();

            // Create a FormData object
            var formData = new FormData(this);
            console.log(formData);

            // Submit form data using AJAX
            $.ajax({
                type: "POST",
                url: "UpdateProduct",
                data: formData,
                processData: false,  // Prevent jQuery from processing the data
                contentType: false,  // Prevent jQuery from setting the content type

                success: function(response){
                    if(response.trim() === "success") {
                        // Show success message
                        swal("Good job!", "Change Success", "success")
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
                    console.error("xhr:", xhr);
                    swal("Error!", "Something went wrong. Please try again later.", "error");
                }
            });
        });
    });

</script>
<script>
    // Function to preview selected image
    function previewImage() {
        var fileInput = document.getElementById('image-input');
        var imagePreview = document.getElementById('image-preview');

        if (fileInput.files && fileInput.files[0]) {
            var reader = new FileReader();
            reader.onload = function(e) {
                imagePreview.innerHTML = '<img src="' + e.target.result + '" width="200px" height="300px">';
                checkImagePreview();
            };
            reader.readAsDataURL(fileInput.files[0]);
        }
    }

    // Function to check image preview and disable image-previous if necessary
    function checkImagePreview() {
        var imagePreview = document.getElementById('image-preview');
        var imgElement = imagePreview.querySelector('img');
        var imagePrevious = document.getElementById('image-previous');

        if (imgElement) {
            // If image exists in #image-preview, disable #image-previous
            imagePrevious.style.pointerEvents = 'none';
            imagePrevious.style.opacity = '0.5'; // Optional: Reduce opacity to indicate it's disabled
        } else {
            // If no image in #image-preview, enable #image-previous
            imagePrevious.style.pointerEvents = 'auto';
            imagePrevious.style.opacity = '1'; // Optional: Restore original opacity
        }
    }

    // Call checkImagePreview() initially to disable image-previous if necessary
    checkImagePreview();

</script>


</body>
</html>