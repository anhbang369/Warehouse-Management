<%-- 
    Document   : createSuccessInventory
    Created on : Jul 7, 2022, 11:41:37 AM
    Author     : 84348
--%>

<%@page import="java.util.List"%>
<%@page import="inventoryAccountant.UserInventoryFull"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Success Inventory Page</title>
    </head>
    <body>
        <jsp:include page="accountantHeader.jsp" />
        <!--        <a href="MainController?action=SeacrhReceipt&search=">Receipt</a>
                <a href="MainController?action=SeacrhOrder&OrderI=&CustomerNam=&Addres=&PhoneNumbe=">Issue</a>
                <a href="report.jsp">Report</a>
                <a href="MainController?action=SearchInventoryAlpha&productID=&name=">Inventory Report</a>-->

        <form action="MainController" name="exportInventoryAC" onsubmit="showExportInventoryAC()"> 
            <%
                List<UserInventoryFull> list = (List<UserInventoryFull>) request.getAttribute("LIST_DETAIL_INVENTORY");
                if (list != null) {
                    if (list.size() > 0) {

            %>

            <table border="1">
                <thead>
                    <tr>
                        <th>reportDetailID</th>
                        <th>reportID</th>
                        <th>productID</th>
                        <th>quality</th>
                        <th>quantityInChecking</th>
                        <th>quantity</th>
                        <th>Note</th>
                    </tr>
                </thead>
                <tbody>
                    <%                        for (UserInventoryFull elem : list) {
                    %>
                <div class="box-container">
                    <div class="row">
                        <div class="col">ReportID: <%=elem.getReportID()%></div>
                        <div class="col">
                            Checking Data: <%=elem.getCheckingDate()%>
                        </div>
                    </div>


                </div>
                <%
                        break;
                    }
                %>

                <%
                    for (UserInventoryFull rc : list) {

                %>

                <tr>
                    <td>
                        <%= rc.getReportDetailID()%>
                    </td>
                    <td>
                        <%= rc.getReportID()%>
                    </td>
                    <td>
                        <%= rc.getProductID()%>
                    </td>
                    <td>
                        <%= rc.getQuality()%>
                    </td>
                    <td>
                        <%= rc.getQuantityInChecking()%>
                    </td>
                    <td>
                        <%= rc.getQuantity()%>
                    </td>
                    <td>
                        <%= rc.getNote()%>
                    </td>
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
            <div class="w-90 filter-container">
                <input  type="hidden" name="action" value="ExportInventory">
                <input type="submit" value="Export"/>
            </div>
        </form>
        <script src="js/mycode.js"></script>
        <script src="https://code.iconify.design/2/2.2.1/iconify.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.5/dist/umd/popper.min.js" integrity="sha384-Xe+8cL9oJa6tN/veChSP7q+mnSPaj5Bcu9mPX5F5xIGE0DVittaqT5lorf0EI7Vk" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.min.js" integrity="sha384-kjU+l4N0Yf4ZOJErLsIcvOU2qSb74wXpOhqTvwVx3OElZRweTnQ6d31fXEoRD1Jy" crossorigin="anonymous"></script>

    </body>
</html>
