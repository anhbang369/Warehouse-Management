<%-- 
    Document   : issue
    Created on : Jun 17, 2022, 8:57:17 PM
    Author     : 84348
--%>

<%@page import="virtual.ListNotify"%>
<%@page import="user.UserNotify"%>
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
        <title>Order Page</title>
    </head>
    <body>
        <jsp:include page="accountantHeader.jsp" />
        <div class="filter-container box-container">
            <form action="">
                <i class="fa-solid fa-filter" onclick="ShowDateReceipt()"></i>
            </form>
            <div class="show_date_receipt hide-show-fiter box-container" id="showDateReceipt" style="display: none;z-index: 1;top: 30%">
                <form action="MainController" name="OdSearch" onsubmit="OdSearchForm();">
                    From <input type="date" name="search"/>
                    To <input type="date" name="searchM" />
                    <input type="hidden" name="action" value="SearchOrderDate"/>
                    <input type="submit" value="Search"/>
                </form>
            </div>

            <div>
                <i class="fa-solid fa-sort" onclick="ShowSort()"></i>

            </div>

            <div id="sortLabel" class="show_date_receipt hide-show-fiter box-container" style="display: none;z-index: 1;top: 30%">
                <form action="MainController" name="sortOrder" onsubmit="showSortOrder()">
                    <label>Sort by: </label>
                    <select name="search">
                        <option value="1">Old date</option>
                        <option value="2">New date</option>
                    </select>
                    <input type="hidden" name="action" value="SortOrder">
                    <input type="submit" value="Sort"/>
                </form>
            </div>

            <form action="MainController" name="OOSearch" onsubmit="OOSearchForm();">
                <input type="number" name="OrderI" placeholder="OrderID"/>
                <input type="hidden" name="CustomerNam" placeholder="CustomerName"/>
                <input type="hidden" name="Addres" placeholder="Address"/>
                <input type="hidden" name="PhoneNumbe" placeholder="PhoneNumber"/>
                <input type="hidden" name="action" value="SeacrhOrder"/>
                <input type="submit" value="Search"/>
            </form>

        </div>
        



        <%
            List<UserOrder> order = (List<UserOrder>) session.getAttribute("LIST_ORDER");
            if (order != null) {
                if (order.size() > 0) {
        %>
        <div class="outer-wrapper">
            <div class="table-wrapper">
                <table border="1" class="table">
                    <thead>
                    <th>Order ID</th>
                    <th>Status</th>
                    <th>Delivery Date</th>
                    <th>Seller ID</th>
                    <th>Note</th>
                    <th>Action</th>
                    </thead>
                    <tbody >
                        <%
                            for (UserOrder rc : order) {
                        %>
                    <form action="MainController" name="OISearch" onsubmit="OISearchForm();">
                        <tr>
                            <td>
                                <%= rc.getOrderID()%>
                            </td>
                            <td>
                                <%= rc.getStatus()%>
                            </td>
                            <td>
                                <%= rc.getDeliveryDate()%>
                            </td>
                            <td>
                                <%= rc.getSellerID()%>
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
                                <input type="hidden" name="action" value="InsertIssue" />
                                <input type="submit" value="Add"/>
                            </td>
                        <input type="hidden" name="orderID" value="<%= rc.getOrderID()%>" readonly=""/>
                        <input type="hidden" name="customerName" value="<%= rc.getCustomerName()%>" readonly=""/>
                        <input type="hidden" name="address" value="<%= rc.getAddress()%>" readonly=""/>
                        <input type="hidden" name="phoneNumber" value="<%= rc.getPhoneNumber()%>" readonly=""/>
                        <input type="hidden" name="status" value="<%= rc.getStatus()%>" readonly=""/>
                        <input type="hidden" name="note" value="<%= rc.getNote()%>" readonly=""/>
                        <input type="hidden" name="deliveryDate" value="<%= rc.getDeliveryDate()%>" readonly=""/>
                        <input type="hidden" name="sellerID" value="<%= rc.getSellerID()%>" />
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
    //        String erro = (String) request.getAttribute("ERROR_ADD_ISSUE");
    //        if (erro == null) {
    //            erro = "";
    //        }
        %>
        <!--    <h2></h2>-->

        <%        String error = (String) request.getAttribute("ERROR");
            if (error == null) {
                error = "";
            }
        %>
        <h2 class="error"><%=error%></h2>



        <%
            String errorr = (String) request.getAttribute("ERROR_ADD_ISSUE");
            if (errorr == null) {
                errorr = "";
            }
        %>
        <h2 class="error"><%=errorr%></h2>
        <div class="filter-container w-90">
        <a class="a-button " href="orderAccount.jsp">Order</a>
        </div>
        <script src="js/mycode.js"></script>
        <script src="https://code.iconify.design/2/2.2.1/iconify.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.5/dist/umd/popper.min.js" integrity="sha384-Xe+8cL9oJa6tN/veChSP7q+mnSPaj5Bcu9mPX5F5xIGE0DVittaqT5lorf0EI7Vk" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.min.js" integrity="sha384-kjU+l4N0Yf4ZOJErLsIcvOU2qSb74wXpOhqTvwVx3OElZRweTnQ6d31fXEoRD1Jy" crossorigin="anonymous"></script>

    </body>
</html>
