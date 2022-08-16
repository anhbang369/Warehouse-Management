<%-- 
    Document   : createReceiptDetail
    Created on : Jun 18, 2022, 5:12:32 PM
    Author     : 84348
--%>

<%@page import="user.UserAccount"%>
<%@page import="receiptAccountant.UserReceiptDetail"%>
<%@page import="virtual.ListReceiptM"%>
<%@page import="user.UserProduct"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Receipt Detail Page</title>
    </head>
    <body>
        <jsp:include page="accountantHeader.jsp" />
        <div class="box-container filter-container">
            <form action="MainController" name="RDSearch" onsubmit="RDSearchForm();">
                <input type="text" name="ProductID" placeholder="ProductID">
                <input type="text" name="Name" placeholder="Name">
                <input type="hidden" name="Brand" placeholder="Brand">
                <input type="hidden" name="Model" placeholder="Model">
                <input type="hidden" name="action" value="ShowProductCreate">
                <input type="submit" value="Search"/>
            </form>
        </div>
        <%
            String errorSearch = (String) request.getAttribute("ERROR_SEARCH");
            if (errorSearch == null) {
                errorSearch = "";
            }
        %>
        <h2><%=errorSearch%></h2>
        <%
            List<UserProduct> product = (List<UserProduct>) request.getAttribute("LIST_PRODUCT");
            if (product != null) {
                if (product.size() > 0) {
        %>
        <div class="container" style="max-width: 90%">
            <div class="row">
                <div class="col" >
                    <div class="outer-wrapper">
                        <div class="table-wrapper" style="height: 400px">
                            <table border="1" class="table">
                                <thead>
                                <th>Product ID</th>
                                <th>Name</th>
                                <th>Quantity In Bill</th>
                                <th>Quantity In Shipping</th>
                                <th>Solution</th>
                                <th>Action</th>
                                </thead>
                                <tbody >
                                    <%
                                        for (UserProduct rc : product) {
                                    %>
                                <form action="MainController" name="RDPSearch" onsubmit="RDPSearchForm();">
                                    <tr>
                                        <td>
                                            <%=rc.getProductID()%>
                                        </td>
                                        <td>
                                            <%=rc.getName()%>
                                        </td>
                                        <td>
                                            <input class="w-90" type="number" name="quantityInBill" required="" />
                                        </td>
                                        <td>
                                            <input class="w-90" type="number" name="quantityInShipping" required=""/>
                                        </td>
                                        <td>
                                            <input type="text" name="solution"/>
                                        </td>
                                        <td>
                                            <input type="hidden" name="action" value="AddVirtualReceiDetail"/>
                                            <input type="submit" value="Add"/>
                                        </td>
                                    </tr>
                                    <input type="hidden" name="productID" value="<%=rc.getProductID()%>"/>
                                    <input type="hidden" name="name" value="<%=rc.getName()%>"/>
                                    <input type="hidden" name="brand" value="<%=rc.getBrand()%>"/>
                                    <input type="hidden" name="model" value="<%=rc.getModel()%>"/>
                                </form>
                                <%
                                    }
                                %>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <%
                        }
                    }
                %> 

                <%
                    String errorCheck = (String) request.getAttribute("ERROR_CHECK");
                    if (errorCheck == null) {
                        errorCheck = "";
                    }
                %>
                <h2 class="error"><%=errorCheck%></h2>


                <%
                    ListReceiptM rd = (ListReceiptM) session.getAttribute("VIRTUAL_RECEI");
                    if (rd != null) {
                        if (rd.getListReceipt().size() > 0) {
                %>
                <div class="box-container fw-bold col " style="margin: 0 auto;padding: 10">
                    <h3>Product Will Add Detail</h3>
                    <div class="outer-wrapper">
                        <div class="table-wrapper">
                            <table border="1" style="box-sizing: content-box" class="table">
                                <thead>
                                <th>No</th>
                                <th>Product ID</th>
                                <th>Quantity In Bill</th>
                                <th>Quantity In Shipping</th>
                                <th>Solution</th>
                                <th>Action</th>
                                </thead>
                                <tbody  >
                                    <%
                                        int count = 1;
                                        for (UserReceiptDetail tm : rd.getListReceipt().values()) {
                                    %>
                                <form action="MainController" name="studentForm" onsubmit="submitForm();">
                                    <tr>
                                        <td>
                                            <%= count++%>
                                        </td>
                                        <td>
                                            <%= tm.getProductID()%>
                                            <input class="w-90" type="hidden" name="productID" value="<%= tm.getProductID()%>" readonly=""/>
                                        </td>
                                        <td>
                                            <input class="w-90" type="text" name="quantityInBill" value="<%= tm.getQuantityInBill()%>" required="" min="1"/>
                                        </td>
                                        <td>
                                            <input class="w-90" type="text" name="quantityInShipping" value="<%= tm.getQuantityInShipping()%>" required="" min="1"/>
                                        </td>
                                        <td>
                                            <input class="w-90" type="text" name="solution" value="<%= tm.getSolution()%>"/>
                                        </td>
                                        <td class="" style="">
                                            <div class="d-flex justify-content-between">
                                                <input type="hidden" name="action" value="UpdateReceiptDetailVirtual" readonly=""/>
                                                <input type="submit" value="Update"/>
                                                <a class="a-button" href="MainController?action=RemoveReceiptDetailVirtual&productID=<%= tm.getProductID()%>">Delete</a>
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
                </div>

                <%
                        }
                    }
                %>
            </div>
        </div>
        <%
            String error = (String) request.getAttribute("ERROR");
            if (error == null) {
                error = "";
            }
        %>
        <h2 class="error"><%=error%></h2>


        <div class="box-container  " style="">
            <form action="MainController" class="d-flex justify-content-center align-items-center">
                <!--    <input type="text" name="stockKeeperID" placeholder="stockKeeperID" required="">-->
                <div for="" class="fw-bold text-center" style="font-size: 25px">StockKeeperID:</div>
                <%
                    List<UserAccount> account = (List<UserAccount>) session.getAttribute("LIST_ACCOUNT");
                    if (account != null) {
                        if (account.size() > 0) {
                %>
                <select name="stockKeeperID">
                    <%
                        for (UserAccount rc : account) {
                    %>
                    <option value="<%= rc.getAccountID()%>"><%= rc.getName()%></option>
                    <%
                        }
                    %>
                </select>
                <%
                        }
                    }
                %> 
                <input type="submit" name="action" value="Finish">
            </form>
        </div>
        <%
            String errorSK = (String) request.getAttribute("VIRTUAL_RECEI_ERROR");
            if (errorSK == null) {
                errorSK = "";
            }
        %>
        <h2 class="error"><%=errorSK%></h2>


        <%
            String errorFinish = (String) request.getAttribute("ERROR_FINISH");
            if (errorFinish == null) {
                errorFinish = "";
            }
        %>
        <h2 class="error"><%=errorFinish%></h2>


        <script src="js/mycode.js"></script>
        <script src="https://code.iconify.design/2/2.2.1/iconify.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.5/dist/umd/popper.min.js" integrity="sha384-Xe+8cL9oJa6tN/veChSP7q+mnSPaj5Bcu9mPX5F5xIGE0DVittaqT5lorf0EI7Vk" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.min.js" integrity="sha384-kjU+l4N0Yf4ZOJErLsIcvOU2qSb74wXpOhqTvwVx3OElZRweTnQ6d31fXEoRD1Jy" crossorigin="anonymous"></script>

    </body>
</html>
