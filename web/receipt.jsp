<%-- 
    Document   : receipt
    Created on : Jun 16, 2022, 4:27:44 PM
    Author     : 84348
--%>

<%@page import="user.UserDTO"%>
<%@page import="virtual.ListNotify"%>
<%@page import="receiptAccountant.UserReceiptDetailS"%>
<%@page import="user.UserNotify"%>
<%@page import="user.UserProduct"%>
<%@page import="receiptAccountant.UserReceipt"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Receipt Page</title>
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
        <jsp:include page="<%= p%>"/>


        <div  class="filter-container box-container">
            <form action="">
                <i class="fa-solid fa-filter" onclick="ShowDateReceipt()"></i>
            </form>
            <div>
                <i class="fa-solid fa-sort" onclick="ShowSort()"></i>
            </div >
            <div  class="show_date_receipt hide-show-fiter box-container" id="showDateReceipt" style="display: none; z-index: 1;top: 25%" >
                <form action="MainController" name="RdSearch" onsubmit="RdSearchForm();">
                    From <input type="date" name="search"/>
                    To <input type="date" name="searchR" />
                    <input type="hidden" name="action" value="SearchReceiptDate"/>
                    <input type="submit" value="Search"/>
                </form>
            </div>

            <form action="MainController" class="center-horizon-flex" >
                <input type="checkbox" name="status" value="True" id="1" checked onclick="getSelectItemThat(this.id)">No Problem
                <input type="checkbox" name="status" value="False" id="3" onclick="getSelectItemThat(this.id)">Handled
                <input type="submit" name="action" value="Send Receipt">
            </form>


            

            <div  id="sortLabel" class="show_date_receipt hide-show-fiter box-container" style="display: none; z-index: 1;top: 25%">
                <form action="MainController" name="sortReceipt" onsubmit="showSortReceipt()">
                    <label>Sort by: </label>
                    <select name="search">
                        <option value="1">Date old</option>
                        <option value="2">Date new</option>
                    </select>
                    <input type="hidden" name="action" value="SortReceipt">
                    <input type="submit" value="Sort"/>
                </form>
            </div>
            <form action="MainController" name="RSearch" onsubmit="RSearchForm();">
                <input type="text" name="search" placeholder="By Receipt ID"/>
                <input type="hidden" name="action" value="SeacrhReceipt"/>
                <input type="submit" value="Search"/>
            </form>
        </div>
        <%
            List<UserReceipt> receipt = (List<UserReceipt>) session.getAttribute("LIST_RECEIPT");
            if (receipt != null) {
                if (receipt.size() > 0) {
        %>
        <div class="outer-wrapper">
            <div class="table-wrapper">
                <table border="1">
                    <thead>
                    <th>Receipt ID</th>
                    <th>Create date</th>
                    <th>Status</th>
                    <th>Total Quantity</th>
                    <th>Note</th>
                    <th>Action</th>
                    </thead>
                    <tbody>
                        <%
                            for (UserReceipt rc : receipt) {
                        %>
                    <form action="MainController" name="SRDSearch" onsubmit="SRDSearchForm();">
                        <tr>
                            <td>
                                <%= rc.getReceiptID()%>
                            </td>
                            <td>
                                <%= rc.getInputDate()%>
                            </td>
                            <%
                                String f;
                                if (rc.getStatus().equalsIgnoreCase("False")) {
                                    f = "Handled";
                                } else {
                                    f = "No Problem";
                                }
                            %>
                            <td>
                                <%= f%>
                            </td>
                            <td>
                                <%= rc.getTotalQuantity()%>
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
                                <input type="hidden" name="action" value="ShowDetailReceipt" readonly="" />
                                <input type="submit" value="Show"/>
                            </td>
                        </tr>
                        <input type="hidden" name="receiptID" value="<%= rc.getReceiptID()%>" readonly=""/>
                        <input type="hidden" name="productID" value="<%= rc.getInputDate()%>" readonly=""/>
                        <input type="hidden" name="productID" value="<%= rc.getStatus()%>" readonly=""/>
                        <input type="hidden" name="productID" value="<%= rc.getTotalQuantity()%>" readonly=""/>
                        <input type="hidden" name="productID" value="<%= rc.getNote()%>" readonly=""/>
                        <input type="hidden" name="productID" value="<%= rc.getAccountantID()%>" readonly=""/>
                        <input type="hidden" name="productID" value="<%= rc.getStockKeeperID()%>" readonly=""/>
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
                    List<UserReceiptDetailS> receiptDetail = (List<UserReceiptDetailS>) request.getAttribute("LIST_DETAIL_RECEIPT");
                    if (receiptDetail != null) {
                        if (receiptDetail.size() > 0) {
                %>
                <div id="orderDetail" class="hide-show-fiter">
                    <table  border="1" >
                        <thead>
                            <tr>
                                <th>receiptDetailID</th>
                                <th>quantityInBill</th>
                                <th>quantityInShipping</th>
                                <th>productID</th>
                                <th>solution</th>
                            </tr>
                        </thead>
                        <tbody>

                            <%
                                for (UserReceiptDetailS tm : receiptDetail) {
                            %>
                            <tr>
                                <td>
                                    <%= tm.getReceiptDetailID()%>
                                </td>
                                <td>
                                    <%= tm.getQuantityInBill()%>
                                </td>
                                <td>
                                    <%= tm.getQuantityInShipping()%>
                                </td>
                                <td>
                                    <%= tm.getProductID()%>
                                </td>
                                <%
                                    String h;
                                    if (tm.getSolution() == null) {
                                        h = "";
                                    } else {
                                        h = tm.getSolution();
                                    }
                                %>
                                <td>
                                    <%= h%>
                                </td>

                        <input type="hidden" name="orderDetailID" value="<%= tm.getReceiptDetailID()%>" readonly=""/>
                        <input type="hidden" name="quantity" value="<%= tm.getQuantityInBill()%>" readonly=""/>
                        <input type="hidden" name="orderID" value="<%= tm.getQuantityInShipping()%>" readonly=""/>
                        <input type="hidden" name="productID" value="<%= tm.getProductID()%>" readonly=""/>
                        <input type="hidden" name="productID" value="<%= tm.getReceiptID()%>" readonly=""/>
                        <input type="hidden" name="productID" value="<%= tm.getSolution()%>" readonly=""/>

                        </tr>
                        <%
                            }
                        %>
                        </tbody>          
                    </table>
                    <input type="button" value="Close" onclick="ShowDetailOrder()">

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
