<%-- 
    Document   : issueSuccess
    Created on : Jul 8, 2022, 8:17:58 PM
    Author     : 84348
--%>

<%@page import="issueAccountant.UserIssueFull"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <a href="issue.jsp"></a>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Issue Create Success Page</title>
    </head>
    <body>
        <jsp:include page="accountantHeader.jsp" />

        <form action="MainController">
            <%
                List<UserIssueFull> list = (List<UserIssueFull>) request.getAttribute("LIST_ISSUE_FULLL");
                if (list != null) {
                    if (list.size() > 0) {

            %>

            <table border="1">
                <thead>
                    <tr>
                        <th>Order Detail ID</th>
                        <th>Product ID</th>
                        <th>Quantity</th>
                        <th>Order ID</th>
                    </tr>
                </thead>
                <tbody>
                    <%                    for (UserIssueFull elem : list) {


                    %>
                    <div class="box-container">
                    <div class="container">
                        <div class="row">
                            <div class="col flex-between">
                                AccountantID: <div><%=elem.getAccountantID()%></div>
                            </div>
                            <div class="col flex-between"> 
                                SellerID: <div> <%=elem.getSellerID()%></div>
                            </div>
                            
                        </div>
                        <div class="row">
                            <div class="col flex-between">CustomerName:<div> <%=elem.getCustomerName()%></div></div>
                            <div class="col flex-between"> Address:<div> <%=elem.getAddress()%></div></div>
                        </div>
                        <div class="row">
                            <div class="col flex-between">PhoneNumber <div><%=elem.getPhoneNumber()%></div></div>
                            <div class="col flex-between">DeliveryDate: <div><%=elem.getDeliveryDate()%></div></div>
                        </div>
                        <div class="row">
                            <div class="col flex-between">
                                Date: <div><%=elem.getDateP()%></div>
                            </div>
                            <div class="col flex-between">
                                Note: <div><%=elem.getNote()%></div>
                            </div>
                        </div>
                    </div>
                </div>
                    <%
                            break;
                        }
                    %>

                    <%
                        for (UserIssueFull rc : list) {

                    %>

                    <tr>
                        <td>
                            <%= rc.getOrderDetailID()%>
                        </td>
                        <td>
                            <%= rc.getProductID()%>
                        </td>
                        <td>
                            <%= rc.getQuantity()%>
                        </td>
                        <td>
                            <%= rc.getOrderID()%>
                        </td>

                <input type="hidden" name="issueID" value="<%= rc.getIssueID()%>" readonly=""/>
                <input type="hidden" name="note" value="<%= rc.getNote()%>" readonly=""/>
                <input type="hidden" name="accountantID" value="<%= rc.getAccountantID()%>" readonly=""/>
                <input type="hidden" name="sellerID" value="<%= rc.getSellerID()%>" readonly=""/>
                <input type="hidden" name="DateP" value="<%= rc.getDateP()%>" readonly=""/>
                <input type="hidden" name="customerName" value="<%= rc.getCustomerName()%>" readonly=""/>
                <input type="hidden" name="address" value="<%= rc.getAddress()%>" readonly=""/>
                <input type="hidden" name="phoneNumber" value="<%= rc.getPhoneNumber()%>" readonly=""/>
                <input type="hidden" name="deliveryDate" value="<%= rc.getDeliveryDate()%>" readonly=""/>
                </tr>   

                </tbody>
                <%

                    }
                %>
            </table>



            <%
                    }
                }
            %>
            

            <div class=" filter-container w-90">
<!--                <input type="text" name="notee" placeholder="Enter Conclusion">
                <input type="submit" name="action" value="Update Issue"/>-->
                <!--                <input type="submit" value="Conclusion"/>-->
                <input type="submit" name="action" value="ExportIssue">
            </div>

        </form>
        <script src="js/mycode.js"></script>
        <script src="https://code.iconify.design/2/2.2.1/iconify.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.5/dist/umd/popper.min.js" integrity="sha384-Xe+8cL9oJa6tN/veChSP7q+mnSPaj5Bcu9mPX5F5xIGE0DVittaqT5lorf0EI7Vk" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.min.js" integrity="sha384-kjU+l4N0Yf4ZOJErLsIcvOU2qSb74wXpOhqTvwVx3OElZRweTnQ6d31fXEoRD1Jy" crossorigin="anonymous"></script>

    </body>
</html>
