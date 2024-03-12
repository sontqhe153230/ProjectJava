<%@ page import="model.entity.Account" %>
<%@ page import="java.util.List" %>
<%@ page import="controller.AdminManageAccountServlet" %>
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
                <h2 class="h5 mb-3 mb-lg-0"><a href="../../pages/admin/customers.html" class="text-muted"><i class="bi bi-arrow-left-square me-2"></i></a> Create new product</h2>

            </div>
<form id="FormAdd">
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
                        <input type="text" class="form-control" name="ProductName">
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Description</label>
                        <input type="text" class="form-control"name="Description">
                    </div>
                    <div class="row">
                        <div class="col-lg-6">
                            <div class="mb-3">
                                <label class="form-label">Product Type</label>
                                <select name="ProductType" class="select2 form-control select2-hidden-accessible" data-select2-placeholder="Select city" data-select2-id="select2-data-7-809c" tabindex="-1" aria-hidden="true">
                                    <option data-select2-id="select2-data-9-k35n"></option>
                                    <option value="b">Bangkok</option>
                                    <option value="d">Dubai</option>
                                    <option value="h">Hong Kong</option>
                                    <option value="k">Kuala Lumpur</option>
                                    <option value="l">London</option>
                                    <option value="n">New York City</option>
                                    <option value="m">Macau</option>
                                    <option value="p">Paris</option>
                                </select>

                            </div>
                        </div>
                        <div class="col-lg-6">
                            <div class="mb-3">
                                <label class="form-label">Price</label>
                                <input type="text" class="form-control" name="Price">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-6">
                            <div class="mb-3">
                                <label class="form-label">Size</label>
                                <select name="size" class="select2 form-control select2-hidden-accessible" data-select2-placeholder="Select city" data-select2-id="select2-data-7-809c" tabindex="-1" aria-hidden="true">
                                    <option data-select2-id="select2-data-9-k35n"></option>
                                    <option value="b">Bangkok</option>
                                    <option value="d">Dubai</option>
                                    <option value="h">Hong Kong</option>
                                    <option value="k">Kuala Lumpur</option>
                                    <option value="l">London</option>
                                    <option value="n">New York City</option>
                                    <option value="m">Macau</option>
                                    <option value="p">Paris</option>
                                </select>
                            </div>
                            <div class="mb-3">
                                <label class="form-label">Color</label>
                                <input type="text" class="form-control" name="Color">
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
                    <input type="file" id="image-input" class="form-control" accept="image/*">
                    <div id="image-preview"></div>
                </div>
            </div>
            <!-- Notes -->
            <div class="card mb-4">
                <div class="card-body">
                    <div class="hstack gap-3">
                        <button class="btn btn-light btn-sm btn-icon-text"><i class="bi bi-x"></i> <span class="text">Cancel</span></button>
                        <button class="btn btn-primary btn-sm btn-icon-text"><i class="bi bi-save"></i> <span class="text">Save</span></button>
                    </div>
                </div>
            </div>

        </div>


    </div>
</form>

        </div>

    </div>
</div>
<script>
    const imageInput = document.getElementById('image-input');
    const imagePreview = document.getElementById('image-preview');

    imageInput.addEventListener('change', function() {
        const file = this.files[0];
        if (file) {
            const reader = new FileReader();
            reader.onload = function(e) {
                const img = document.createElement('img');
                img.src = e.target.result;
                imagePreview.innerHTML = '';
                imagePreview.appendChild(img);
            }
            reader.readAsDataURL(file);
        } else {
            imagePreview.innerHTML = 'No image selected';
        }
    });
</script>
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
                url: "AddNewProduct",
                data: formData,
                success: function(response){
                    if(response.trim() === "success") {
                        // Show success message
                        swal("Good job!", "Add New Product Success", "success")
                            .then((value) => {
                                // Redirect to another page
                                window.location.href = "/Shop-Clothes";
                            });
                    }

                    else {
                        let message=response.trim();
                        swal('${message}', "Please try again.", "error");
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
<script src="js/jquery.min.js"></script>
<script src="js/popper.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/main.js"></script>
</body>
</html>