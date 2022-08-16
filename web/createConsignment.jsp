<%-- 
    Document   : createConsignment
    Created on : Jul 21, 2022, 8:43:21 PM
    Author     : GMT
--%>


<%@page import="inventoryStockeepr.BinDTO"%>
<%@page import="inventoryStockeepr.ProductDTO"%>
<%@page import="ListStockeeper.ChooseBinDTO"%>
<%@page import="ListStockeeper.InventoryTempDTO"%>
<%@page import="time.Timer"%>

<%@page import="java.util.List"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Consignment Page</title>
    </head>
    <body>
        <jsp:include page="stockeepHeader.jsp" />
        <!--        <a href="welcome.jsp">Home</a>
                <a href="MainController?action=ViewProductSK">Check-in</a>
                <a href="MainController?action=ViewIssueSK">Check-out</a>
                <a href="MainController?action=ViewBinSK&search=">Position</a>-->
        <%
            ProductDTO product = (ProductDTO) session.getAttribute("PRODUCT");
            List<BinDTO> listBin = (List<BinDTO>) session.getAttribute("LIST_BIN");
            List<BinDTO> listBinSum = (List<BinDTO>) session.getAttribute("LIST_BIN_SUM");
            List<ChooseBinDTO> listChoose = (List<ChooseBinDTO>) session.getAttribute("LIST_CHOOSE");
            InventoryTempDTO inven = (InventoryTempDTO) session.getAttribute("INVEN");
        %>
        <div class="box-container ">
            <h1 class="text-center">Product: <%=product.getName()%></h1>
            <table border="1">
                <thead>
                    <tr>
                        <th>Input Date</th>                    
                        <th>Warranty</th>
                        <th>Quantity</th>
                        <th>Quantity on hand</th>
                        <th>Status</th>
                        <th>Selected location</th>
                    </tr>
                </thead>
                <tbody>            
                    <%
                        if (inven != null) {
                    %>
                    <tr>
                        <td>
                            <%
                                Timer t = new Timer();
                            %>
                            <input type="hidden" name="inputDate" value="<%=t.timeNowUser()%>" readonly=""/>
                            <%=inven.getInputDate()%>
                        </td>
                        <td>
                            <input type="hidden" name="warranty"/>
                            <%=inven.getWarranty()%>
                        </td>
                        <td>
                            <input type="hidden" name="quantity" min="50" max="200" required=""/>
                            <%=inven.getQuantity()%>
                        </td>
                        <td>
                            <input type="hidden" name="quantityOnHand" min="10" max="100" required=""/>
                            <%=inven.getQuantityOnHand()%>
                        </td>
                        <td>
                            <%
                                if (inven.getNote() != null) {
                            %>
                            <input type="hidden" name="note"/>
                            <%=inven.getNote()%>
                            <%
                                }
                            %>
                        </td>
                        <td>
                            <%
                                if (listChoose != null) {
                                    for (ChooseBinDTO bin : listChoose) {
                            %>
                            <%=bin.getBinID()%></br>
                            <%
                                    }
                                }
                            %>
                        </td>
                    </tr>
                    <%
                    } else {
                    %>
                <form action="MainController" method="POST" name="saveInvenSK" onsubmit="showSaveInvenSK()">
                    <tr>
                        <td>
                            <%
                                Timer t = new Timer();
                            %>
                            <input type="date" name="inputDate" value="<%=t.timeNowUser()%>" readonly=""/>                        
                        </td>
                        <td>
                            <input type="text" name="warranty"/>
                        </td>
                        <td>
                            <input type="number" name="quantity" min="50" max="200" required=""/>
                        </td>
                        <td>
                            <input type="number" name="quantityOnHand" min="10" max="100" required=""/>
                        </td>
                        <td>

                        </td>
                        <td></td>
                    </tr>                

                    <%
                        }
                    %>
                    <input type="hidden" name="action" value="SaveInvenSK"/>
                    <input class="" type="submit" value="Create"/>
                </form>
                </tbody>
            </table>

        </div>
        <%
            if (inven != null) {
                if (listBinSum.size() > 0) {
        %>
        <div class="box-container">
            <table border="1">
                <thead>
                    <tr>
                        <th>#</th>
                        <th>BIN ID</th>
                        <th>AVAILABLE QUANTITY</th>
                        <th>ACTION</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        int count = 1;
                        for (BinDTO b : listBinSum) {
                    %>
                    <tr>
                        <td><%=count++%></td>
                        <td>
                            <input type="hidden" name="binID" value="<%=b.getBinID()%>"/>
                            <%=b.getBinID()%>
                        </td>
                        <td>
                            <input type="hidden" name="availableQuantity" value="<%=b.getCapacity() - b.getQuantity()%>"/>
                            <%=b.getCapacity() - b.getQuantity()%>
                        </td>
                        <td>
                            <a class="a-button" href="MainController?action=AddBinSK&binID=<%=b.getBinID()%>">Add</a>
                        </td>
                    </tr>
                    <%
                            }
                        }
                    %>
                </tbody>
            </table>
            <div class="filter-container w-90">
                <a class="a-button " href="MainController?action=CreateConsignmentSK">Save</a>

            </div>
        </div>
        <%
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
