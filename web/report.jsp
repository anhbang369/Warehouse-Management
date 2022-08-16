<%-- 
    Document   : report
    Created on : Jun 17, 2022, 10:05:43 AM
    Author     : 84348
--%>

<%@page import="reportAccountant.UserReport"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Report Page</title>
    </head>
    <body>
        <!--        <a href="MainController?action=SeacrhReceipt&search=">Receipt</a>
                <a href="MainController?action=SeacrhOrder&OrderI=&CustomerNam=&Addres=&PhoneNumbe=">Issue</a>
                <a href="report.jsp">Report</a>
                <a href="MainController?action=SearchInventoryAlpha&productID=&name=">Inventory Report</a>-->
        <jsp:include page="accountantHeader.jsp" />

        <form class="box-container filter-container align-items-center" action="MainController" name="SRSearch" onsubmit="SRSearchForm();">
            <input type="text" name="productID" placeholder="productID">
            <input style="margin: 0 20px !important" type="text" name="name" placeholder="Name">
            From<input style="margin: 0 20px !important" type="date" name="date" required="">
            To<input type="date" name="date2"required="">
            <input type="hidden" name="action" value="ShowReport" >
            <input type="submit" value="Show"/>
        </form>


        <%
            String errorp = (String) request.getAttribute("DATE_PO");
            if (errorp == null) {
                errorp = "";
            }
        %>
        <h2 class="text-center fw-bold " style="font-size: 20px"><%=errorp%></h2>

        <!--        <div>
                    <i class="fa-solid fa-sort" onclick="ShowSort()"></i>
                    
                </div>-->

        <!--        <div id="sortLabel" class="show_date_receipt">
                    <form action="MainController">
                        <label>Sort by: </label>
                        <select name="search">
                            <option value="1">Brand reduce</option>
                            <option value="2">Brand increase</option>
                        </select>
                        <input type="submit" name="action" value="SortReport">
                    </form>
                </div>-->





        <%
            List<UserReport> tp = (List<UserReport>) session.getAttribute("LIST_REPORT");
            if (tp != null) {
                if (tp.size() > 0) {
        %>
        <div class="outer-wrapper">
            <div class="table-wrapper">
                <table border="1">
                    <thead>
                    <th>No</th>
                    <th>ProductID</th>
                    <th>Name</th>
                    <th>Brand</th>
                    <th>Quantity Begin</th>
                    <th>Quantity End</th>
                    <th>Import</th>
                    <th>Export</th>
                    </thead>
                    <tbody >
                        <%
                            int count = 1;
                            for (UserReport tm : tp) {
                        %>
                    <form action="MainController">
                        <tr>
                            <td>
                                <%= count++%>
                            </td>
                            <td>
                                <%= tm.getProductID()%>
                            </td>
                            <td>
                                <%= tm.getName()%>
                            </td>
                            <td>
                                <%= tm.getBrand()%>
                            </td>
                            <td>
                                <%= tm.getQuantityBegin()%>
                            </td>
                            <td>
                                <%= tm.getQuantityEnd()%>
                            </td>
                            <td>
                                <%= tm.getImport()%>
                            </td>
                            <td>
                                <%= tm.getExport()%>
                            </td>
                        <input type="hidden" name="orderID" value="<%= tm.getProductID()%>"/>
                        <input type="hidden" name="orderID" value="<%= tm.getName()%>"/>
                        <input type="hidden" name="orderID" value="<%= tm.getBrand()%>" />
                        <input type="hidden" name="orderID" value="<%= tm.getQuantityBegin()%>" />
                        <input type="hidden" name="orderID" value="<%= tm.getQuantityEnd()%>" />
                        <input type="hidden" name="orderID" value="<%= tm.getImport()%>" />
                        <input type="hidden" name="orderID" value="<%= tm.getExport()%>" />
                        </tr>
                    </form>
                    <%
                        }
                    %>
                    </tbody>
                </table>
            </div>
        </div>

        <form action="MainController" class="filter-container w-90">
            <input type="submit" name="action" value="Export Report">
        </form>
        <%
                }
            }
        %>

        <%
            String error = (String) request.getAttribute("ERROR_REPORT");
            if (error == null) {
                error = "";
            }
        %>
        <h2 class="error"><%=error%></h2>
        <script src="js/mycode.js"></script>
        <script src="https://code.iconify.design/2/2.2.1/iconify.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.5/dist/umd/popper.min.js" integrity="sha384-Xe+8cL9oJa6tN/veChSP7q+mnSPaj5Bcu9mPX5F5xIGE0DVittaqT5lorf0EI7Vk" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.min.js" integrity="sha384-kjU+l4N0Yf4ZOJErLsIcvOU2qSb74wXpOhqTvwVx3OElZRweTnQ6d31fXEoRD1Jy" crossorigin="anonymous"></script>

    </body>
</html>
