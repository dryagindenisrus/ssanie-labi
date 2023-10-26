<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.Map, java.util.List" %>
<%@ page import="com.list.ToggleList" %>
<div class="mx-auto px-2 width-max">
    <ul class="list-group list-group-flush m-auto width-max list-styles-custom  ">
        <%
            Map<String, List<String>> lists = (Map<String, List<String>>) request.getAttribute("toggelList");
            if (lists != null && !lists.isEmpty()) {
                for (Map.Entry<String, List<String>> entry : lists.entrySet()) {
                    String key = entry.getKey();
                    List<String> value = entry.getValue();
        %>
        <li>
            <strong><%= key %></strong>
            <button class="toggle-button button-64" role="button" onclick="toggleList('<%= key %>')"><span class="text">Toggle</span></button>
            <button class="toggle-button button-64" role="button" onclick="anotherView('<%= key %>')"><span class="text">Another view</span></button>
            <ul class="sublist-styles hidden" id="<%= key %>">
                <%
                    if (value != null) {
                        for (String item : value) {
                %>
                <li><%= item %></li>
                <%
                        }
                    }
                %>
            </ul>
        </li>
        <%
            }
        } else {
        %>
        <li class="list-group-item">
            <p>Nothing there's</p>
        </li>
        <%
            }
        %>
    </ul>
</div>
