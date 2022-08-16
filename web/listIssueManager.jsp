<%-- 
    Document   : listIssueManager
    Created on : Jul 22, 2022, 9:56:02 AM
    Author     : 84348
--%>

<%@page import="issueAccountant.UserIssueS"%>
<%@page import="issueAccountant.UserOrderDetail"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Issue Page</title>
    </head>
    <body>
        <jsp:include page="managerHeader.jsp" />
        <div class="filter-container box-container">
            <div>
                <i class="fa-solid fa-sort" onclick="ShowSort()"></i>
            </div>
            <div id="sortLabel" class="show_date_receipt hide-show-fiter box-container" style="display: none">
                <form action="MainController" name="sortIssueManager" onsubmit="showSortIssueManager()">
                    <label>Sort by: </label>
                    <select name="search">
                        <option value="1">Date old</option>
                        <option value="2">Date new</option>
                    </select>
                    <input type="hidden" name="action" value="SortIssueManager">
                    <input type="submit" value="Sort"/>
                </form>
            </div>
            <form action="MainController"  onsubmit="showSearchIssueManager()">
                <input type="number" name="OrderI" placeholder="OrderID"/>
                <input type="hidden" name="action" value="SearchIssueManager"/>
                <input type="submit" value="Search"/>
            </form>
        </div>
        <%
            List<UserIssueS> issue = (List<UserIssueS>) session.getAttribute("LIST_ISSUE_MANAGER");
            if (issue != null) {
                if (issue.size() > 0) {
        %>
        <div class="outer-wrapper">
            <div class="table-wrapper">
                <table border="1" class="table">
                    <thead>
                    <th>Issue ID</th>                   
                    <th>Order ID</th>
                    <th>Date</th>
                    <th>Status</th>
                    <th>Action</th>
                    </thead>
                    <tbody>
                        <%
                            for (UserIssueS tm : issue) {
                        %>
                    <form action="MainController" name="viewIssueDetailManager" onsubmit="showViewIssueDetailManager()">
                        <tr>
                            <td>
                                <%= tm.getIssueID()%>
                            </td>
                            <td>
                                <%= tm.getOrderID()%>
                            </td>
                            <td>
                                <%= tm.getDateP()%>
                            </td>
                            <td>
                                <%= tm.getNote()%>
                            </td>
                            <td>
                                <input type="hidden" name="action" value="ViewIssueDetailManager"/>
                                <input type="submit" value="View"/>
                            </td>
                        <input type="hidden" name="issueID" value="<%= tm.getIssueID()%>" readonly=""/>
                        <input type="hidden" name="note" value="<%= tm.getNote()%>" readonly=""/>
                        <input type="hidden" name="accountantID" value="<%= tm.getAccountantID()%>" readonly=""/>
                        <input type="hidden" name="sellerID" value="<%= tm.getSellerID()%>" readonly=""/>
                        <input type="hidden" name="orderID" value="<%= tm.getOrderID()%>" readonly=""/>
                        <input type="hidden" name="DateP" value="<%= tm.getDateP()%>" readonly=""/>
                        <input type="hidden" name="action" value="ShowDetailOrder"/>
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
            List<UserOrderDetail> issueDetail = (List<UserOrderDetail>) request.getAttribute("LIST_ORDER_DETAIL_MANAGER");
            if (issueDetail != null) {
                if (issueDetail.size() > 0) {
        %>
        <div id="orderDetail" class=" box-container hide-show-fiter">
            <form action="MainController">

                <table  border="1" >
                    <thead>
                        <tr>
                            <th>orderDetailID</th>
                            <th>quantity</th>
                            <th>productID</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            for (UserOrderDetail tm : issueDetail) {
                        %>
                        <tr>
                            <td>
                                <%= tm.getOrderDetailID()%>
                            </td>
                            <td>
                                <%= tm.getQuantity()%>
                            </td>
                            <td>
                                <%= tm.getProductID()%>
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
            <input type="button" value="Close" onclick="ShowDetailOrder()">
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
