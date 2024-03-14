<%@ page import="model.entity.Product" %>
<%@ page import="java.util.List" %>
<%@ page import="model.entity.Size" %>
<%@ page import="controller.ProductDetailServlet" %>
<%@ page import="model.entity.Color" %>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link href="https://fonts.googleapis.com/css?family=Poppins:100,200,300,400,500,600,700,800,900&display=swap" rel="stylesheet">

    <title>Hexashop - Product Detail Page</title>


    <!-- Additional CSS Files -->
    <link rel="stylesheet" type="text/css" href="assets/css/bootstrap.min.css">

    <link rel="stylesheet" type="text/css" href="assets/css/font-awesome.css">

    <link rel="stylesheet" href="assets/css/templatemo-hexashop.css">

    <link rel="stylesheet" href="assets/css/owl-carousel.css">

    <link rel="stylesheet" href="assets/css/lightbox.css">
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

<!-- ***** Main Banner Area Start ***** -->
<div class="page-heading" id="top">
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <div class="inner-content">
                    <h2>Single Product Page</h2>
                    <span>Awesome &amp; Creative HTML CSS layout by TemplateMo</span>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- ***** Main Banner Area End ***** -->


<!-- ***** Product Area Starts ***** -->
<section class="section" id="product">
    <div class="container">
        <div class="row">
            <%
               Product product=(Product)request.getAttribute("product");

            %>
            <div class="col-lg-8">
                <div class="left-images">
                    <img src="<%= product.getIMG()%>" alt="">

                </div>
            </div>
            <div class="col-lg-4">
                <div class="right-content">
                    <h4><%= product.getProductName()%></h4>
                    <span class="price">$<%= product.getPrice()%></span>
                    <ul class="stars">
                        <li><i class="fa fa-star"></i></li>
                        <li><i class="fa fa-star"></i></li>
                        <li><i class="fa fa-star"></i></li>
                        <li><i class="fa fa-star"></i></li>
                        <li><i class="fa fa-star"></i></li>
                    </ul>
                    <span><%= product.getDescription()%></span>






                    <div class="quantity-content">
                      <h3>Size</h3>
                        <%
                            List<Size> sizes= (List<Size>) new ProductDetailServlet().getAllSizeByProductId(product.getProductID());
                            for (int i=0;i<sizes.size();i++) {
                        %>
                        <input type="radio"  id="size<%=i%>"  name="size<%=i%>" value="<%=sizes.get(i).getSizeID()%>">
                        <label for="<%=sizes.get(i).getSize()%>"><%=sizes.get(i).getSize()%></label><br>
                        <%}%>
                        <h3>Color</h3>
                        <%
                            List<Color> colors = (List<Color>) new ProductDetailServlet().getAllColorByProductId(product.getProductID());
                            for (int i=0;i<colors.size();i++) {
                        %>
                        <input type="radio" id="color<%=i%>" name="color<%=i%>" value="<%=colors.get(i).getColorID()%>">
                        <label for="<%=colors.get(i).getColor()%>"><%=colors.get(i).getColor()%></label><br>
                        <%}%>
                        <div class="left-content">
                            <h6>No. of Orders</h6>
                        </div>
                        <div class="right-content">
                            <div class="quantity buttons_added">
                                <input type="button" value="-" class="minus"><input type="number" step="1" min="1" max="" name="quantity" value="1" title="Qty" class="input-text qty text" size="4" pattern="" inputmode=""><input type="button" value="+" class="plus">
                            </div>
                        </div>
                    </div>
                    <div class="total">
                        <h4>Total: $210.00</h4>
                        <div class="main-border-button"><a href="javascript:void(0);"onchange="addToCart(<%= product.getProductID()%>,<%= sizes.size()%>,<%= colors.size()%>)" onclick="addToCart(<%= product.getProductID()%>,<%= sizes.size()%>,<%= colors.size()%>)">Add To Cart</a></div>
                    </div>
                </div>
            </div>

        </div>
    </div>
</section>
<!-- ***** Product Area Ends ***** -->

<!-- ***** Footer Start ***** -->
<%@include file="WEB-INF/jspf/footer.jsp"%>



<!-- jQuery -->
<script src="assets/js/jquery-2.1.0.min.js"></script>

<!-- Bootstrap -->
<script src="assets/js/popper.js"></script>
<script src="assets/js/bootstrap.min.js"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
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
<script src="assets/js/quantity.js"></script>

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
    function addToCart(productId,size,color) {
        console.log(size,color);
        var isCheckSizeSelect=false;
        var isCheckColorSelect=false;
        var sizeSelect="";
        var colorSelect="";
        for(let i=0;i<size;i++){
         var SizeName="size"+i ;
            console.log(SizeName);
            var radios = document.getElementById(SizeName);
            console.log(radios);
         if(radios.checked){
             isCheckSizeSelect=true;
             sizeSelect=radios.value;
             console.log(sizeSelect,isCheckSizeSelect);
         }
        }
        for(let i=0;i<color;i++){
            var ColorName="color"+i ;
            var radios2 = document.getElementById(ColorName);
            if(radios2.checked){
                isCheckColorSelect=true;
                colorSelect=radios2.value;
                console.log(colorSelect,isCheckColorSelect);
            }
        }


        var qualityValue = document.querySelector('input[name="quantity"]').value;
        console.log(qualityValue);

        if(isCheckSizeSelect===false && isCheckColorSelect===false){
            swal("Error!", "Please Select Size and Color", "error");
        }
        else if(isCheckSizeSelect===false){
            swal("Error!", "Please Select Size", "error");
        }
       else if(isCheckColorSelect===false){
            swal("Error!", "Please Select Color", "error");
        }
       else{
            var url = "Cart?id=" + productId + "&quantity=" + qualityValue+ "&size="+sizeSelect+"&color="+colorSelect;
            console.log(url); // Just for testing, you can modify this to send the URL wherever you need
            // Here you can perform any further actions with the constructed URL, like sending a request
            window.location.href = url;
        }

    }
</script>

</body>

</html>
