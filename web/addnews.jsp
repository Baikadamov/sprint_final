<%@ page import="java.util.ArrayList" %>
<%@ page import="kz.biltlab.narxoz.db.News" %>
<%@ page import="kz.biltlab.narxoz.db.Category" %>
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
        <%@include file="navbar.jsp"%>
        <div class="container mt-3">
            <div class="row">
                <div class="col-6 mx-auto">
                    <form action="/add-news-page" method="post">
                        <div class="row">
                            <div class="col-12">
                                <label>
                                    TITLE :
                                </label>
                            </div>
                        </div>
                        <div class="row mt-2">
                            <div class="col-12">
                                <input type="text" class="form-control" name="title" required placeholder="Insert title:">
                            </div>
                        </div>
                        <div class="row mt-3">
                            <div class="col-12">
                                <label>
                                    CONTENT :
                                </label>
                            </div>
                        </div>
                        <div class="row mt-2">
                            <div class="col-12">
                                <textarea class="form-control" name="content" placeholder="Insert content:" required rows="10"></textarea>
                            </div>
                        </div>
                        <div class="row mt-3">
                            <div class="col-12">
                                <label>AUTHOR : </label>
                            </div>
                        </div>
                        <div class="row mt-2">
                            <div class="col-12">
                                <select class="form-select" name="category">
                                    <%
                                        ArrayList<Category> categories = (ArrayList<Category>) request.getAttribute("category");
                                        if(categories!=null){
                                            for(Category category : categories){
                                    %>
                                    <option value="<%=category.getId()%>"><%=category.getName() %></option>
                                    <%
                                            }
                                        }
                                    %>
                                </select>
                            </div>
                        </div>
                        <div class="row mt-3">
                            <div class="col-12">
                                <button class="btn btn-success">ADD POST</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>