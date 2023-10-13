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