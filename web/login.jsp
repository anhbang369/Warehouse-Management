<%-- 
    Document   : login
    Created on : Jun 16, 2022, 4:20:57 PM
    Author     : 84348
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
        <link href="https://fonts.googleapis.com/css?family=Poppins" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css">
        <link rel="stylesheet" href="css/styles.css">
    </head>
    <body class="form-container">
        <%
            String error = (String) request.getAttribute("ERROR");
            if (error == null) {
                error = "";
            }
        %>
        <div class="login-form form-container">
            <div class="">
                <div>
                    <h3>USER LOGIN</h3>
                </div>
                <form action="MainController" method="POST" >
                    <i class="fa-regular fa-circle-user"></i>
                    <input  type="text" name="accountID" required="" placeholder="username"></br>
                    <i class="fa-solid fa-lock"></i>
                    <input type="password" name="password" required="" placeholder="password"></br>
                    <p class="error"><%=error%></p>
                    <i class="iconify" data-icon="fluent:swipe-right-24-filled"></i>
                    <input type="submit" name="action" value="Login">
                    <input type="reset" name="Reset">

                </form>
            </div>
        </div>

        
        <script src="js/mycode.js"></script>
        <script src="https://code.iconify.design/2/2.2.1/iconify.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.5/dist/umd/popper.min.js" integrity="sha384-Xe+8cL9oJa6tN/veChSP7q+mnSPaj5Bcu9mPX5F5xIGE0DVittaqT5lorf0EI7Vk" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.min.js" integrity="sha384-kjU+l4N0Yf4ZOJErLsIcvOU2qSb74wXpOhqTvwVx3OElZRweTnQ6d31fXEoRD1Jy" crossorigin="anonymous"></script>

    </body>
</html>
