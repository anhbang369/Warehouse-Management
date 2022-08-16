<%-- 
    Document   : viewListLocation
    Created on : Jul 22, 2022, 1:18:37 AM
    Author     : GMT
--%>


<%@page import="ListStockeeper.LocationDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Location Page</title>
    </head>
    <body>
        <!--        <a href="welcome.jsp">Home</a>
                <a href="MainController?action=ViewProductSK">Check-in</a>
                <a href="MainController?action=ViewIssueSK">Check-out</a>
                <a href="MainController?action=ViewBinSK&search=">Position</a>
                <a href="MainController?action=CreateBinSK">Create</a>-->
        <jsp:include page="stockeepHeader.jsp" />
        <form class="filter-container w-90" action="MainController" method="POST" name="createBinSK" onsubmit="showCreateBinSK()">
            <input type="hidden" name="action" value="CreateBinSK">
            <input type="submit" value="Create"/>
        </form>
        <%
            List<LocationDTO> location = (List<LocationDTO>) session.getAttribute("LIST_LOCATION");
            if (location != null) {
                if (location.size() > 0) {
        %>
        <div class="outer-wrapper">
            <div class="table-wrapper">
                <table border="1">
                    <thead>
                    <th>#</th>
                    <th>Bin ID</th>
                    <th>Input Date</th>
                    <th>Quantity</th>
                    <th>Capacity</th>
                    <th>Available</th>
                    <th>Action</th>
                    </thead>
                    <tbody >
                        <%
                            int count = 1;
                            for (LocationDTO l : location) {
                        %>
                    <form action="MainController" method="POST" name="updateBinStockepp" onsubmit="showUpdateBinStockepp()">
                        <tr>
                            <td>
                                <%= count++%>
                            </td>
                            <td>
                                <input type="hidden" name="binID" value="<%=l.getBinID()%>">
                                <%=l.getBinID()%>
                            </td>
                            <td>
                                <input type="hidden" name="inputDate" value="<%=l.getInputDate()%>">
                                <%=l.getInputDate()%>
                            </td>
                            <td>
                                <input type="hidden" name="quantity" value="<%=l.getQuantity()%>">
                                <%=l.getQuantity()%>
                            </td>
                            <td>
                                <input type="text" name="capacity" value="<%=l.getCapacity()%>">
                            </td>
                            <td>
                                <input type="text" name="available" value="<%=l.getAvalable()%>">
                            </td>
                            <td>
                                <input type="hidden" name="action" value="UpdateBinStockepp">
                                <input type="submit" value="Update"/>
                                <a class="a-button" href="MainController?action=DeleteBinSK&binID=<%=l.getBinID()%>">Delete</a>
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
            String errorMessage = (String) request.getAttribute("ERROR");
            if (errorMessage == null) {
                errorMessage = "";
            }
        %>
        <h2><%=errorMessage%></h2>
        <script src="js/mycode.js"></script>
        <script src="https://code.iconify.design/2/2.2.1/iconify.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.5/dist/umd/popper.min.js" integrity="sha384-Xe+8cL9oJa6tN/veChSP7q+mnSPaj5Bcu9mPX5F5xIGE0DVittaqT5lorf0EI7Vk" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.min.js" integrity="sha384-kjU+l4N0Yf4ZOJErLsIcvOU2qSb74wXpOhqTvwVx3OElZRweTnQ6d31fXEoRD1Jy" crossorigin="anonymous"></script>

    </body>
</html>
