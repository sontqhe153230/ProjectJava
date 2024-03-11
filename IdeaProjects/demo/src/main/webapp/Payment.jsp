<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Payment Options Card UI Design</title>
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" >
    <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css">

    <link href="https://fonts.googleapis.com/css?family=Poppins:100,200,300,400,500,600,700,800,900&display=swap" rel="stylesheet">
    <title>Hexashop Ecommerce HTML CSS Template</title>
    <!-- Additional CSS Files -->
    <link rel="stylesheet" type="text/css" href="assets/css/bootstrap.min.css">

    <link rel="stylesheet" type="text/css" href="assets/css/font-awesome.css">

    <link rel="stylesheet" href="assets/css/templatemo-hexashop.css">

    <link rel="stylesheet" href="assets/css/owl-carousel.css">

    <link rel="stylesheet" href="assets/css/lightbox.css">
    <link rel="stylesheet" href="assets/css/Order.css">
</head>
<style>
    @import url("https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap");
    body {
        background: #f9f9f9;
        font-family: "roboto", sans-serif;
    }
    a {
        text-decoration: none;
    }
    .btn{
        width: 100%;
    }
    .main-content {
        padding-top: 100px;
        padding-bottom: 100px;
    }
    .shadow {
        box-shadow: 0 2px 10px rgba(0, 0, 0, 0.06) !important;
    }
    .payment-card__type {
        background: #eee;
    }
    .payment-card__type img {
        width: 50%;
    }
    .add-payment {
        min-height: 290px;
        background: #eee;
        cursor: pointer;
    }
    /* Credit Card */
    .credit-card {
        width: 360px;
        margin: 15px auto 0;
        border: 1px solid #ddd;
        border-radius: 6px;
        background-color: #fff;
        box-shadow: 1px 2px 3px 0px rgba(0, 0, 0, 0.10);
    }
    .form-header {
        height: 60px;
        padding: 20px 30px 0;
        border-bottom: 1px solid #E1E8EE;
    }
    .form-body {
        height: 340px;
        padding: 30px 30px 20px;
    }
    /* Title */
    .title {
        margin: 0;
        color: #5e6977;
        font-size: 18px;
    }
    /* Common */
    .card-number, .cvv-input input, .month select, .paypal-btn, .proceed-btn, .year select {
        height: 42px;
    }
    .card-number, .cvv-input input, .month select, .year select {
        font-size: 14px;
        font-weight: 100;
        line-height: 14px;
    }
    .card-number, .cvv-details, .cvv-input input, .month select, .year select {
        color: #86939e;
        opacity: .7;
    }
    /* Card Number */
    .card-number {
        width: 100%;
        margin-bottom: 20px;
        padding-left: 20px;
        border: 2px solid #e1e8ee;
        border-radius: 6px;
    }
    /* Date Field */
    .month select, .year select {
        -moz-appearance: none;
        -webkit-appearance: none;
        width: 145px;
        margin-bottom: 20px;
        padding-left: 20px;
        border: 2px solid #e1e8ee;
        border-radius: 6px;

        background-position: 85% 50%;
    }
    .month select {
        float: left;
    }
    .year select {
        float: right;
    }
    /* Card Verification Field */
    .cvv-input input {
        width: 145px;
        float: left;
        padding-left: 20px;
        border: 2px solid #e1e8ee;
        border-radius: 6px;
        background: #fff;
    }
    .cvv-details {
        float: right;
        margin-bottom: 20px;
        font-size: 12px;
        font-weight: 300;
        line-height: 16px;
    }
    .cvv-details p {
        margin-top: 6px;
    }
    .modal-body img{
        display: block;
        margin-left: auto;
        margin-right: auto;
        width: 50%;
    }
    .payment-card:hover{
        cursor: pointer;
        color: aqua;
        background-color: #5e6977;
    }
</style>
<body>
<!-- ***** Header Area Start ***** -->
<%@include file="WEB-INF/jspf/header.jsp"%>
<!-- ***** Header Area End ***** -->


<section class="main-content">
    <div class="container">
        <h1 class="text-uppercase text-center">Payment Options Card</h1>
        <br>
        <br>
        <div class="row">


            <div class="col-md-3 col-sm-6 mb-4" onclick="GetAlert()">
                <div class="payment-card rounded-lg shadow bg-white text-center h-100">
                    <div class="payment-card__type px-4 py-3 d-flex justify-content-center align-items-center"> Option</div>
                    <div class="payment-card__info p-4">
                        <h4>Payment on delivery</h4>

                    </div>
                </div>
            </div>
            <div class="col-md-3 col-sm-6 mb-4" data-bs-toggle="modal" data-bs-target="#staticBackdrop">
                <div class="payment-card rounded-lg shadow bg-white text-center h-100">
                    <div class="payment-card__type px-4 py-3 d-flex justify-content-center align-items-center"> Option</div>
                    <div class="payment-card__info p-4">
                        <h4>Pay by qr code</h4>

                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- Modal -->
<div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title text-center" id="staticBackdropLabel">
                    QR CODE
                </h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <img src="110322-qr-code-hmed-425p.webp"  alt="" class="avatar-lg rounded">
                <button onclick="GetAlert()"> Complete</i> </button>
            </div>
        </div>
    </div>
</div>


<!-- ***** Footer Start ***** -->
<%@include file="WEB-INF/jspf/footer.jsp"%>
</body>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<script src="assets/js/jquery-2.1.0.min.js"></script>

<!-- Bootstrap -->
<script src="assets/js/popper.js"></script>
<script src="assets/js/bootstrap.min.js"></script>

<!-- Plugins -->
<script src="assets/js/owl-carousel.js"></script>
<script src="assets/js/accordions.js"></script>
<script src="assets/js/datepicker.js"></script>
<script src="assets/js/scrollreveal.min.js"></script>
<script src="assets/js/waypoints.min.js"></script>
<script src="assets/js/jquery.counterup.min.js"></script>
<script src="assets/js/imgfix.min.js"></script>
<script src="assets/js/slick.js"></script>
<script src="assets/js/lightbox.js"></script>
<script src="assets/js/isotope.js"></script>

<!-- Global Init -->
<script src="assets/js/custom.js"></script>
<script>
    function GetAlert() {
        var status = "success"; // Replace "your_id_here" with the actual ID you want to send

        // Create a new XMLHttpRequest object
        var xhr = new XMLHttpRequest();

        // Specify the request method and URL
        xhr.open("POST", "Payment", true);

        // Set the Content-Type header to indicate the data format
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

        // Define a callback function to handle the response
        xhr.onreadystatechange = function() {
            if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
                // Request finished, do something with the response if needed
                console.log("Response from servlet:", xhr.responseText);
            }
        };

        // Construct the data to be sent in the request body
        var data = "status=" + encodeURIComponent(status);

        // Send the request with the data
        xhr.send(data);


        swal("Complete!", "Order successful!", "success")
            .then((value) => {
                // Redirect to another page
                window.location.href = "/Shop-Clothes";
            });

    }


</script>
<script>

    $(function() {
        var selectedClass = "";
        $("p").click(function(){
            selectedClass = $(this).attr("data-rel");
            $("#portfolio").fadeTo(50, 0.1);
            $("#portfolio div").not("."+selectedClass).fadeOut();
            setTimeout(function() {
                $("."+selectedClass).fadeIn();
                $("#portfolio").fadeTo(50, 1);
            }, 500);

        });
    });

</script>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<script>
    $(function () {

        $('[data-toggle="tooltip"]').tooltip()
    })
</script>

</html>