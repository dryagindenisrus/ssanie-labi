<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.Map, java.util.ArrayList,com.servletbook.Notebook,java.lang.String" %>
<%@ page import="com.servletbook.SimpleBookServlet" %>
<%@ page import="java.awt.*" %>
<%@ page import="java.util.Objects" %>
<%!  Notebook noteBookModel = new Notebook(); %>

<form class="px-2 width-max mx-auto" action="<%= request.getContextPath() %>/action/search" method="post">
    <div class="form-group">
        <label for="search">Name:</label>
        <input class="form-control" type="text" id="search" name="search" required placeholder="Enter name for search">
        <small id="searchHelp" class="form-text text-muted">Enter name for adding telephone</small>
    </div>
    <br>
    <button onclick="location.href = <%= request.getContextPath() %>" type="submit" class="btn btn-primary">Submit</button>
</form>

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