<%-- 
    Document   : stockeepHeader
    Created on : Jul 23, 2022, 12:42:08 PM
    Author     : 84348
--%>

<%@page import="user.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
        <link href="https://fonts.googleapis.com/css?family=Poppins" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css">
        <link rel="stylesheet" href="css/styles.css">
        <title>Welcome Stockkeeper Page</title>
    </head>
    <body>
        <div class="header">
            <div class="header-container">
                <div class=" header-item">
                    <img style="width: 5rem" src="img/logo.png" alt="alt"/>
                    <div class="dropdown inline-block ">
                        <span  data-icon="fluent:line-horizontal-3-20-filled" class="iconify dropdown-toggle" href="#"  data-bs-toggle="dropdown" aria-expanded="false">
                        </span>
                        <!--//phan drop-down. chinh so luong element trong nay.-->
                        <ul class="dropdown-menu menu-container" >

                            <li>
                                <a class=" menu-element dropdown-item" href="homeStockKeeper.jsp">
                                    <span class="iconify" data-icon="akar-icons:home"></span>
                                    <span >Home</span>
                                </a>
                            </li>
                            <h1 class="dropdown-header" id="dropdown-header">Product</h1>
                            <li>
                                <a class=" menu-element dropdown-item " href="MainController?action=SearchProductManager&name=">
                                    <span class="iconify" data-icon="gridicons:create"></span>
                                    <span >List Product</span>
                                </a>
                            </li>
                            <h1 class="dropdown-header" id="dropdown-header">Inventory</h1>
                            <li>
                                <a class=" menu-element dropdown-item " href="MainController?action=ViewProductSK">
                                    <span class="iconify" data-icon="gridicons:create"></span>
                                    <span >Check-in</span>
                                </a>
                            </li>
                            <li>
                                <a class=" menu-element dropdown-item " href="MainController?action=SearchIssueStockeeper&searchIssue=&searchCustomer=">
                                    <span class="iconify" data-icon="gridicons:create"></span>
                                    <span >Check-out</span>
                                </a>
                            </li>
                            <li>
                                <a class=" menu-element dropdown-item " href="MainController?action=ViewBinSK">
                                    <span class="iconify" data-icon="gridicons:create"></span>
                                    <span >Position</span>
                                </a>
                            </li>


                            <%
                                UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
                            %>





                        </ul>
                    </div>
                </div>
                <div class="right-item header-item" style="right: 0">
                    <label for="gropdown"><%= loginUser.getName()%></label>
                    <div class="dropdown inline-block ">
                        <i  data-icon="gridicons:dropdown" class="iconify dropdown-toggle" href="#"  id="dropdownMenuLink" data-bs-toggle="dropdown" aria-expanded="false"></i>
                        <ul class="dropdown-menu menu-container" aria-labelledby="dropdownMenuButton1">
                            <li>
                                <a class=" menu-element dropdown-item" href="MainController?action=Logout">
                                    <span class="iconify" data-icon="gridicons:sign-out"></span>
                                    <span >Sign out</span>
                                </a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
