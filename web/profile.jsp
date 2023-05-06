<%@ page import="java.util.ArrayList" %>
<%@ page import="kz.biltlab.narxoz.db.Tasks" %>
<%@ page import="kz.biltlab.narxoz.db.Items" %>
<%@ page import="kz.biltlab.narxoz.db.Users" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="ru">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Демо Bootstrap</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
</head>
<body>
<%@include file="navbar.jsp" %>
<div class="container mt-5">
    <div class="row">
        <div class="col-12 mx-auto">
            <h3>Profile Page </h3>
            <h4>HELLO <%=currentUser!=null?currentUser.getFullName():""%></h4>
        </div>
        <div class="mt-5 col-6">
            <form action="/updateprofile" method="post">
                <div class="mt-3 ">
                    <input type="hidden" name="user_email" class="form-control" value="<%=currentUser!=null?currentUser.getEmail():""%>">
                    <label >Name</label>
                    <input type="text" name="user_name" class="form-control" value="<%=currentUser!=null?currentUser.getFullName():""%>">
                </div>
                <div class="mt-3 ">
                    <label >Password</label>
                    <input type="text" name="user_password" class="form-control" value="<%=currentUser!=null?currentUser.getPassword():""%>">
                </div>
                <div class="mt-3 ">
                    <button type="submit" class="btn btn-dark">Update</button>
                </div>
            </form>

        </div>
    </div>
</div>

</body>
</html>