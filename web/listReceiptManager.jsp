<%-- 
    Document   : listReceiptManager
    Created on : Jul 22, 2022, 9:55:20 AM
    Author     : 84348
--%>

<%@page import="receiptAccountant.UserReceiptDetailS"%>
<%@page import="java.util.List"%>
<%@page import="receiptAccountant.UserReceipt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Receipt Page</title>
    </head>
    <body>
        <jsp:include page="managerHeader.jsp" />
        <div class="filter-container box-container">
            <div>
                <i class="fa-solid fa-sort" onclick="ShowSort()"></i>
            </div>
            <div id="sortLabel" class="show_date_receipt hide-show-fiter box-container" style="display: none" >
                <form action="MainController" name="sortReceiptManager" onsubmit="showSortReceiptManager()">
                    <label>Sort by: </label>
                    <select name="search">
                        <option value="1">Date old</option>
                        <option value="2">Date new</option>
                    </select>
                    <input type="hidden" name="action" value="SortReceiptManager">
                    <input type="submit" value="Sort"/>
                </form>
            </div>
            <form action="MainController" name="searchReceiptManager" onsubmit="showSearchReceiptManager()">
                <input type="text" name="receiptID" placeholder="receiptID">
                <input type="hidden" name="action" value="SearchReceiptManager">
                <input type="submit" value="Search" />
            </form>
        </div>
        <%
            List<UserReceipt> receipt = (List<UserReceipt>) session.getAttribute("LIST_RECEIPT_MANAGER");
            if (receipt != null) {
                if (receipt.size() > 0) {
        %>
        <div class="outer-wrapper">
            <div class="table-wrapper">
                <table border="1" class="table">
                    <thead>
                    <th>Receipt ID</th>
                    <th>Create date</th>
                    <th>Status</th>
                    <th>Total Quantity</th>
                    <th>Note</th>
                    <th>Action</th>
                    </thead>
                    <tbody >
                        <%
                            for (UserReceipt rc : receipt) {
                        %>
                    <form action="MainController" name="siewRceiptManager" onsubmit="showViewRceiptManager()">
                        <tr>
                            <td>
                                <%= rc.getReceiptID()%>
                                <input type="hidden" name="receiptId" value="<%= rc.getReceiptID()%>" />
                            </td>
                            <td>
                                <%= rc.getInputDate()%>
                            </td>
                            <%
                                String a = "";
                                if (rc.getStatus().equalsIgnoreCase("True")) {
                                    a = "No problem";
                                } else {
                                    a = "Handled";
                                }
                            %>
                            <td>
                                <%= a%>
                            </td>
                            <td>
                                <%= rc.getTotalQuantity()%>
                            </td>
                            <%
                                String c;
                                if (rc.getNote() == null) {
                                    c = "";
                                } else {
                                    c = rc.getNote();
                                }
                            %>
                            <td>
                                <%= c%>
                            </td>
                            <td>
                                <input type="hidden" name="action" value="ViewRceiptManager" readonly="" />
                                <input type="submit" value="View"/>
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
            List<UserReceiptDetailS> receiptDetail = (List<UserReceiptDetailS>) request.getAttribute("LIST_DETAIL_RECEIPT_MANAGER");
            if (receiptDetail != null) {
                if (receiptDetail.size() > 0) {
        %>
        <div id="orderDetail" class="box-container hide-show-fiter">
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
                            String s = "";
                            if (tm.getSolution() == null) {
                                s = "";
                            } else {
                                s = tm.getSolution();
                            }
                        %>
                        <td>
                            <%= s%>
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
        <script src="js/mycode.js"></script>
        <script src="https://code.iconify.design/2/2.2.1/iconify.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.5/dist/umd/popper.min.js" integrity="sha384-Xe+8cL9oJa6tN/veChSP7q+mnSPaj5Bcu9mPX5F5xIGE0DVittaqT5lorf0EI7Vk" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.min.js" integrity="sha384-kjU+l4N0Yf4ZOJErLsIcvOU2qSb74wXpOhqTvwVx3OElZRweTnQ6d31fXEoRD1Jy" crossorigin="anonymous"></script>

    </body>
</html>
