
<%@page import="inventoryStockeepr.BinDTO"%>
<%-- 
    Document   : createBin
    Created on : Jul 22, 2022, 9:53:54 AM
    Author     : GMT
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Bin Page</title>
    </head>
    <body>
        <jsp:include page="stockeepHeader.jsp" />
        <!--        <a href="welcome.jsp">Home</a>
                <a href="MainController?action=ViewProductSK">Check-in</a>
                <a href="MainController?action=ViewIssueSK">Check-out</a>
                <a href="MainController?action=ViewBinSK&search=">Position</a>-->
        <%
            String action = request.getParameter("action");
        %>
        <div class="container box-container">
            <form action="MainController" method="POST" name="saveCreateSK" onsubmit="showSaveCreateSK()">
                <div class="row ">
                    <div class="col align-items-center d-flex justify-content-between">
                        <span>Bin ID:</span>
                        <input style="width: 300px;margin-left: 70px !important" type="text" name="binID" required=""/>
                    </div>
                    <div class="col align-items-center d-flex justify-content-between">
                        <span>Capacity:</span>
                        <input style="width: 300px;" type="number" name="capacity" required="" min="100" max="1000" step="50"/>
                    </div>
                </div>
                <div class="row">
                    <div class="col align-items-center d-flex justify-content-between">
                        Available for:
                        <input style="width: 300px;"  type="text" name="available" required="" maxlength="2"/>
                    </div>
                    <div class="col ">
                    </div>
                </div>
                <div class="filter-container">
                    <input type="hidden" name="action" value="SaveCreateSK"/>
                    <input type="submit" value="Save"/>
                </div>
            </form>
        </div>
        <%
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
