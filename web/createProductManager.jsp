<%-- 
    Document   : createProductManager
    Created on : Jul 22, 2022, 12:39:02 PM
    Author     : 84348
--%>

<%@page import="user.UserProduct"%>
<%@page import="virtualSeller.ListProduct"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Product Page</title>
    </head>
    <body>
        <jsp:include page="managerHeader.jsp" />
        <div class="banner-container ">
            <h3>Product/ Create Product</h3>
        </div>
        <div  class="container" style="max-width: 90%">
            <div class="row ">
                <div class=" col-8" style="margin: 0 auto">
                    <form action="MainController" method="POST" class=" box-container" name="createProductManager" onsubmit="ShowCreateProductManager()">
                        <div class="container" >
                            <div class="row">
                                <div class="col"><input type="text" name="name" required="" placeholder="Name"/></div>
                                <div class="col "><input type="text" name="brand" required="" placeholder="Brand"/></div>
                            </div>
                            <div class="row ">
                                <div class="col"><input type="text" name="model" required="" placeholder="Model"/></div>
                                <div class="col "><input type="text" name="type" required="" placeholder="Type"/></div>
                            </div>
                            <div class="row">
                                <div class="col"><input type="number" step="0.01" min="20" name="width" required="" placeholder="Width"/></div>
                                <div class="col "><input type="number" step="0.01" min="1" name="depth" required="" placeholder="Depth"/></div>
                            </div>
                            <div class="row">
                                <div class="col"><input type="number" step="0.01" min="20" name="height" required="" placeholder="Height"/></div>
                                <div class="col "><input type="number" step="1" min="15" name="screenSize" required="" placeholder="Screen Size"/></div>
                            </div>
                            <div class="row" >
                                <div class="col d-flex justify-content-center align-items-center"><input type="checkbox" checked="true" value="true" name="voiceRemote" required=""/><span class="fw-bold" style="font-size: 30px"> Voice Remode</span></div>
                                <div class="col d-flex justify-content-center align-items-center" ><input type="checkbox" checked="true" value="true" name="bluetooth" required="" /> <span class="fw-bold" style="font-size: 30px"> Bluetooth</span></div>
                            </div>
                            <div class="row">
                                <div class="col"><input type="number" step="1" name="manufacturingDate" required="" placeholder="Manufacturing Date"/></div>
                                <div class="col"><input type="text" name="madeIn" placeholder="Made In" required=""/></div>
                            </div>
                            <div class="row">
                                <div class="col"></div>
                                <div class="col">
                                    <input type="hidden" name="action" value="CreateProductManager"/>
                                    <input type="submit" value="Create"/>
                                    <input type="reset" value="Reset"/>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>

                <%
                    ListProduct rd = (ListProduct) session.getAttribute("LIST_PRODUCT_SUCCESS_ADD");
                    if (rd != null) {
                        if (rd.getListProduct().size() > 0) {
                %>
                <div id="orders" class="col-4 box-container" style="padding: 10px 0; width: 100%">
                    <h2 class="text-center">Created Product</h2>
                    <table border="1" style="width: auto !important;">
                        <thead>
                            
                        </thead>
                        <tbody class="table-container tbody block ">
                            <tr  class="table-header">
                                <th>No</th>
                                <th>productID</th>
                                <th>Name</th>
                                <th>Quantity</th>
                            </tr>
                            <%
                                int count = 1;
                                for (UserProduct tm : rd.getListProduct().values()) {
                            %>
                        <form action="MainController" >
                            <tr>
                                <td>
                                    <%= count++%>
                                </td>
                                <td>
                                    <div style="margin: 10px"><%= tm.getProductID()%></div>
                                </td>
                                <td>
                                    <div style="margin: 10px"><%= tm.getName()%></div>
                                </td>
                                <td>
                                    <%= tm.getQuantity()%>
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

                    <%
                        String errorAdd = (String) request.getAttribute("ERROR_PRODUCT");
                        if (errorAdd == null) {
                            errorAdd = "";
                        }
                    %>
                    <h2 class="error"><%=errorAdd%></h2>

                </div>
            </div>
        </div>



        <script src="js/mycode.js"></script>
        <script src="https://code.iconify.design/2/2.2.1/iconify.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.5/dist/umd/popper.min.js" integrity="sha384-Xe+8cL9oJa6tN/veChSP7q+mnSPaj5Bcu9mPX5F5xIGE0DVittaqT5lorf0EI7Vk" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.min.js" integrity="sha384-kjU+l4N0Yf4ZOJErLsIcvOU2qSb74wXpOhqTvwVx3OElZRweTnQ6d31fXEoRD1Jy" crossorigin="anonymous"></script>



    </body>
</html>
