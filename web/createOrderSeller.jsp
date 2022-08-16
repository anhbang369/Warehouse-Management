<%-- 
    Document   : createOrderSeller
    Created on : Jul 21, 2022, 2:27:58 AM
    Author     : 84348
--%>

<%@page import="orderSeller.UserProductError"%>
<%@page import="virtualSeller.ListErrorProduct"%>
<%@page import="orderSeller.UserOrderDetailSeller"%>
<%@page import="virtualSeller.ListOrder"%>
<%@page import="user.UserProduct"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css">
        <link rel="stylesheet" href="css/styles.css">
        <script src="js/mycode.js"></script>
        <title>Create Order Detail Page</title>
    </head>
    <body>
        <jsp:include page="sellerHeader.jsp" />

        <div class="container" style="max-width: 90%">
            <div class="row">
                <div class="col-5">
                    <div class="d-flex justify-content-center box-container " style="">
                        <form action="MainController" name="SPODSeller" onsubmit="SPODSellerForm();" style="">
                            <div class="container">
                                <div class="row">
                                    <input class="col" type="text" name="ProductID" placeholder="ProductID">
                                    <input class="col" type="text" name="Name" placeholder="Name" >
                                    <input  type="hidden" name="action" value="SearchProductOrderSeller"/>
                                </div>
                            </div>
                            <input type="submit" value="Search"/>
                        </form>
                    </div>

                    <%
                        List<UserProduct> product = (List<UserProduct>) request.getAttribute("LIST_PRODUCT_SELLER");
                        if (product != null) {
                            if (product.size() > 0) {
                    %>
                    <div class="outer-wrapper">
                        <div class="table-wrapper">
                            <form action="MainController">
                                <table border="1" >
                                    <thead>
                                    <th>No</th>
                                    <th>Product ID</th>
                                    <th>Buy</th>
                                    <th>Quantity</th>
                                    <th>Action</th>
                                    </thead>

                                    <tbody >
                                        <%
                                            for (UserProduct rc : product) {
                                        %>
                                    <form action="MainController" name="AVPSeller" onsubmit="AVPSellerForm();">
                                        <tr>
                                            <td>
                                                <%= rc.getProductID()%>
                                                <input type="hidden" name="productId" value="<%= rc.getProductID()%>">

                                            </td>
                                            <td>
                                                <%= rc.getName()%>
                                                <input type="hidden" name="name" value="<%= rc.getName()%>">
                                            </td>
                                            <td>
                                                <input style="width: 100px" type="number" name="quantity" required="" min="1" max="<%= rc.getQuantity()%>">
                                            </td>
                                            <td>
                                                <%= rc.getQuantity()%>
                                            </td>
                                            <td>
                                                <input type="hidden" name="action" value="AddVirtualOrderSeller">
                                                <input type="submit" value="Add"/>
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
                        </div>
                    </div>
                </div>
                <%
                    ListOrder rd = (ListOrder) session.getAttribute("VIRTUAL_ORDER");
                    if (rd != null) {
                        if (rd.getListOrder().size() > 0) {
                %>
                <div class="col-7">
                    <div class="outer-wrapper">
                        <div class="table-wrapper">
                            <form action="MainController">
                                <table border="1" class="table">
                                    <thead>
                                    <th>No</th>
                                    <th>Product ID</th>
                                    <th>Name</th>
                                    <th>Quantity</th>
                                    <th>Action</th>
                                    </thead>

                                    <tbody >
                                        <%
                                            int count = 1;
                                            for (UserOrderDetailSeller tm : rd.getListOrder().values()) {
                                        %>
                                    <form action="MainController" name="UVORSeller" onsubmit="UVORSellerForm();">
                                        <tr>
                                            <td>
                                                <%= count++%>
                                            </td>
                                            <td>
                                                <%= tm.getProductId()%>
                                                <input type="hidden" name="productId" value="<%= tm.getProductId()%>">
                                            </td>
                                            <td>
                                                <%= tm.getName()%>
                                                <input type="hidden" name="name" value="<%= tm.getName()%>">
                                            </td>
                                            <td>
                                                <input type="number" name="quantity" value="<%= tm.getQuantity()%>" min="1">
                                            </td>
                                            <td>
                                                <div class="d-flex ">
                                                    <input type="hidden" name="action" value="UpdateOrderVirtualSeller">
                                                    <input type="submit" value="Update"/>
                                                    <a class="a-button " href="MainController?action=DeleteOrderVirtualSeller&productId=<%= tm.getProductId()%>">Delete</a>
                                                </div>
                                            </td>
                                        </tr>
                                    </form>
                                    <%
                                        }
                                    %>
                                    </tbody>
                                </table>
                        </div>
                    </div>
                    <form action="MainController" name="SPOSeller" onsubmit="SPOSellerForm();" class="container box-container">
                        <h2 >Customer Information</h2>
                        <div class="row">
                            <input class="col" type="text" name="custom" placeholder="Customer Name">
                        </div>
                        <div class="row">
                            <input class="col" type="text" name="address" placeholder="Customer Address">
                        </div>
                        <div class="row">
                            <input class="col" type="number" name="phone" placeholder="Phone Number">
                            <input class="col" type="date" name="date" placeholder="Delivery Date">
                        </div>
                        <div class="row">
                            <input class="col" type="text" name="note" placeholder="Note">
                        </div>
                        <input type="hidden" name="action" value="InsertOrderDetailSeller">
                        <div class=" d-flex justify-content-center">
                            <input type="submit" value="Finish"/>
                        </div>
                    </form>

                    <%
                            }
                        }
                    %>

                    <%
                        String error = (String) request.getAttribute("ERROR_FINISH");
                        if (error == null) {
                            error = "";
                        }
                    %>
                    <h2 class="error"><%=error%></h2>


                    <%
                        ListErrorProduct rc = (ListErrorProduct) session.getAttribute("VIRTUAL_ERROR_PRODUCT");
                        if (rc != null) {
                            if (rc.getListErrorProduct().size() > 0) {
                    %>

                    <%
                        for (UserProductError tmm : rc.getListErrorProduct().values()) {
                    %>
                    <%= tmm.getProductID()%> Currently not enough
                    <%
                        }
                    %>

                    <%
                            }
                        }
                    %>

                </div>
            </div>
        </div>
        <script src="js/mycode.js"></script>
        <script src="https://code.iconify.design/2/2.2.1/iconify.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.5/dist/umd/popper.min.js" integrity="sha384-Xe+8cL9oJa6tN/veChSP7q+mnSPaj5Bcu9mPX5F5xIGE0DVittaqT5lorf0EI7Vk" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.min.js" integrity="sha384-kjU+l4N0Yf4ZOJErLsIcvOU2qSb74wXpOhqTvwVx3OElZRweTnQ6d31fXEoRD1Jy" crossorigin="anonymous"></script>


    </body>
</html>
