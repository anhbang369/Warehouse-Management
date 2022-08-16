<%-- 
    Document   : viewConsignmentDetail
    Created on : Jul 21, 2022, 3:03:10 PM
    Author     : GMT
--%>

<%@page import="inventoryStockeepr.BinDTO"%>
<%@page import="inventoryStockeepr.InventoryDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Consignment Detail Page</title>
    </head>
    <body>
        <!--        <a href="welcome.jsp">Home</a>
                <a href="MainController?action=ViewProductSK">Check-in</a>
                <a href="MainController?action=ViewIssueSK">Check-out</a>
                <a href="MainController?action=ViewBinSK&search=">Position</a>-->
        <jsp:include page="stockeepHeader.jsp" />
        <%
            InventoryDTO inven = (InventoryDTO) session.getAttribute("INVENTORY");
            List<BinDTO> listBin = (List<BinDTO>) session.getAttribute("LIST_BIN");
            if (inven != null) {
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>Input Date</th>
                    <th>Product ID</th>
                    <th>Warranty</th>
                    <th>Stockkeeper</th>
                    <th>Quantity on hand</th>
                    <th>Status</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>
                        <%=inven.getInputDate()%>
                    </td>
                    <td>
                        <%=inven.getProductID()%>
                    </td>
                    <td>
                        <%=inven.getWarranty()%>
                    </td>
                    <td>
                        <%=inven.getStockKeeperID()%>
                    </td>
                    <td>
                        <%=inven.getQuantityOnHand()%>
                    </td>
                    <td>
                        <%=inven.getNote()%>
                    </td>
                </tr>
            </tbody>
        </table>
        <%
            if (listBin.size() > 0) {
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>#</th>
                    <th>BIN ID</th>
                    <th>QUANTITY</th>
                </tr>
            </thead>
            <tbody>
                <%
                    int count = 1;
                    for (BinDTO b : listBin) {
                %>
                <tr>
                    <td><%=count++%></td>
                    <td>
                        <input type="hidden" name="binID" value="<%=b.getBinID()%>"/>
                        <%=b.getBinID()%>
                    </td>
                    <td>
                        <input type="hidden" name="binID" value="<%=b.getQuantity()%>"/>
                        <%=b.getQuantity()%>
                    </td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>
        <%
                }
            }
            String errorMessage = (String) request.getAttribute("ERROR");
            if (errorMessage == null) {
                errorMessage = "";
            }
        %>
        <%=errorMessage%>
        <script src="js/mycode.js"></script>
        <script src="https://code.iconify.design/2/2.2.1/iconify.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.5/dist/umd/popper.min.js" integrity="sha384-Xe+8cL9oJa6tN/veChSP7q+mnSPaj5Bcu9mPX5F5xIGE0DVittaqT5lorf0EI7Vk" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.min.js" integrity="sha384-kjU+l4N0Yf4ZOJErLsIcvOU2qSb74wXpOhqTvwVx3OElZRweTnQ6d31fXEoRD1Jy" crossorigin="anonymous"></script>

    </body>
</html>
