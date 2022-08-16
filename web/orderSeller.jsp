<%-- 
    Document   : orderSeller
    Created on : Jul 21, 2022, 2:27:40 AM
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
        <title>Order Page</title>
    </head>
    <body>
        <jsp:include page="sellerHeader.jsp" />
        <div class="box-container filter-container" style="justify-content: space-around">
            <form action="MainController" name="ODSSearch" onsubmit="ODSSearchForm();">
                <input type="hidden" name="OrderI" placeholder="OrderID"/>
                <input type="hidden" name="action" value="SearchOrderSeller"/>
                <input type="hidden" value="Search"/>
            </form>


            <form action="MainController" class="center-horizon-flex" >
                <input type="radio" name="status" value="Not Confirm" id="1" checked onclick="getSelectItemThat(this.id)">Not Confirm
                <input type="radio" name="status" value="Confirm" id="2" checked onclick="getSelectItemThat(this.id)">Confirm
                <input type="radio" name="status" value="Cancel" id="3" onclick="getSelectItemThat(this.id)">Canceled
                <input type="submit" name="action" value="Send">
            </form>


            <div id="sortLabel">
                <form action="MainController" name="ODSSSearch" onsubmit="ODSSSearchForm();">
                    <label>Sort by: </label>
                    <select name="search">
                        <option value="1">Old date</option>
                        <option value="2">New date</option>
                    </select>
                    <input type="hidden" name="action" value="SortOrderSeller">
                    <input type="submit" value="Sort"/>
                </form>
            </div>
        </div>
        <%
            List<UserOrder> order = (List<UserOrder>) session.getAttribute("LIST_ORDER_SELLER");
            if (order != null) {
                if (order.size() > 0) {
        %>
        <div class="outer-wrapper">
            <div class="table-wrapper">
                <table border="1" class="table">
                    <thead>
                    <th>Order ID</th>
                    <th>Custom</th>
                    <th>Delivery Date</th>
                    <th>Note</th>
                    <th>Status</th>
                    <th>Action</th>
                    <th>View</th>
                    </thead>
                    <tbody>
                        <%
                            for (UserOrder rc : order) {
                        %>
                    <form action="MainController" name="ODVSSearch" onsubmit="ODVSSearchForm();">
                        <tr>
                            <td>
                                <%= rc.getOrderID()%>
                                <input type="hidden" name="orderId" value="<%= rc.getOrderID()%>" />
                            </td>
                            <td>
                                <input type="text" name="custom" value="<%= rc.getCustomerName()%>" />
                            </td>
                            <td>
                                <input type="date" name="deliveryDate" required pattern="\d{4}-\d{2}-\d{2}" value="<%= rc.getDeliveryDate()%>"/>
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
                                <input type="text" name="note" value="<%= k%>" />
                            </td>
                            <td>
                                <select name="status">
                                    <option selected="true"  ><%= rc.getStatus()%></option>
                                    <%
                                        if (rc.getStatus().equalsIgnoreCase("Not Confirm")) {
                                    %>
                                    <option value="Cancel">Cancel</option>
                                    <%
                                        }
                                    %>

                                </select>
                            </td>
                            <td>
                                <%
                                    if (rc.getStatus().equalsIgnoreCase("Not Confirm")) {
                                %>

                                <input type="submit" name="action" value="Update Order" readonly=""/>

                                <%
                                    }
                                %>
                            </td>
                            <td>
                                <input type="hidden" name="action" value="ViewOrderDetailSeller" readonly=""/>
                                <input type="submit" value="View"/>
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


                <%
                    List<UserOrderDetailView> orderDetail = (List<UserOrderDetailView>) request.getAttribute("LIST_ORDER_DETAIL_VIEW");
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

                <%
                    String error = (String) request.getAttribute("ERROR_FINISH");
                    if (error == null) {
                        error = "";
                    }
                %>
                <h2 class="error"><%=error%></h2>
                <script src="js/mycode.js"></script>
                <script src="https://code.iconify.design/2/2.2.1/iconify.min.js"></script>
                <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.5/dist/umd/popper.min.js" integrity="sha384-Xe+8cL9oJa6tN/veChSP7q+mnSPaj5Bcu9mPX5F5xIGE0DVittaqT5lorf0EI7Vk" crossorigin="anonymous"></script>
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.min.js" integrity="sha384-kjU+l4N0Yf4ZOJErLsIcvOU2qSb74wXpOhqTvwVx3OElZRweTnQ6d31fXEoRD1Jy" crossorigin="anonymous"></script>

                </body>
                </html>
