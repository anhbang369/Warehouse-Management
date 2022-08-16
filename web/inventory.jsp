<%-- 
    Document   : inventory
    Created on : Jul 31, 2022, 6:02:26 PM
    Author     : quang
--%>

<%@page import="java.util.List"%>
<%@page import="inventoryAccountant.UserInventory"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css">
        <link rel="stylesheet" href="css/styles.css">
        <script src="js/mycode.js"></script>
        <title>Inventory Page</title>
    </head>
    <body>
        <jsp:include page="accountantHeader.jsp" />

        <div class="box-container filter-container">
            <form action="">
                <i class="fa-solid fa-filter" onclick="ShowDateReceipt()"></i>
            </form>
            <div class="show_date_receipt box-container hide-show-fiter" id="showDateReceipt" style="display: none">
                <form action="MainController" name="SRIdSearch" onsubmit="SRIdSearchForm();">
                    From <input type="date" name="searchInventory"/>
                    To <input type="date" name="searchInventoryM" />
                    <input type="hidden" name="action" value="SearchInventory"/>
                    <input type="submit" value="Search"/>
                </form>
            </div>

            <div>
                <i class="fa-solid fa-sort" onclick="ShowSort()"></i>

            </div>

            <div id="sortLabel" class="show_date_receipt box-container hide-show-fiter" style="display: none">
                <form action="MainController" name="SIVTRSearch" onsubmit="SIVTRSearchForm();">
                    <label>Sort by: </label>
                    <select name="search">
                        <option value="1">Date old</option>
                        <option value="2">Date new</option>
                    </select>
                    <input type="hidden" name="action" value="SortInventory">
                    <input type="submit" name="Sort">
                </form>
            </div>
            <form action="MainController" name="SRISearch" onsubmit="SRISearchForm();">
                <input type="text" name="productID" placeholder="ProductID"/>
                <input type="text" name="name" placeholder="Product Name" />
                <input type="hidden" name="action" value="SearchInventoryAlpha"/>
                <input type="submit" value="Search"/>
            </form>
        </div>
        <%
            List<UserInventory> inventory = (List<UserInventory>) session.getAttribute("LIST_INVENTORY");
            if (inventory != null) {
                if (inventory.size() > 0) {
        %>
        <div class="outer-wrapper">
            <div class="table-wrapper">
                <table border="1">
                    <thead>
                    <th>productID</th>
                    <th>Name</th>
                    <th>Checking Date</th>
                    <th>quantity In Checking</th>
                    <th>Quantity</th>
                    <th>quality</th>
                    <th>Deviant</th>
                    <th>Note</th>
                    </thead>
                    <tbody >
                        <%
                            for (UserInventory rc : inventory) {
                        %>
                        <tr>
                            <td>
                                <%= rc.getProductID()%>
                            </td>
                            <td>
                                <%= rc.getName()%>
                            </td>
                            <td>
                                <%= rc.getCheckingDate()%>
                            </td>
                            <td>
                                <%= rc.getQuantityInChecking()%>
                            </td>
                            <td>
                                <%= rc.getQuantity()%>
                            </td>
                            <td>
                                <%= rc.getQuality()%>
                            </td>
                            <%
                                String isMax;
                                float a = (float) (rc.getQuantity() - rc.getQuantityInChecking()) / rc.getQuantity();
                                float b = (a * 100);

                                if (b == 0) {
                                    isMax = "0";
                                } else {
                                    String source = String.valueOf(b);
                                    char firstChar = source.charAt(0);
                                    String first = String.valueOf(firstChar);
                                    char secondChar = source.charAt(1);
                                    String second = String.valueOf(secondChar);
                                    char thirdChar = source.charAt(2);
                                    String third = String.valueOf(thirdChar);
                                    char lastChar = source.charAt(3);
                                    String last = String.valueOf(lastChar);
                                    isMax = first + second + third + last;
                                }

                            %>
                            <td>
                                <%= isMax + "%"%>
                            </td>
                            <%
                                String k;
                                if (rc.getNote() == null) {
                                    k = "";
                                } else {
                                    k = rc.getNote();
                                }
                            %>
                            <td>
                                <%= k%>
                            </td>
                    <input type="hidden" name="productID" value="<%= rc.getProductID()%>" readonly=""/>
                    <input type="hidden" name="name" value="<%= rc.getName()%>" readonly=""/>
                    <input type="hidden" name="checkingDate" value="<%= rc.getCheckingDate()%>" readonly=""/>
                    <input type="hidden" name="quantityInChecking" value="<%= rc.getQuantityInChecking()%>" readonly=""/>
                    <input type="hidden" name="quantity" value="<%= rc.getQuantity()%>" readonly=""/>
                    <input type="hidden" name="quality" value="<%= rc.getQuality()%>" readonly=""/>
                    <input type="hidden" name="deviant" value="<%= isMax%>%" readonly=""/>
                    <input type="hidden" name="note" value="<%= rc.getNote()%>" readonly=""/>
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
        %>

        <%
            String error = (String) request.getAttribute("ERROR");
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