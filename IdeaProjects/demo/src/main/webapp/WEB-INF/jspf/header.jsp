<header class="header-area header-sticky">
    <div class="container">
        <div class="row">
            <div class="col-12">
                <nav class="main-nav">
                    <!-- ***** Logo Start ***** -->
                    <a href="index.html" class="logo">
                        <img src="assets/images/logo.png">
                    </a>
                    <!-- ***** Logo End ***** -->
                    <!-- ***** Menu Start ***** -->
                    <ul class="nav">
                        <li class="scroll-to-section"><a href="index.html" class="active">Home</a></li>
                        <li class="submenu">
                            <a href="javascript:;">Category</a>
                            <ul>
                                <li><a href="#">Men</a></li>
                                <li><a href="#">Women</a></li>
                                <li><a rel="nofollow" href="https://templatemo.com/page/4" target="_blank">Template Page 4</a></li>
                            </ul>
                        </li>
                        <li class="submenu">
                            <a href="javascript:;">Pages</a>
                            <ul>
                                <li><a href="about.html">About Us</a></li>
                                <li><a href="products.jsp">Products</a></li>
                                <li><a href="single-product.html">Single Product</a></li>
                                <li><a href="contact.html">Contact Us</a></li>
                            </ul>
                        </li>
                        <% if(session.getAttribute("loggedInUser")=="" ||session.getAttribute("loggedInUser")==null) {%>
                        <li class="scroll-to-section"><a href="Login">Log In</a></li>
                        <%}%>
                        <% if(session.getAttribute("loggedInUser")!=""&&session.getAttribute("loggedInUser")!=null) {%>
                        <li class="scroll-to-section"><a href="Cart">Cart</a></li>
                        <li class="scroll-to-section"><a href="index.html">Nguyen Van A</a></li>
                        <li class="scroll-to-section"><a href="Logout">Log Out</a></li>
                        <%}%>
                    </ul>
                    <a class='menu-trigger'>
                        <span>Menu</span>
                    </a>
                    <!-- ***** Menu End ***** -->
                </nav>
            </div>
        </div>
    </div>
</header>