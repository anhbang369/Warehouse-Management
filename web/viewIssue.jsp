<%-- 
    Document   : viewIssue
    Created on : Jul 21, 2022, 11:50:24 PM
    Author     : GMT
--%>


<%@page import="issueAccountant.UserOrderDetail"%>
<%@page import="issueAccountant.UserIssueS"%>
<%@page import="inventoryStockeepr.IssueDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>List Issues Page</title>
    </head>
    <body>
        <jsp:include page="stockeepHeader.jsp" />


        <div class="box-container filter-container">
            <div>
                <i class="fa-solid fa-sort" onclick="ShowSort()"></i>
            </div >
            <form id="sortLabel" name="sortIssueStockeeper" onsubmit="showSortIssueStockeeper()" class="show_date_receipt hide-show-fiter box-container" action="MainController" style="display: none">
                <label>Sort by: </label>
                <select name="search">
                    <option value="1">Date old</option>
                    <option value="2">Date new</option>
                </select>
                <input type="hidden" name="action" value="SortIssueStockeeper">
                <input type="submit" value="Sort"/>
            </form>
            <form action="MainController" name="searchIssueStockeeper" onsubmit="showSearchIssueStockeeper()">
                <input type="number" name="searchIssue" placeholder="OrderID"/>
                <input type="hidden" name="searchCustomer" placeholder="CustomerName"/>
                <input type="hidden" name="Addres" placeholder="Address"/>
                <input type="hidden" name="PhoneNumbe" placeholder="PhoneNumber"/>
                <input type="hidden" name="action" value="SearchIssueStockeeper"/>
                <input type="submit" value="Search"/>
            </form>

        </div>
        <%
            List<UserIssueS> issue = (List<UserIssueS>) session.getAttribute("LIST_ISSUE_STOCKEEPR");
            if (issue != null) {
                if (issue.size() > 0) {
        %>
        <div class="outer-wrapper">
            <div class="table-wrapper">
                <table border="1">
                    <thead>
                    <th>Issue ID</th>                   
                    <th>Order ID</th>
                    <th>Date</th>
                    <th>Status</th>
                    <th>Action</th>
                    </thead>
                    <tbody >
                        <%
                            for (UserIssueS tm : issue) {
                        %>
                    <form action="MainController">
                        <tr>
                            <td>
                                <%= tm.getIssueID()%>
                                <input type="hidden" name="issueID" value="<%= tm.getIssueID()%>" readonly=""/>
                            </td>
                            <td>
                                <%= tm.getOrderID()%>
                            </td>
                            <td>
                                <%
                                    if (tm.getDateP() != null) {
                                %>
                                <%= tm.getDateP()%>
                                <%
                                    }
                                %>

                            </td>
                            <td>
                                <%
                                    if (tm.getNote() != null) {
                                %>
                                <%= tm.getNote()%>
                                <%
                                    }
                                %>
                            </td>
                            <td>
                                <!--                        <input type="submit" name="action" value="ViewIssueDetailStockeepr"/>-->

                                <input type="hidden" name="action" value="ViewIssueDetailStockeepr"/>
                                <input type="submit" value="View Detail"/>
                                <%
                                    if (!tm.getNote().equals("Implemented")) {
                                %>
                                <a class="a-button" href="MainController?action=UpdateStatusStockeep&issueID=<%= tm.getIssueID()%>&orderID=<%= tm.getOrderID()%>">Update</a>
                                <%
                                    }
                                %>
                                

                            </td>

                        <input type="hidden" name="note" value="<%= tm.getNote()%>" readonly=""/>
                        <input type="hidden" name="accountantID" value="<%= tm.getAccountantID()%>" readonly=""/>
                        <input type="hidden" name="sellerID" value="<%= tm.getSellerID()%>" readonly=""/>
                        <input type="hidden" name="orderID" value="<%= tm.getOrderID()%>" readonly=""/>
                        <input type="hidden" name="DateP" value="<%= tm.getDateP()%>" readonly=""/>
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
            List<UserOrderDetail> issueDetail = (List<UserOrderDetail>) request.getAttribute("LIST_ORDER_DETAIL_SK");
            if (issueDetail != null) {
                if (issueDetail.size() > 0) {
        %>
        <div id="orderDetail" class="hide-show-fiter box-container">
            <form action="MainController">
                Order ID: <%= issueDetail.get(1).getOrderID()%>
                <table  border="1" >
                    <thead>
                        <tr>
                            <th>Product ID</th>
                            <th>Quantity</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            for (UserOrderDetail tm : issueDetail) {
                        %>
                        <tr>                    
                            <td>
                                <%= tm.getProductID()%>
                            </td>
                            <td>
                                <%= tm.getQuantity()%>
                            </td>

                    <input type="hidden" name="orderDetailID" value="<%= tm.getOrderDetailID()%>" readonly=""/>
                    <input type="hidden" name="quantity" value="<%= tm.getQuantity()%>" readonly=""/>
                    <input type="hidden" name="orderID" value="<%= tm.getOrderID()%>" readonly=""/>
                    <input type="hidden" name="productID" value="<%= tm.getProductID()%>" readonly=""/>
                    </tr>
                    <%
                        }
                    %>
                    </tbody>          
                </table>
            </form> 
            <input class="a-button" type="button" value="Close" onclick="ShowDetailOrder()">
        </div>
        <%
                }
            }
        %>

        <%
            String error = (String) request.getAttribute("ERROR");
            if (error == null) {
                error = "";
            }
        %>
        <h2><%=error%></h2>
        <script src="js/mycode.js"></script>
        <script src="https://code.iconify.design/2/2.2.1/iconify.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.5/dist/umd/popper.min.js" integrity="sha384-Xe+8cL9oJa6tN/veChSP7q+mnSPaj5Bcu9mPX5F5xIGE0DVittaqT5lorf0EI7Vk" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.min.js" integrity="sha384-kjU+l4N0Yf4ZOJErLsIcvOU2qSb74wXpOhqTvwVx3OElZRweTnQ6d31fXEoRD1Jy" crossorigin="anonymous"></script>

    </body>
</html>
