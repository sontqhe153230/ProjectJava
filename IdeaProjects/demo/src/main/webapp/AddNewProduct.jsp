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
<form id="FormAdd" enctype="multipart/form-data">
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
                                <label class="form-label">ProductType</label>
                                <input type="text" id="textInputProductType" placeholder="Enter text and press Enter">
                                <select id="optionsListProductType"></select>


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
                                <input type="text" id="textInputSize" placeholder="Enter text and press Enter">
                                <select id="optionsListSize">
                                    <!-- Options will be dynamically added here -->
                                </select>
                            </div>
                            <div class="mb-3">
                                <label class="form-label">Color</label>
                                <input type="text" id="textInputColor" placeholder="Enter text and press Enter">
                                <input type="file" id="imageInputColor" hidden>
                                <select id="optionsListColor">
                                    <!-- Options will be dynamically added here -->
                                </select>
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
                    <input type="file" id="image-input" name="image-input" class="form-control" accept="image/*">
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
<script>
    document.addEventListener('DOMContentLoaded', function() {
        const textInputSize = document.getElementById('textInputSize');
        const optionsListSize = document.getElementById('optionsListSize');
        const textInputColor = document.getElementById('textInputColor');
        const optionsListColor = document.getElementById('optionsListColor');
        const imageInput = document.getElementById('imageInputColor');
        const textInputProductType = document.getElementById('textInputProductType');
        const optionsListProductType = document.getElementById('optionsListProductType');

         const sendOptionsBtn = document.getElementById('sendData');

        textInputSize.addEventListener('keydown', function(event) {
            if (event.key === 'Enter') {
                event.preventDefault(); // Prevent form submission

                const inputValue = textInputSize.value.trim();
                if (inputValue !== '') {
                    const options = inputValue.split(' '); // Split input by spaces

                    options.forEach(optionText => {
                        if (optionText !== '') {
                            const option = document.createElement('option');
                            option.value = optionText;
                            option.text = optionText;
                            optionsListSize.appendChild(option);
                        }
                    });

                    textInputSize.value = ''; // Clear input field after adding options
                }
            }
        });

        textInputColor.addEventListener('keydown', function(event) {
            if (event.key === 'Enter') {
                event.preventDefault(); // Prevent form submission

                const inputValue = textInputColor.value.trim();
                if (inputValue !== '') {
                    const option = document.createElement('option');
                    option.value = inputValue;
                    option.text = inputValue;

                    optionsListColor.appendChild(option);

                    textInputColor.value = ''; // Clear input field after adding option
                    imageInput.value = ''; // Clear image input field after adding option
                }
            }
        });

        imageInput.addEventListener('change', function() {
            const inputValue = textInputColor.value.trim();
            const imageFile = imageInput.files[0];
            const imageName = imageFile ? imageFile.name : '';
            const optionText = inputValue + (inputValue && imageName ? ', ' : '') + imageName;

            if (optionText !== '') {
                const option = document.createElement('option');
                option.value = optionText;
                option.text = optionText;

                // Add image to the option if available
                if (imageName) {
                    const imageUrl = URL.createObjectURL(imageFile);
                    const img = document.createElement('img');
                    img.src = imageUrl;
                    img.alt = inputValue;
                    img.className = 'option-image';
                    option.appendChild(img);
                }

                optionsListColor.appendChild(option);

                textInputColor.value = ''; // Clear input field after adding option
                imageInput.value = ''; // Clear image input field after adding option
            }
        });

        textInputProductType.addEventListener('keydown', function(event) {
            if (event.key === 'Enter') {
                event.preventDefault(); // Prevent form submission

                const inputValue = textInputProductType.value.trim();
                if (inputValue !== '') {
                    const options = inputValue.split(' '); // Split input by spaces

                    options.forEach(optionText => {
                        if (optionText !== '') {
                            const option = document.createElement('option');
                            option.value = optionText;
                            option.text = optionText;
                            optionsListProductType.appendChild(option);
                        }
                    });

                    textInputProductType.value = ''; // Clear input field after adding options
                }
            }
        });


        // sendOptionsBtn.addEventListener('click', function() {
        //     const selectedOptionsSize = Array.from(optionsListSize.options).map(option => option.value);
        //     const selectedOptionsColor = Array.from(optionsListColor.options).map(option => option.value);
        //     const selectedOptionsProductType = Array.from(optionsListProductType.options).map(option => option.value);
        //     sendOptionsToController(selectedOptionsSize,selectedOptionsColor,selectedOptionsProductType);
        // });
        //
        // function sendOptionsToController(optionsSize, optionsColor,optionProductType) {
        //     const xhr = new XMLHttpRequest();
        //     xhr.open('POST', 'AddNewProduct', true);
        //     xhr.setRequestHeader('Content-Type', 'application/json');
        //     xhr.onreadystatechange = function() {
        //         if (xhr.readyState === XMLHttpRequest.DONE) {
        //             if (xhr.status === 200) {
        //                 console.log('Options sent successfully');
        //             } else {
        //                 console.error('Failed to send options');
        //             }
        //         }
        //     };
        //
        //     const data = {
        //         optionsSize: Array.from(optionsSize.options).map(option => option.value),
        //         optionsColor: Array.from(optionsColor.options).map(option => option.value),
        //         optionProductType:Array.from(optionProductType.options).map(option => option.value)
        //     };
        //
        //     xhr.send(JSON.stringify(data));
        // }
    });
</script>

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

            // Create a FormData object
            var formData = new FormData(this);

            const optionsListSize = document.getElementById('optionsListSize');
            const optionsListColor = document.getElementById('optionsListColor');
            const optionsListProductType = document.getElementById('optionsListProductType');
            // Get selected options
            var selectedOptionsSize = Array.from(optionsListSize.options).map(option => option.value);
            var selectedOptionsColor = Array.from(optionsListColor.options).map(option => option.value);
            var selectedOptionsProductType = Array.from(optionsListProductType.options).map(option => option.value);

            // Append options data to FormData object
            formData.append('optionsSize', JSON.stringify(selectedOptionsSize));
            formData.append('optionsColor', JSON.stringify(selectedOptionsColor));
            formData.append('optionsProductType', JSON.stringify(selectedOptionsProductType));

            console.log(formData);

            // Submit form data using AJAX
            $.ajax({
                type: "POST",
                url: "AddNewProduct",
                data: formData,
               processData: false  ,// Prevent jQuery from processing the data
                contentType: false,  // Prevent jQuery from setting the content type

                success: function(response){
                    console.log(response);
                    if(response.trim() === "success") {
                        // Show success message
                        swal("Good job!", "Add New Product Success", "success")
                            .then((value) => {
                                // Redirect to another page
                                window.location.href = "/ClothesShop/AdminManageProduct";
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
<script src="js/jquery.min.js"></script>
<script src="js/popper.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/main.js"></script>
</body>
</html>