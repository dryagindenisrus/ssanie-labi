<form action="<%= request.getContextPath() %>/action/login" method="post">
  <div class="form-group">
    <label for="login">Login</label>
    <input type="text" class="form-control" name="username" id="login" aria-describedby="emailHelp" placeholder="Enter login">
  </div>
  <div class="form-group">
    <label for="password">Password</label>
    <input type="password" class="form-control" name="password" id="password" placeholder="Password">
    <%
      try {
        String isValid = (String) request.getAttribute("isValid");
        if (Objects.equals(isValid, "0")) {
    %>
    <small id="emailHelp" class="form-text text-muted"><span class="text-danger">Invalid login or password</span></small>
    <%
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    %>
  </div>
  <button type="submit" class="btn btn-primary my-3">Log In</button>
</form>