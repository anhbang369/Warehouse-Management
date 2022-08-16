<%-- 
    Document   : createInventory
    Created on : Jun 21, 2022, 10:38:35 PM
    Author     : 84348
--%>

<%@page import="inventoryAccountant.UserInventoryVirtual"%>
<%@page import="virtual.ListInventory"%>
<%@page import="user.UserProduct"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Inventory Page</title>
    </head>
    <body>
        <jsp:include page="accountantHeader.jsp"/>
        <div class="box-container filter-container">
            <form action="MainController" name="PRSearch" onsubmit="PRSearchForm();">
                <input type="text" name="ProductID" placeholder="ProductID">
                <input type="text" name="Name" placeholder="Name">
                <input type="hidden" name="Brand" placeholder="Brand">
                <input type="hidden" name="Model" placeholder="Model">
                <input type="hidden" name="action" value="ShowProductReprot">
                <input type="submit" value="Search"/>
            </form>
        </div>
        <%
            String errorSearch = (String) request.getAttribute("ERROR_SEARCH");
            if (errorSearch == null) {
                errorSearch = "";
            }
        %>
        <h2 class="error"><%=errorSearch%></h2>


        <%
            List<UserProduct> product = (List<UserProduct>) request.getAttribute("LIST_PRODUCT_REPORT");
            if (product != null) {
                if (product.size() > 0) {
        %>
        <div class="container" style="max-width: 90%">
            <div class="row">
                <div class="col" >
                    <div class="outer-wrapper">
                        <div class="table-wrapper">
                            <table border="1">
                                <thead>
                                <th>Product ID</th>
                                <th>Name</th>
                                <th>Quality (%)</th>
                                <th>Quantity In System</th>
                                <th>Quantity In Checking</th>
                                <th>Note</th>
                                <th>Action</th>
                                </thead>
                                <tbody >
                                    <%
                                        for (UserProduct rc : product) {
                                    %>
                                <form action="MainController" name="APRSearch" onsubmit="APRSearchForm();">
                                    <tr>
                                        <td>
                                            <input type="hidden" name="productID" value="<%=rc.getProductID()%>"/>
                                            <%=rc.getProductID()%>
                                        </td>
                                        <td>
                                            <input type="hidden" name="name" value="<%=rc.getName()%>"/>
                                            <%=rc.getName()%>
                                        </td>
                                    <input type="hidden" name="brand" value="<%=rc.getBrand()%>"/>
                                    <input type="hidden" name="model" value="<%=rc.getModel()%>"/>
                                    <td>
                                        <input class="w-90" type="number" name="quality" required=""  min="1" max="100"/>
                                    </td>
                                    <td>
                                        <input type="text" name="quantity" readonly="" value="<%=rc.getQuantity()%>"/>
                                    </td>
                                    <td>
                                        <input class="w-90" type="number" name="quantityInChecking" required="" min="1"/>
                                    </td>
                                    <td>
                                        <input type="text" name="note" />
                                    </td>
                                    <td>
                                        <input type="hidden" name="action" value="AddVirtualInventory"/>
                                        <input type="submit" value="Add"/>
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


                <%
                    String errorCheck = (String) request.getAttribute("ERROR_CHECK");
                    if (errorCheck == null) {
                        errorCheck = "";
                    }
                %>
                <h2><%=errorCheck%></h2>

                <%
                    ListInventory rd = (ListInventory) session.getAttribute("LIST_INVEN_VIRTUAL");
                    if (rd != null) {
                        if (rd.getListInventory().size() > 0) {
                %>
                <div class=" box-container col">
                    <h3>Product will be add to the detail</h3>
                    <table border="1">
                        <thead>
                            <tr>
                                <th>No</th>
                                <th>ProductID</th>
                                <th>Quality</th>
                                <th>Quantity In Checking</th>
                                <th>Note</th>
                                <th>Quantity</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                int count = 1;
                                for (UserInventoryVirtual tm : rd.getListInventory().values()) {
                            %>
                        <form action="MainController" name="AVRSearch" onsubmit="AVRSearchForm();">
                            <tr>
                                <td>
                                    <%= count++%>
                                </td>
                                <td>
                                    <input class="w-90" type="hidden" name="productID" value="<%= tm.getProductID()%>" readonly=""/>
                                    <%= tm.getProductID()%>
                                </td>
                                <td>
                                    <input class="w-90" type="text" name="quality" value="<%= tm.getQuality()%>" required="" min="1"/>
                                </td>
                                <td>
                                    <input class="w-90" type="text" name="quantityInChecking" value="<%= tm.getQuantityInChecking()%>" required="" min="1"/>
                                </td>
                                <td>
                                    <input type="text" name="note" value="<%= tm.getNote()%>"/>
                                </td>
                                <td>
                                    <%= tm.getQuantity()%>
                                    <input type="hidden" name="quantity" value="<%= tm.getQuantity()%>"/>
                                </td>
                                <td >
                                    <div class="d-flex">
                                        <input type="hidden" name="action" value="UpdateInventoryVirtua" readonly=""/>
                                        <input type="submit" value="Update"/>
                                        <a class="a-button" href="MainController?action=RemoveInventoryVirtual&productID=<%= tm.getProductID()%>">Delete</a>
                                    </div>                                
                                </td>
                            </tr>
                        </form>
                        <%
                            }
                        %>
                        </tbody>
                    </table>
                    <%
                            }
                        }
                    %>
                </div>
            </div>
        </div>

        <%
            String error = (String) request.getAttribute("ERROR");
            if (error == null) {
                error = "";
            }
        %>
        <h2 class="error"><%=error%></h2>


        <form action="MainController" class="filter-container wi-85" style="margin: 0 auto">
            <input type="submit" name="action" value="Complete">
        </form>


        <%
            String errorAdd = (String) request.getAttribute("ERROR_ADD");
            if (errorAdd == null) {
                errorAdd = "";
            }
        %>
        <h2 class="error"><%=errorAdd%></h2>
        <script src="js/mycode.js"></script>
        <script src="https://code.iconify.design/2/2.2.1/iconify.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.5/dist/umd/popper.min.js" integrity="sha384-Xe+8cL9oJa6tN/veChSP7q+mnSPaj5Bcu9mPX5F5xIGE0DVittaqT5lorf0EI7Vk" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.min.js" integrity="sha384-kjU+l4N0Yf4ZOJErLsIcvOU2qSb74wXpOhqTvwVx3OElZRweTnQ6d31fXEoRD1Jy" crossorigin="anonymous"></script>



    </body>
</html>
