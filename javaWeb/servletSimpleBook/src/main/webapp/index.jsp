<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.Map, java.util.ArrayList,com.servletbook.Notebook,java.lang.String" %>
<%@ page import="com.servletbook.SimpleBookServlet" %>
<%@ page import="java.awt.*" %>
<%@ page import="java.util.Objects" %>
<%!  Notebook noteBookModel = new Notebook(); %>


<!DOCTYPE html>
<html lang="en">
<head>
    <title>Simple Book</title>
    <meta charset="UTF-8">
    <link rel="icon" type="image/x-icon" href="<%= request.getContextPath() %>/static/favicon.ico">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/static/css/style.css">
</head>
<body>
<nav class="navbar navbar-light bg-light px-2">
    <div class="mx-auto w-100 width-max">
        <h4 class="text-primary">Java Servlet</h4>
    </div>
</nav>
<main>
    <div class="mx-auto px-2 width-max">
        <ul class="list-group list-group-flush m-auto width-max">
            <% try {
                Map<String, ArrayList<String>> notebook = (Map<String, ArrayList<String>>)request.getAttribute("notes");
                if (notebook != null) {
                    for (Map.Entry<String, ArrayList<String>> entry : notebook.entrySet()) {
            %>
                <li class="list-group-item">
                    <p>Name: <%= entry.getKey() %></p>
                    <p>Phones: <%= entry.getValue() %></p>
                </li>
            <%
                }
            } else {
            %>
            <p>Nothing there's</p>
            <%
                }
            } catch (Exception e) {
            %>
            <p>Error: <%= e.getMessage() %></p>
            <%
                }
            %>
        </ul>
    </div>

    <form class="px-2 width-max mx-auto" action="<%= request.getContextPath() %>/action/add" method="post">
        <div class="form-group">
            <label for="name">Name:</label>
            <input class="form-control" type="text" id="name" name="name" required placeholder="Enter name">
            <small id="nameHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
        </div>
        <div class="form-group">
            <label for="tel">Value:</label>
            <input class="form-control" type="text" id="tel" name="Value" required placeholder="Enter name">
            <small id="telHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
        </div>
        <br>
        <button type="submit" class="btn btn-primary">Submit</button>
    </form>
</main>
<footer class="footer bg-light py-2">
    <p class="text-center text-muted">&#169 2023 Dryagin Denis</p>
</footer>
</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</html>
