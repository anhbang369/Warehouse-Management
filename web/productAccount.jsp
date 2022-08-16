<%-- 
    Document   : productAccount
    Created on : Jul 20, 2022, 11:14:54 AM
    Author     : 84348
--%>

<%@page import="user.UserProduct"%>
<%@page import="java.util.List"%>
<%@page import="user.UserProductNotFull"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <title>Product Page</title>
    </head>
    <body>
        <jsp:include page="accountantHeader.jsp" />
        <form action="MainController" class="box-container filter-container" name="productSearchAC" onsubmit="showProductSearchAC()"> 
            <input type="text" name="name" placeholder="Name">.
            <input type="hidden" name="action" value="SearchProductAccount">
            <input type="submit" value="Search">
        </form>
        <%
            List<UserProductNotFull> product = (List<UserProductNotFull>) session.getAttribute("LIST_PRODUCT_NOT_FULL");
            if (product != null) {
                if (product.size() > 0) {
        %>
        <form action="MainController">
            <table border="1">
                <thead>

                </thead>
                <tbody class="table-container tbody block ">
                    <tr class="table-header">
                        <th>No</th>
                        <th>Product ID</th>
                        <th>Name</th>
                        <th>Quantity</th>
                        <th>Action</th>
                    </tr>
                    <%
                        int count = 1;
                        for (UserProductNotFull rc : product) {

                    %>
                <form action="MainController">
                    <tr>
                        <td>
                            <%= count++%>
                        </td>
                        <td>
                            <input type="hidden" name="productID" value="<%= rc.getProductID()%>">
                            <%= rc.getProductID()%>
                        </td>
                        <td>
                            <input type="hidden" name="name" value="<%= rc.getName()%>">
                            <%= rc.getName()%>
                        </td>
                        <td>
                            <input type="hidden" name="quantity" value="<%= rc.getQuantity()%>">
                            <%= rc.getQuantity()%>
                        </td>
                        <td>
                            <input type="submit" name="action" value="View">
                        </td>

                    </tr>

                </form>
                <%
                    }
                %>
                </tbody>
            </table>
            <%
                    }
                }
            %>
        </form>


        <%
            List<UserProduct> productF = (List<UserProduct>) session.getAttribute("LIST_PRODUCT_FULL");
            if (productF != null) {
                if (productF.size() > 0) {
        %>

        <%
            int count = 1;
            for (UserProduct rc : productF) {

        %>
        <div class="box-container hide-show-fiter"  id='productDetail' style="width: 50%">
            <form action="MainController">
                <div class="container " style="width: 60%">
                    <div class="row d-flex justify-content-between" >
                        <div class="col align-items-center d-flex justify-content-between" >
                            Product ID: <div> <%= rc.getProductID()%></div>
                            <input type="hidden" name="productID" value="<%= rc.getProductID()%>">
                        </div>
                        <div class="col align-items-center d-flex justify-content-between">
                            Manufactoring date: <div > <%= rc.getManufacturingDate()%> </div>
                        </div>

                    </div>
                    <div class="row">
                        <div class="col align-items-center d-flex  justify-content-between">
                            Product Name: <div><%= rc.getName()%></div>
                        </div>
                        <div class="col">

                        </div>
                    </div>

                    <div class="row">
                        <div class="col align-items-center d-flex justify-content-between">
                            Brand: <div><%= rc.getBrand()%></div>
                        </div>
                        <div class="col align-items-center d-flex justify-content-between">
                            Model: <div> <%= rc.getModel()%></div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col align-items-center d-flex justify-content-between">
                            Type:<div><%= rc.getType()%></div>
                        </div>
                        <div class="col align-items-center d-flex justify-content-between d-flex justify-content-between">
                            Made In:<div> <%= rc.getMadeIn()%></div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col align-items-center d-flex justify-content-between">
                            Voice Remode:  <div><%= rc.getVoiceRemote()%></div>

                        </div>
                        <div class="col align-items-center d-flex justify-content-between">
                            Bluetooth: <div> <%= rc.getBluetooth()%></div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col align-items-center d-flex justify-content-between">
                            Screen size: <div><%= rc.getScreenSize()%></div>
                        </div>

                        <div class="col align-items-center d-flex justify-content-between">
                            Height:<div><%= rc.getHeight()%></div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col align-items-center d-flex justify-content-between">
                            Width: <div><%= rc.getWidth()%></div>
                        </div>
                        <div class="col align-items-center d-flex justify-content-between">

                            Depth: <div><%= rc.getDepth()%></div>
                        </div>
                    </div>

                    <div class="filter-container">
                        <input type="button" class="a-button" value="Close" onclick="ShowProductDetail()">
                    </div>
                </div>
            </form>
        </div>

        <%
            }
        %>

        <%
                }
            }
        %>
        <script src="js/mycode.js"></script>
        <script src="https://code.iconify.design/2/2.2.1/iconify.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.5/dist/umd/popper.min.js" integrity="sha384-Xe+8cL9oJa6tN/veChSP7q+mnSPaj5Bcu9mPX5F5xIGE0DVittaqT5lorf0EI7Vk" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.min.js" integrity="sha384-kjU+l4N0Yf4ZOJErLsIcvOU2qSb74wXpOhqTvwVx3OElZRweTnQ6d31fXEoRD1Jy" crossorigin="anonymous"></script>

    </body>
</html>
