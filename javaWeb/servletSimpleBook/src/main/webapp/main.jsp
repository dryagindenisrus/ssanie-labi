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
        <a class="header-link" href="<%= request.getContextPath() %>">
            <h4 class="text-primary">Java Servlet</h4>
        </a>
    </div>
</nav>
<main>
    <div class="mx-auto px-2 width-max">
        <ul class="list-group list-group-flush m-auto width-max">
            <% try {
                Map<String, ArrayList<String>> notebook = (Map<String, ArrayList<String>>) request.getAttribute("notes");
                if (notebook != null) {
                    for (Map.Entry<String, ArrayList<String>> entry : notebook.entrySet()) {
                        String key = entry.getKey();
                        ArrayList<String> value = entry.getValue();
            %>
                <li class="list-group-item">
                    <strong>Name:</strong> <span><%= key %></span><br>
                    <strong>Phones:</strong>
                    <%
                        if (value != null) {
                            for (String item : value) {
                    %>
                    <span>
                        <%= item + ", " %>
                    </span>
                    <%
                            }
                        }
                    %>
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
            <small id="nameHelp" class="form-text text-muted">Enter name for adding telephone</small>
        </div>
        <div class="form-group">
            <label for="telephone">Value:</label>
            <input class="form-control" type="text" id="telephone" name="telephone" required placeholder="Enter telephone">
            <small id="telHelp" class="form-text text-muted">Telephone will be add</small>
        </div>
        <br>
        <button onclick="location.href = <%= request.getContextPath() %>" type="submit" class="btn btn-primary">Submit</button>
        <a href="<%= request.getContextPath() %>/action/reset" class="btn btn-danger ml-4">Reset</a>
    </form>

</main>
</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</html>
