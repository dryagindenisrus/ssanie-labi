<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<header class="main-header">
    <div class="layers">
        <div class="layer__header">
            <div class="layers__caption">Welcome to Parallax</div>
            <div class="layers__title">Fairy Forest</div>
        </div>
        <div class="layer layers__base" style="background-image: url(<%= request.getContextPath() %>/static/img/layer-base.png);"></div>
        <div class="layer layers__middle" style="background-image: url(<%= request.getContextPath() %>/static/img/layer-middle.png);"></div>
        <div class="layer layers__front" style="background-image: url(<%= request.getContextPath() %>/static/img/layer-front.png);"></div>
    </div>
</header>
