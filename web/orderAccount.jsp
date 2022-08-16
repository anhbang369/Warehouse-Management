<%-- 
    Document   : orderAccount
    Created on : Aug 1, 2022, 9:47:07 PM
    Author     : 84348
--%>

<%@page import="orderSeller.UserOrderDetailView"%>
<%@page import="issueAccountant.UserOrder"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css">
        <link rel="stylesheet" href="css/styles.css">
        <script src="js/mycode.js"></script>
        <title>Order Full Page</title>
    </head>
    <body>
        <jsp:include page="accountantHeader.jsp" />
        <div class="box-container filter-container">
            <form action="">
                <i class="fa-solid fa-filter" onclick="ShowDateReceipt()"></i>
            </form>
            <div  class="show_date_receipt hide-show-fiter box-container" id="showDateReceipt" style="display: none; z-index: 1;top: 25%" >
                <form action="MainController" name="SDACDDSearch" onsubmit="SDACDDSearchForm();">
                    From <input type="date" name="search"/>
                    To <input type="date" name="searchR" />
                    <input type="hidden" name="action" value="SearchOrderAccountDate"/>
                    <input type="submit" name="Search"/>
                </form>
            </div>

            <div id="sortLabel">
                <form action="MainController">
                    <label>Sort by: </label>
                    <select name="search">
                        <option value="1">Old date</option>
                        <option value="2">New date</option>
                    </select>
                    <input type="submit" name="action" value="Sort Date"/>
                </form>
            </div>

            <form action="MainController" class="center-horizon-flex" >
                <input type="checkbox" name="status" value="Not Confirm" id="1" onclick="getSelectItemThat(this.id)">Not Confirm
                <input type="checkbox" name="status" value="Confirm" id="2" checked onclick="getSelectItemThat(this.id)">Confirm
                <input type="checkbox" name="status" value="Cancel" id="3" onclick="getSelectItemThat(this.id)">Canceled
                <input type="submit" name="action" value="Send Order">
            </form>

        </div>
        <%
            List<UserOrder> order = (List<UserOrder>) session.getAttribute("LIST_ORDER_ACCOUNT");
            if (order != null) {
                if (order.size() > 0) {
        %>
        <div class="outer-wrapper">
            <div class="table-wrapper">
                <table border="1">
                    <thead>
                    <th>Order ID</th>
                    <th>Custom</th>
                    <th>Delivery Date</th>
                    <th>Note</th>
                    <th>Status</th>
                    <th>Action</th>
                    </thead>
                    <tbody >
                        <%
                            for (UserOrder rc : order) {
                        %>
                    <form action="MainController">
                        <tr>
                            <td>
                                <%= rc.getOrderID()%>
                            </td>
                            <td>
                                <%= rc.getCustomerName()%>
                            </td>
                            <td>
                                <%= rc.getDeliveryDate()%>
                            </td>
                            <%
                                String k;
                                if (rc.getNote() == null) {
                                    k = "";
                                } else {
                                    k = rc.getNote();
                                }
                            %>
                            <td>
                                <%= k%>
                            </td>
                            <td>
                                <%= rc.getStatus()%>
                            </td>

                            <td>
                                <a href="MainController?action=ViewOrderDetailAccount&orderId=<%= rc.getOrderID()%>">View</a>
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
        <%
                }
            }
        %>

        <%
            List<UserOrderDetailView> orderDetail = (List<UserOrderDetailView>) request.getAttribute("LIST_ORDER_DETAIL_ACCOUNT");
            if (orderDetail != null) {
                if (orderDetail.size() > 0) {
        %>
        <div id="orders" class="banner-container hide-show-fiter" style="display: block;">
            <table border="1">
                <thead>
                    <tr>
                        <th>Order Detail</th>
                        <th>Product ID</th>
                        <th>Quantity</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        for (UserOrderDetailView rc : orderDetail) {
                    %>
                    <tr>
                        <td>
                            <%= rc.getOrderDetailID()%>
                        </td>
                        <td>
                            <%= rc.getProductId()%>
                        </td>
                        <td>
                            <%= rc.getQuantity()%>
                        </td>
                    </tr>
                    <%
                        }
                    %>
                </tbody>

            </table>
            <input type="button" value="Close" onclick="ShowOrders()">
        </div>
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
