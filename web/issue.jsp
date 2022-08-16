<%-- 
    Document   : issueVirtual
    Created on : Jun 17, 2022, 10:20:13 PM
    Author     : 84348
--%>

<%@page import="user.UserDTO"%>
<%@page import="issueAccountant.UserOrderDetail"%>
<%@page import="issueAccountant.UserIssueS"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css">
        <script src="js/backNoWork.js" type="text/javascript" ></script>
        <link rel="stylesheet" href="css/styles.css">
        <script src="js/mycode.js"></script>
        <title>Issue Page</title>
    </head>
    <body>
        <%
            UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
            String p = null;
            if ("MN".equals(loginUser.getRole())) {
                p = "managerHeader.jsp";
            } else if ("AC".equals(loginUser.getRole())) {
                p = "accountantHeader.jsp";
            }
        %>
        <jsp:include page="<%=p%>"/>



        <div class="box-container filter-container"> 
            <form action="MainController" class="center-horizon-flex" >
                <input type="radio" name="status" value="Implementing" id="option1" checked onclick="getSelectItemThat(this.id)">Implementing
                <input type="radio" name="status" value="Implemented" id="option2" checked onclick="getSelectItemThat(this.id)">Implemented
                <input type="submit" name="action" value="Send Issue">
            </form>

            <form action="">
                <i class="fa-solid fa-filter" onclick="ShowDateReceipt()"></i>
            </form>
            <div class="show_date_receipt box-container hide-show-fiter" id="showDateReceipt" style="display: none;z-index: 1">
                <form action="MainController" name="SIdSearch" onsubmit="SIdSearchForm();">
                    Search<input type="date" name="search"/>
                    To<input type="date" name="searchR" />
                    <input type="hidden" name="action" value="SearchIssueDate"/>
                    <input type="submit" value="Search"/>
                </form>
            </div>

            <div>
                <i class="fa-solid fa-sort " onclick="ShowSort()"></i>
            </div>
            <div id="sortLabel" class="show_date_receipt box-container hide-show-fiter" style="display: none;z-index: 1">
                <form action="MainController" name="SIACOSearch" onsubmit="SIACOSearchForm();">
                    <label>Sort by: </label>
                    <select name="search">
                        <option value="1">Date old</option>
                        <option value="2">Date new</option>
                    </select>
                    <input type="hidden" name="action" value="SortIssue">
                    <input type="submit" name="Sort">
                </form>
            </div>

            <form action="MainController" name="OSearch" onsubmit="OSearchForm();">
                <input type="text" name="searchIssue" placeholder="Enter OrderID">
                <input type="hidden" name="searchCustomer" placeholder="Enter Customer Name">
                <input type="hidden" name="action" value="SearchIssue">
                <input type="submit" value="Search"/>
            </form>
        </div>
        <%
            List<UserIssueS> issue = (List<UserIssueS>) session.getAttribute("LIST_ISSUE");
            if (issue != null) {
                if (issue.size() > 0) {
        %>
        <div class="outer-wrapper">
            <div class="table-wrapper">
                <table border="1">
                    <thead>
                    <th>issueID</th>                   
                    <th>orderID</th>
                    <th>Date</th>
                    <th>Status</th>
                    <th>Action</th>
                    </thead>
                    <tbody>
                        <%
                            for (UserIssueS tm : issue) {
                        %>
                    <form action="MainController" name="SIDSearch" onsubmit="SIDSearchForm();">
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
                            <%
                                String k;
                                if (tm.getNote() == null) {
                                    k = "";
                                } else {
                                    k = tm.getNote();
                                }
                            %>
                            <td>
                                <%= k%>
                            </td>
                            <td>
                                <input type="hidden" name="action" value="ShowDetailOrder"/>
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
            List<UserOrderDetail> issueDetail = (List<UserOrderDetail>) request.getAttribute("LIST_ORDER_DETAIL");
            if (issueDetail != null) {
                if (issueDetail.size() > 0) {
        %>
        <div id="orderDetail" class="hide-show-fiter box-container">
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
                <input type="button" value="Close" onclick="ShowDetailOrder()">
            </form> 

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


