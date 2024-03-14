<header class="header-area header-sticky">
    <div class="container">
        <div class="row">
            <div class="col-12">
                <nav class="main-nav">
                    <!-- ***** Logo Start ***** -->
                    <a href="/ClothesShop" class="logo">
                        <img src="assets/images/logo.png">
                    </a>
                    <!-- ***** Logo End ***** -->
                    <!-- ***** Menu Start ***** -->
                    <ul class="nav">
                        <li class="scroll-to-section"><a href="/ClothesShop" class="active">Home</a></li>
                        <li class="submenu">
                            <a href="javascript:;">Pages</a>
                            <ul>
                                <li><a href="about">About Us</a></li>
                                <li><a href="contact">Contact Us</a></li>
                            </ul>
                        </li>

                        <% if(session.getAttribute("loggedInUser")=="" ||session.getAttribute("loggedInUser")==null) {%>
                        <li class="scroll-to-section"><a href="Login">Log In</a></li>
                        <%}%>
                        <% if(session.getAttribute("loggedInUser")!=""&&session.getAttribute("loggedInUser")!=null) {%>
                        <li class="scroll-to-section"><a href="Cart">Cart</a></li>
                        <li class="scroll-to-section"><a href="profile">Profile</a></li>
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