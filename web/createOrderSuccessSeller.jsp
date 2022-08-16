<%-- 
    Document   : createOrderSuccessSeller
    Created on : Jul 21, 2022, 2:28:20 AM
    Author     : 84348
--%>

<%@page import="orderSeller.UserOrderFullSeller"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Order Success Page</title>
    </head>
    <body>
        <jsp:include page="sellerHeader.jsp" />
        <%
            List<UserOrderFullSeller> list = (List<UserOrderFullSeller>) request.getAttribute("LIST_ORDER_FULLL_SUCCESS");
            if (list != null) {
                if (list.size() > 0) {

        %>

        <%            for (UserOrderFullSeller ele : list) {


        %>
        <div class="container box-container " style="font-size: 20px;text-align: unset;display: block;">
            <div class="row">
                <div class="col fw-bold">OrderID: <%= ele.getOrderID()%></div>
                <div class="col fw-bold">sellerID: <%= ele.getSellerID()%></div>
                
            </div>
            <div class="row">
                <div class="col fw-bold">Customer Name: <%= ele.getCustomerName()%></div>
                <div class="col fw-bold">Phone Number: <%= ele.getPhoneNumber()%></div>
            </div>
            <div class="row">
                <div class="col fw-bold">Address: <%= ele.getAddress()%></div>
                <div class="col-6 fw-bold">Delivery Date: <%= ele.getDeliveryDate()%></div>
            </div>
            <div class="row">
                <div class="col fw-bold">Note: <%= ele.getNote()%></div>
            </div>
        </div>

        <%
                break;

            }
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>No</th>
                    <th>orderDetailID</th>
                    <th>quantity</th>
                    <th>orderID</th>
                    <th>productID</th>
                </tr>
            </thead>
            <tbody>
                <%                    int count = 1;
                    for (UserOrderFullSeller elem : list) {


                %>
            <form action="MainController">
                <tr>
                    <td>
                        <%= count++%>
                    </td>
                    <td>
                        <%= elem.getOrderDetailID()%>
                    </td>
                    <td>
                        <%= elem.getQuantity()%>
                    </td>
                    <td>
                        <%= elem.getOrderID()%>
                    </td>
                    <td>
                        <%= elem.getProductID()%>
                    </td>
                </tr>
            </form>
        </tbody>
        <%

            }
        %>
    </table>
    <%
            }
        }
    %>
    <form class="filter-container" action="MainController" name="exportOrder" onsubmit="changeExportOrderBtn()" style="width: 85%;">
        <input type="hidden" name="action" value="ExportOrderSeller" >
        <input type="submit" name="action" value="Export">
    </form>
    <script src="js/mycode.js"></script>
    <script src="https://code.iconify.design/2/2.2.1/iconify.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.5/dist/umd/popper.min.js" integrity="sha384-Xe+8cL9oJa6tN/veChSP7q+mnSPaj5Bcu9mPX5F5xIGE0DVittaqT5lorf0EI7Vk" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.min.js" integrity="sha384-kjU+l4N0Yf4ZOJErLsIcvOU2qSb74wXpOhqTvwVx3OElZRweTnQ6d31fXEoRD1Jy" crossorigin="anonymous"></script>

</body>
</html>
