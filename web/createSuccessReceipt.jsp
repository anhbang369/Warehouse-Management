<%-- 
    Document   : createSuccessReceipt
    Created on : Jun 16, 2022, 4:26:18 PM
    Author     : 84348
--%>

<%@page import="receiptAccountant.UserFakeList"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Success Receipt Page</title>
    </head>
    <body>
        <jsp:include page="accountantHeader.jsp" />
        <!--        <a href="MainController?action=SeacrhReceipt&search=">Receipt</a>
                <a href="MainController?action=SeacrhOrder&OrderI=&CustomerNam=&Addres=&PhoneNumbe=">Issue</a>
                <a href="report.jsp">Report</a>
                <a href="MainController?action=SearchInventoryAlpha&productID=&name=">Inventory Report</a>-->

        <form action="MainController">
            <%
                List<UserFakeList> list = (List<UserFakeList>) request.getAttribute("LIST_ALL_RECEIPT");
                if (list != null) {
                    if (list.size() > 0) {

            %>


            <%                    for (UserFakeList elem : list) {


            %>
            <div class="container box-container">
                <div class="row">
                    <div class="col flex-between">
                        Accountant ID: <div><%=elem.getAccountantID()%></div>
                    </div>
                    <div class="col flex-between">
                        StockKeeper ID: <div><%=elem.getStockKeeperID()%></div>
                    </div>
                </div>
                <div class="row">
                    <div class="col  flex-between">
                        Input Date: <div><%=elem.getInputDate()%></div>
                    </div>
                    <div class="col flex-between">
                        Total Quantity: <div><%=elem.getTotalQuantity()%></div>
                    </div>
                    
                </div>
                    <div class="row">
                        <div class="col flex-between">
                        Note: <div><%=elem.getNote()%></div>
                    </div>
                    </div>
            </div>
            <%
                    break;
                }
            %>
            <form action="MainController">

                <div class="custom-container">
                    <table border="1">
                        <thead>
                            <tr>
                                <th>Product ID</th>
                                <th>Model</th>
                                <th>Brand</th>
                                <th>type</th>
                                <th>receiptDetailID</th>
                                <th>Quantity In Bill</th>
                                <th>Quantity In Shipping</th>
                                <th>Solution</th>
                            </tr>
                        </thead>
                        <tbody>

                            <%
                                for (UserFakeList rc : list) {

                            %>

                            <tr>
                                <td>
                                    <%= rc.getProductID()%>
                                </td>
                                <td>
                                    <%= rc.getModel()%>
                                </td>
                                <td>
                                    <%= rc.getBrand()%>
                                </td>
                                <td>
                                    <%= rc.getName()%>
                                </td>
                                <td>
                                    <%= rc.getReceiptDetailID()%>
                                </td>
                                <td>
                                    <%= rc.getQuantityInBill()%>
                                </td>
                                <td>
                                    <%= rc.getQuantityInShipping()%>
                                </td>
                                <td>
                                    <%= rc.getSolution()%>
                                </td>
                        <input type="hidden" name="inputDate" value="<%= rc.getInputDate()%>" readonly=""/>
                        <input type="hidden" name="status" value="<%= rc.getStatus()%>" readonly=""/>
                        <input type="hidden" name="accountantID" value="<%= rc.getAccountantID()%>" readonly=""/>
                        <input type="hidden" name="stockKeeperID" value="<%= rc.getStockKeeperID()%>" readonly=""/>
                        <input type="hidden" name="totalQuantity" value="<%= rc.getTotalQuantity()%>" readonly=""/>
                        <input type="hidden" name="note" value="<%= rc.getNote()%>" readonly=""/>
                        </tr>   

                        </tbody>
                        <%

                            }
                        %>
                    </table>



                    <%
                            }
                        }
                    %>

                    <div class="filter-container wi-85" style="margin: 0 auto;">
                        <input type="text" name="conclusion" placeholder="Enter Conclusion">
                        <input type="submit" name="action" value="Conclusion">
                        <input type="submit" name="action" value="Export">
                    </div>
            </form>
            <script src="js/mycode.js"></script>
            <script src="https://code.iconify.design/2/2.2.1/iconify.min.js"></script>
            <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.5/dist/umd/popper.min.js" integrity="sha384-Xe+8cL9oJa6tN/veChSP7q+mnSPaj5Bcu9mPX5F5xIGE0DVittaqT5lorf0EI7Vk" crossorigin="anonymous"></script>
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.min.js" integrity="sha384-kjU+l4N0Yf4ZOJErLsIcvOU2qSb74wXpOhqTvwVx3OElZRweTnQ6d31fXEoRD1Jy" crossorigin="anonymous"></script>

    </body>
</html>
