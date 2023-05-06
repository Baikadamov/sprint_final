<%@ page import="java.util.ArrayList" %>
<%@ page import="kz.biltlab.narxoz.db.News" %>
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
            <div class="row mt-3">
                <div class="col-12">
                    <%
                        ArrayList<News> news = (ArrayList<News>) request.getAttribute("news");
                        if(news!=null){
                            for(News n : news){
                    %>
                        <div class="p-5 mb-3" style="background-color: #dee1df;">
                            <a href="/news-details?id=<%=n.getId()%>">
                                <h3><%=n.getTitle()%></h3>
                            </a>
                            <p><%=n.getContent()%></p>
                            <p><%=n.getCategory().getName()%></p>
                            <p>
                                Posted by <strong><%=n.getUser().getFullName()%></strong>
                                 At <strong><%=n.getPostDate()%></strong>
                            </p>
                        </div>
                    <%
                            }
                        }
                    %>
                </div>
            </div>
        </div>
    </body>
</html>