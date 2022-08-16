<%-- 
    Document   : viewConsignment
    Created on : Jul 21, 2022, 12:55:33 AM
    Author     : GMT
--%>


<%@page import="inventoryStockeepr.ProductDTO"%>
<%@page import="inventoryStockeepr.InventoryDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Consignment Page</title>
    </head>
    <body>
        <!--        <a href="welcome.jsp">Home</a>
                <<a href="MainController?action=ViewProductSK">Check-in</a>
                <a href="MainController?action=ViewIssueSK">Check-out</a>
                <a href="MainController?action=ViewBinSK&search=">Position</a>-->
        <jsp:include page="stockeepHeader.jsp" />


        <div class="box-container filter-container align-items-center">
            <div id="iconSearchConsignment">
                <i class="fa-solid fa-sort" onclick="ShowSort()"></i>
            </div >

            <div  id="sortLabel" class="show_date_receipt hide-show-fiter box-container" style="display: none; z-index: 1;top: 25%">
                <form action="MainController">
                    <label>Sort by: </label>
                    <select name="search">
                        <option value="1">Date old</option>
                        <option value="2">Date new</option>
                    </select>
                    <input type="submit" name="action" value="SortConsignmentSK">
                </form>
            </div>

            <%
                if (session.getAttribute("PRODUCT") == null) {
            %>
            <form name="searchInvenSK" onsubmit="showSearchInvenSK()" action="MainController" method="POST" class=" filter-container align-items-center">
                <input type="text" name="productID" placeholder="Product ID"/>
                <input type="checkbox" name="statusA" value="Available"/>
                <label class="fw-bold" for="statusA"> Available</label><br>
                <input type="checkbox" name="statusN" value="Not available"/>
                <label class="fw-bold" for="statusB"> Not available</label><br>
                <input type="hidden" name="action" value="SearchInvenSK"/>
                <input type="submit" value="Search"/>
            </form>
            <%
            } else {
                ProductDTO pSearch = (ProductDTO) session.getAttribute("PRODUCT");
            %>
            <form name="searchInvenSK" onsubmit="showSearchInvenSK()" action="MainController" method="POST" class=" filter-container align-items-center">
                <input type="hidden" name="productID" value="<%=pSearch.getProductID()%>"/>
                <input type="checkbox" name="statusA" value="Available"/>
                <label class="fw-bold" for="statusA"> Available</label><br>
                <input type="checkbox" name="statusN" value="Not available"/>
                <label class="fw-bold" for="statusB"> Not available</label><br>
                <input type="hidden" name="action" value="SearchInvenSK"/>
                <input type="submit" value="Search"/>
            </form>
            <%}%>
        </div>

        <%

            List<InventoryDTO> inven = (List<InventoryDTO>) session.getAttribute("LIST_INVEN");
            if (inven != null) {
                if (inven.size() > 0) {
        %>
        <div class="outer-wrapper">
            <div class="table-wrapper">
                <table border="1">
                    <thead>
                    <th>#</th>
                    <th>INVENTORY ID</th>
                    <th>INPUT DATE</th>
                    <th>PRODUCT ID</th>
                    <th>STATUS</th>
                    <th>ACTION</th>
                    </thead>
                    <tbody >
                        <%
                            int count = 1;
                            for (InventoryDTO i : inven) {
                        %>
                        <tr>
                            <td>
                                <%= count++%>
                            </td>
                            <td>
                                <input type="hidden" name="inventoryID" value="<%=i.getInventoryID()%>">
                                <%=i.getInventoryID()%>
                            </td>
                            <td>
                                <input type="hidden" name="inputDate" value="<%=i.getInputDate()%>">
                                <%=i.getInputDate()%>
                            </td>
                            <td>
                                <input type="hidden" name="productID" value="<%=i.getProductID()%>">
                                <%=i.getProductID()%>
                            </td>
                            <td>
                                <input type="hidden" name="status" value="<%=i.getNote()%>">
                                <%=i.getNote()%>
                            </td>
                            <td>
                                <a class="a-button" href="MainController?action=ViewSK&inventoryID=<%=i.getInventoryID()%>">View</a>
                            </td>
                        </tr>
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
            String errorMessage = (String) request.getAttribute("ERROR");
            if (errorMessage == null) {
                errorMessage = "";
            }
        %>
        <h2 class="error"><%=errorMessage%></h2>
        <script src="js/mycode.js"></script>
        <script src="https://code.iconify.design/2/2.2.1/iconify.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.5/dist/umd/popper.min.js" integrity="sha384-Xe+8cL9oJa6tN/veChSP7q+mnSPaj5Bcu9mPX5F5xIGE0DVittaqT5lorf0EI7Vk" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.min.js" integrity="sha384-kjU+l4N0Yf4ZOJErLsIcvOU2qSb74wXpOhqTvwVx3OElZRweTnQ6d31fXEoRD1Jy" crossorigin="anonymous"></script>

    </body>
</html>
