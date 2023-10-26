<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.Map, java.util.List, com.board.AdvertisementManager, com.board.Advertisement, java.lang.String" %>
<%@ page import="com.servletcallboard.CallboardServlet" %>
<%@ page import="java.awt.*" %>
<%@ page import="java.util.Objects" %>
<%! AdvertisementManager advertisementManager = new AdvertisementManager(); %>

<div class="mx-auto px-2 width-max">
    <ul class="list-group list-group-flush m-auto width-max">
        <% try {
            Map<String, List<Advertisement>> advertisements = (Map<String, List<Advertisement>>) request.getAttribute("advertisements");
            if (advertisements != null) {
                for (Map.Entry<String, List<Advertisement>> entry : advertisements.entrySet()) {
                    String title = entry.getKey();
                    List<Advertisement> ads = entry.getValue();
        %>
        <li class="list-group-item">
            <strong>Title: </strong> <span><%= title %></span><br>
            <%
                if (ads != null) {
                    for (Advertisement ad : ads) {
            %>
            <strong>Description: </strong><span><%= ad.getDescription() %><br>
            <strong>Price: </strong><span><%= ad.getPrice() %>
            <%
                    }
                }
                try {
                    String username = (String) request.getAttribute("username");
                    if (username != null) {
            %>
                <form method="post" action="<%=request.getContextPath()%>/action/delete">
                    <input type="hidden" id="title" name="title" value="<%= title %>" />
                    <button type="submit" class="btn btn-outline-danger">Delete</button>
                </form>
                    <%
                    }
                } catch (Exception e) {
                    e.printStackTrace();
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
