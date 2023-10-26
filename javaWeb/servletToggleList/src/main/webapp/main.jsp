<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<%@ include file="jsp/head.jsp" %>
<body>
<div class="wrapper">
    <div class="content">
        <%@ include file="jsp/header.jsp" %>
        <article class="main-article" style="background-image: url(<%= request.getContextPath() %>/static/img/dungeon.jpg);">
            <div class="main-article__content">
                <h2 class="main-article__header">Toggle list</h2>
                <%@ include file="jsp/list.jsp" %>
            </div>
        </article>
    </div>
</div>
</body>
</html>