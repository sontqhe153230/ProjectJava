<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="model.entity.Product" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="controller.OrderServlet" %>
<%@ page import="model.entity.Order" %>

<!DOCTYPE html>
<html>
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link href="https://fonts.googleapis.com/css?family=Poppins:100,200,300,400,500,600,700,800,900&display=swap" rel="stylesheet">

    <title>Hexashop</title>


    <!-- Additional CSS Files -->
    <link rel="stylesheet" type="text/css" href="assets/css/bootstrap.min.css">

    <link rel="stylesheet" type="text/css" href="assets/css/font-awesome.css">

    <link rel="stylesheet" href="assets/css/templatemo-hexashop.css">

    <link rel="stylesheet" href="assets/css/owl-carousel.css">

    <link rel="stylesheet" href="assets/css/lightbox.css">
    <link rel="stylesheet" href="assets/css/Order.css">
    <!--

    TemplateMo 571 Hexashop

    https://templatemo.com/tm-571-hexashop

    -->
</head>
<body>
<!-- ***** Preloader Start ***** -->
<div id="preloader">
    <div class="jumper">
        <div></div>
        <div></div>
        <div></div>
    </div>
</div>
<!-- ***** Preloader End ***** -->


<!-- ***** Header Area Start ***** -->
<%@include file="WEB-INF/jspf/header.jsp"%>
<!-- ***** Header Area End ***** -->




<!-- ***** Products Area Starts ***** -->
<section class="section" id="Cart">
    <div class="container">
        <div class="row">

            <div class="col-xl-8">


                <div class="card border shadow-none">
                    <%
                        Map<Integer, Map<String, Object>> cart = (Map<Integer, Map<String, Object>>) session.getAttribute("cart");
                        if (cart != null) {
                            // Loop through each item in the cart and display its details
                            for (Map.Entry<Integer, Map<String, Object>> entry : cart.entrySet()) {
                                int productId = entry.getKey();
                                int quantity = (int) entry.getValue().get("quantity");
                                int size = (int) entry.getValue().get("size");
                                int color = (int) entry.getValue().get("color");

                                // Now you can display the product ID, quantity, size, and color
                    %>
                    <div class="card-body">

                        <div class="d-flex align-items-start border-bottom pb-3">
                            <div class="me-4">
                                <img src="<%= new OrderServlet().GetUrlByProductId(productId) %>" alt="" class="avatar-lg rounded">
                            </div>
                            <div class="flex-grow-1 align-self-center overflow-hidden">
                                <div>
                                    <h5 class="text-truncate font-size-18"><a href="#" class="text-dark"><%= new OrderServlet().GetNameById(productId) %></a></h5>
                                    <p class="text-muted mb-0">
                                        <i class="bx bxs-star text-warning"></i>
                                        <i class="bx bxs-star text-warning"></i>
                                        <i class="bx bxs-star text-warning"></i>
                                        <i class="bx bxs-star text-warning"></i>
                                        <i class="bx bxs-star-half text-warning"></i>
                                    </p>
                                    <p class="mb-0 mt-1">Color : <span class="fw-medium"><%= new OrderServlet().GetColorTypeById(color) %></span></p>
                                    <p class="mb-0 mt-1">Size : <span class="fw-medium"><%= new OrderServlet().GetSizeTypeById(size) %></span></p>
                                </div>
                            </div>
                            <div class="flex-shrink-0 ms-2">
                                <ul class="list-inline mb-0 font-size-16">
                                    <li class="list-inline-item">
                                        <a href="#" class="text-muted px-1">
                                            <i class="mdi mdi-trash-can-outline"></i>
                                        </a>
                                    </li>
                                    <li class="list-inline-item">
                                        <a href="#" class="text-muted px-1">
                                            <i class="mdi mdi-heart-outline"></i>
                                        </a>
                                    </li>
                                </ul>
                            </div>
                        </div>

                        <div>
                            <div class="row">
                                <div class="col-md-4">
                                    <div class="mt-3">
                                        <p class="text-muted mb-2">Price</p>
                                        <h5 class="mb-0 mt-2">$<%= new OrderServlet().GetPrice(productId) %></h5>
                                    </div>
                                </div>
                                <div class="col-md-3">
                                    <div class="mt-3">
                                        <p class="text-muted mb-2">Quantity</p>
                                        <div class="d-inline-flex">
                                            <h5 class="mb-0 mt-2"><%= quantity %></h5>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-3">
                                    <div class="mt-3">
                                        <p class="text-muted mb-2">Total</p>
                                        <h5>$<%= new OrderServlet().GetTotalPrice(productId,quantity) %></h5>
                                    </div>
                                </div>
                                <div class="col-md-2">
                                    <div class="mt-3">
                                        <p class="text-muted mb-2">Remove</p>
                                        <button onclick="RemoveItem(<%= productId %>)">Remove</button>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>
                   <% } %>
                    <% } %>
                </div>
                <!-- end card -->


                <div class="row my-4">
                    <div class="col-sm-6">
                        <a href="/ClothesShop" class="btn btn-link text-muted">
                            <i class="mdi mdi-arrow-left me-1"></i> Continue Shopping </a>
                    </div> <!-- end col -->
                    <div class="col-sm-6">
                        <div class="text-sm-end mt-2 mt-sm-0">
                            <a href="Payment" class="btn btn-success">
                                <i class="mdi mdi-cart-outline me-1"></i> Checkout </a>
                        </div>
                    </div> <!-- end col -->
                </div> <!-- end row-->


            </div>
            <%
                // Lấy giỏ hàng từ session
                Map<Integer, Map<String, Object>> cart2 = (Map<Integer, Map<String, Object>>) session.getAttribute("cart");
                if (cart2 != null) { %>
            <div class="col-xl-4">
                <div class="mt-5 mt-lg-0">
                    <div class="card border shadow-none">
                        <div class="card-header bg-transparent border-bottom py-3 px-4">
                            <h5 class="font-size-16 mb-0">Order Summary <span class="float-end"></span></h5>
                        </div>
                        <div class="card-body p-4 pt-2">

                            <div class="table-responsive">
                                <table class="table mb-0">
                                    <tbody>


                                    <tr class="bg-light">
                                        <th>Total :</th>
                                        <td class="text-end">
                                            <span class="fw-bold">
                                                $ <%= new OrderServlet().GetOfTotalCart(cart2) %>
                                            </span>
                                        </td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                            <!-- end table-responsive -->
                        </div>
                    </div>
                </div>
            </div>
            <%}%>
        </div>
        <!-- end row -->

    </div>
</section>
<div id="content" class="p-4 p-md-5">
    <div class="container-xl">
        <div class="table-responsive">
            <div class="table-wrapper">
                <div class="table-title">
                    <div class="row">
                        <div class="col-sm-8"><h2>Manage <b>Order</b></h2></div>
                    </div>
                </div>
                <table class="table table-striped table-hover table-bordered">
                    <thead>

                    <tr>
                        <th>OrderId</th>
                        <th>Product name<i class="fa fa-sort"></i></th>
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
                        <td><%= new OrderServlet().GetNameById(order.getProductId())%></td>
                        <td><%= order.getDescription()%></td>
                        <td><%= order.isPayment()==true?"Done":"Not yet" %></td>
                        <td><%= order.getDeliveryStatus()%></td>
                        <td><%= order.getCreatedDate()%></td>

                    </tr>
                    <%}%>
                    <%}%>
                    </tbody>
                </table>

            </div>
        </div>
    </div>
</div>
</div>

<!-- ***** Products Area Ends ***** -->

<!-- ***** Footer Start ***** -->
<%@include file="WEB-INF/jspf/footer.jsp"%>

<!-- jQuery -->
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

    function RemoveItem(productId){

            // Example of sending an AJAX request to the server
            var xhr = new XMLHttpRequest();
            xhr.open("POST", "Cart", true);
            xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
            xhr.onreadystatechange = function() {
                if (xhr.readyState === 4 && xhr.status === 200) {
                    // Handle response from server if needed
                    console.log("Item removed successfully.");
                }
            };
            xhr.send("productId=" + encodeURIComponent(productId));
        location.reload();
    }

</script>
</body>
</html>