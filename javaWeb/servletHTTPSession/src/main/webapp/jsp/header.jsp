<%@ page import="java.util.Objects" %>


<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid w-100 width-max">
        <a class="header-link" href="<%= request.getContextPath() %>">
            <h4 class="text-primary">Java Servlet</h4>
        </a>
        <div class="ml-2" id="navbarNavAltMarkup">
            <div class="navbar-nav">
                <% try {
                    String username = (String) request.getAttribute("username");
                    if (username != null) {
                %>
                <a href="<%=request.getContextPath()%>/create" class="btn btn-outline-primary">Create</a>
                <a class="nav-link text-warning" href="<%=request.getContextPath()%>/action/logout">Logout</a>
                <a class=" nav-link disabled"><span class="font-weight-bold"><%=username%></span></a>
                <%
                } else {
                %>
                    <a class="nav-link <%=request.getContextPath().contains("login")? "selected": ""%>" href="<%=request.getContextPath()%>/login">Login</a>
                <%
                    }
                } catch (Exception e) {
                %>
                <p>Error: <%= e.getMessage() %></p>
                <%
                    }
                %>
            </div>
        </div>
    </div>
</nav>