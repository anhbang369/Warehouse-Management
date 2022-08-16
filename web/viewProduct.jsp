<%-- 
    Document   : viewProduct
    Created on : Jul 19, 2022, 11:17:19 PM
    Author     : GMT
--%>

<%@page import="inventoryStockeepr.ProductDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Product Page</title>
    </head>
    <body>
        <!--        <a href="welcome.jsp">Home</a>
                <a href="MainController?action=ViewProductSK">Check-in</a>
                <a href="MainController?action=ViewIssueSK">Check-out</a>
                <a href="MainController?action=ViewBinSK&search=">Position</a>-->
        <jsp:include page="stockeepHeader.jsp" />
        <div class="filter-container w-90">
            <a class="a-button " href="MainController?action=ViewConsignmentSK&search=">View All Consignment</a>
        </div>
        <%
            List<ProductDTO> product = (List<ProductDTO>) session.getAttribute("LIST_PRODUCT");
            if (product != null) {
                if (product.size() > 0) {
        %>
        <div class="outer-wrapper">
            <div class="table-wrapper">
                <table border="1">
                    <thead>
                    <th>#</th>
                    <th>Product Name</th>
                    <th>Product ID</th>
                    <th>Quantity</th>
                    <th>Action</th>
                    </thead>
                    <tbody  >
                        <%
                            int count = 1;
                            for (ProductDTO p : product) {
                        %>
                        <tr>
                            <td>
                                <%= count++%>
                            </td>
                            <td>
                                <input type="hidden" name="productID" value="<%=p.getProductID()%>">
                                <%=p.getProductID()%>
                            </td>
                            <td>
                                <input type="hidden" name="name" value="<%=p.getName()%>">
                                <%=p.getName()%>
                            </td>
                            <td>
                                <input type="hidden" name="quantity" value="<%=p.getQuantity()%>">
                                <%=p.getQuantity()%>
                            </td>
                            <td>
                                <a class="a-button" href="MainController?action=ViewConsignmentSK&search=<%=p.getProductID()%>">View Consignment</a>
                                <a class="a-button" href="MainController?action=ViewCreateConsignmentSK&productID=<%=p.getProductID()%>">Create Consignment</a>
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
        <h2><%=errorMessage%></h2>
        <script src="js/mycode.js"></script>
        <script src="https://code.iconify.design/2/2.2.1/iconify.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.5/dist/umd/popper.min.js" integrity="sha384-Xe+8cL9oJa6tN/veChSP7q+mnSPaj5Bcu9mPX5F5xIGE0DVittaqT5lorf0EI7Vk" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.min.js" integrity="sha384-kjU+l4N0Yf4ZOJErLsIcvOU2qSb74wXpOhqTvwVx3OElZRweTnQ6d31fXEoRD1Jy" crossorigin="anonymous"></script>

    </body>
</html>
