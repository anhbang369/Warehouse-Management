<%-- 
    Document   : listProductManager
    Created on : Jul 22, 2022, 9:53:57 AM
    Author     : 84348
--%>

<%@page import="user.UserDTO"%>
<%@page import="productManager.UserProducNotiManager"%>
<%@page import="virtualSeller.ListProductNotiManager"%>
<%@page import="user.UserNotify"%>
<%@page import="virtual.ListNotify"%>
<%@page import="user.UserProduct"%>
<%@page import="user.UserProductNotFull"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <title>Product Page</title>
    </head>
    <body>
        <%
            UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
            String p = null;
            if ("MN".equals(loginUser.getRole())) {
                p = "managerHeader.jsp";
            } else if ("AC".equals(loginUser.getRole())) {
                p = "accountantHeader.jsp";
            } else if ("SK".equals(loginUser.getRole())) {
                p = "stockeepHeader.jsp";
            } else if ("SL".equals(loginUser.getRole())) {
                p = "sellerHeader.jsp";
            }
        %>
        <jsp:include page="<%=p%>"/>
        <div class="box-container grid" style="">
            <%                ListNotify rd = (ListNotify) session.getAttribute("LIST_NOTIFY");
                if (rd != null) {
                    if (rd.getListNotify().size() > 0) {
            %>

            <div class="notification_head hide-show-fiter box-container border" id="showValue" style="display: none">
                <header class="head_notify">
                    <h3>
                        Warning
                    </h3>
                </header>
                <%
                    for (UserNotify tm : rd.getListNotify().values()) {
                %>
                <ul>
                    <li class="notify_list text-left">
                        <span > Product <%= tm.getName()%> (ID: <%= tm.getProductID()%>) is almost out of stock. Please consider enter more.</span>
                    </li>

                </ul>
                <%
                    }
                %>


            </div>
            <%
                    }
                }
            %>
            <%
                int b;
                if (rd != null) {
                    b = rd.getListNotify().size();
                } else {
                    b = 0;
                }
            %>
            <div class="left-item ">
                <a href="#" style="text-decoration:none;" class="">
                    <span oncl class="iconify icon-border" style="background-color: white;color: #F7EAB0;" data-icon="el:laptop-alt"  onclick="ShowNotify()"></span>
                </a>
                <div class="absolute " style="top: 30px;right: 0; text-align: end;font-size: 20px">
                    <div style="color: #F7EAB0;font-weight: bold" ><%= b%></div>
                    <div style="font-weight:  bold">Television</div>
                    <div >UNDERSTOCK</div>
                </div>
            </div>


            <%

                ListProductNotiManager rdy = (ListProductNotiManager) session.getAttribute("LIST_ZERO_ONE");
                if (rdy != null) {
                    if (rdy.getListProduct().size() > 0) {
            %>

            <div class="notification_head1 hide-show-fiter box-container border" id="showValue1" style="display: none">
                <header class="head_notify">
                    <h3>
                        Warning
                    </h3>
                </header>
                <%
                    for (UserProducNotiManager tmm : rdy.getListProduct().values()) {
                %>
                <ul>
                    <li class="notify_list text-left">
                        <span> Product <%= tmm.getName()%> (ID: <%= tmm.getProductID()%>) is almost out of stock. Please consider enter more.</span>
                    </li>

                </ul>
                <%
                    }
                %>


            </div>
            <%
                    }
                }
            %>
            <%
                int a;
                if (rdy != null) {
                    a = rdy.getListProduct().size();
                } else {
                    a = 0;
                }
            %>
            <div class="right-item ">

                <a href="#" style="text-decoration:none;">
                    <span class="iconify icon-border" style="background-color: white;color: #FCBAA4;" data-icon="el:laptop-alt" value="show" onclick="ShowNotify1()"></span>
                </a>
                <div class="absolute" style="top: 30px;left: 0; text-align: start;font-size: 20px">
                    <div style="color: #FCBAA4;font-weight: bold" ><%= a%></div>
                    <div style="font-weight:  bold">Television</div>
                    <div >OUT OF STOCK</div>
                </div>

            </div>


        </div>
        <form action="MainController" class="box-container filter-container " name="searchProductMN" onsubmit="showSearchProductMN()">
            <input type="text" name="name" placeholder="Name">.
            <input type="hidden" name="action" value="SearchProductManager">
            <input type="submit" value="Search"/>
        </form>

        <%
            List<UserProductNotFull> product = (List<UserProductNotFull>) session.getAttribute("LIST_PRODUCT_NOT_FULL_MANAGER");
            if (product != null) {
                if (product.size() > 0) {
        %>
        <div class="outer-wrapper">
            <div class="table-wrapper"  style="height: 350px">
                <form action="MainController">
                    <table border="1" class="table">
                        <thead>
                        <th>No</th>
                        <th>Product ID</th>
                        <th>Name</th>
                        <th>Quantity</th>
                        <th>Action</th>
                        </thead>
                        <tbody >

                            <%
                                int count = 1;
                                for (UserProductNotFull rc : product) {

                            %>
                        <form  action="MainController" name="viewProductMN"  onsubmit="ShowVeiwProductMN()">
                            <tr>
                                <td>
                                    <%= count++%>
                                </td>
                                <td>
                                    <input type="hidden" name="productID" value="<%= rc.getProductID()%>">
                                    <%= rc.getProductID()%>
                                </td>
                                <td>
                                    <input type="hidden" name="name" value="<%= rc.getName()%>">
                                    <%= rc.getName()%>
                                </td>
                                <td>
                                    <input type="hidden" name="quantity" value="<%= rc.getQuantity()%>">
                                    <%= rc.getQuantity()%>
                                </td>
                                <td>
                                    <input type="hidden" name="action" value="ViewProductManager">
                                    <input type="submit"  value="View"/>
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
                </form>
            </div>
        </div>


        <%
            List<UserProduct> productF = (List<UserProduct>) session.getAttribute("LIST_PRODUCT_FULL");
            if (productF != null) {
                if (productF.size() > 0) {
        %>
        <div class="box-container hide-show-fiter" id='productDetail' style="width: 60%">

            <%
                int count = 1;
                for (UserProduct rc : productF) {

            %>
            <form action="MainController" name="updateProductManager" onclick="updateProductManager()" id="productDetail">
                <div class="container" style="max-width: 70%" >
                    <div class="row d-flex justify-content-between" >
                        <div class="col align-items-center d-flex justify-content-between" >
                            Product ID: <div><%= rc.getProductID()%></div>
                            <input type="hidden" name="productID" value="<%= rc.getProductID()%>">
                        </div>
                        <div class="col align-items-center d-flex justify-content-between">
                            Name: <input type="text" name="name" value="<%= rc.getName()%>">
                        </div>
                    </div>
                    <div class="row">
                        <div class="col align-items-center d-flex justify-content-between">
                            Brand: <div><%= rc.getBrand()%></div>
                        </div>
                        <div class="col align-items-center d-flex justify-content-between">
                            Model: <div><%= rc.getModel()%></div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col align-items-center d-flex justify-content-between">
                            Type: <input type="text" name="type" value="<%= rc.getType()%>">
                        </div>
                        <div class="col align-items-center d-flex justify-content-between d-flex justify-content-between">
                            Made In: <input type="text" name="madein" value="<%= rc.getMadeIn()%>">
                        </div>
                    </div>
                    <div class="row">
                        <div class="col align-items-center d-flex justify-content-between">   
                            Voice Remote: 
                            <select name="voice">
                                <option selected="true" disabled="disabled" ><%= rc.getBluetooth()%></option>
                                <%
                                    if (!rc.getBluetooth().equalsIgnoreCase("Have")) {
                                %>
                                <option value="Have">Have</option>
                                <%
                                    }
                                %>
                                <%
                                    if (!rc.getBluetooth().equalsIgnoreCase("Not Have")) {
                                %>
                                <option value="Not Have">Not Have</option>
                                <%
                                    }
                                %>
                            </select>
                        </div>
                        <div class="col align-items-center d-flex justify-content-between"> 
                            Bluetooth: 
                            <select name="blue">
                                <option selected="true" disabled="disabled" ><%= rc.getBluetooth()%></option>
                                <%
                                    if (!rc.getBluetooth().equalsIgnoreCase("Have")) {
                                %>
                                <option value="Have">Have</option>
                                <%
                                    }
                                %>
                                <%
                                    if (!rc.getBluetooth().equalsIgnoreCase("Not Have")) {
                                %>
                                <option value="Not Have">Not Have</option>
                                <%
                                    }
                                %>
                            </select>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col align-items-center d-flex justify-content-between">
                            Screen size: <input type="number" name="screen" value="<%= rc.getScreenSize()%>" step="0.1" min="15">
                        </div>

                        <div class="col align-items-center d-flex justify-content-between">
                            Height: <input type="text" name="geight" value="<%= rc.getHeight()%>" step="0.1" min="20">
                        </div>
                    </div>
                    <div class="row">
                        <div class="col align-items-center d-flex justify-content-between">

                            Width: <input type="number" name="width" value="<%= rc.getWidth()%>" step="0.1" min="20">
                        </div>
                        <div class="col align-items-center d-flex justify-content-between">

                            Manufactoring date:<input type="text" name="manu" value="<%= rc.getManufacturingDate()%>">
                        </div>
                    </div>
                    <div class="row">
                        <div class="col align-items-center d-flex justify-content-between">
                            Depth: <input type="number" name="depth" value="<%= rc.getDepth()%>" step="0.1" min="1">
                        </div>
                        <div class="col align-items-center d-flex justify-content-between">
                            Status:<input type="hidden" name="status" value="<%= rc.getStatus()%>">
                        </div>
                    </div>
                    <%
                        if ("MN".equals(loginUser.getRole())) {
                    %>
                    <input type="hidden" name="action" value="UpdateProductManager">
                    <input type="submit" value="Update">
                    <%
                    } else if ("AC".equals(loginUser.getRole())) {
                    %>
                    <%
                    } else if ("SK".equals(loginUser.getRole())) {
                    %>
                    <%
                    } else if ("SL".equals(loginUser.getRole())) {
                    %>
                    <%
                        }
                    %>

                </div>
                <input type="button" value="Close" onclick="ShowProductDetail()">
            </form>
            <%
                }
            %>
            <%
                    }
                }
            %>
        </div>
        <script src="js/mycode.js"></script>
        <script src="https://code.iconify.design/2/2.2.1/iconify.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.5/dist/umd/popper.min.js" integrity="sha384-Xe+8cL9oJa6tN/veChSP7q+mnSPaj5Bcu9mPX5F5xIGE0DVittaqT5lorf0EI7Vk" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.min.js" integrity="sha384-kjU+l4N0Yf4ZOJErLsIcvOU2qSb74wXpOhqTvwVx3OElZRweTnQ6d31fXEoRD1Jy" crossorigin="anonymous"></script>

    </body>
</html>
