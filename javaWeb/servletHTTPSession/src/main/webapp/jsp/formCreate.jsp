<form action="<%= request.getContextPath() %>/action/create" method="post">
  <div class="form-group">
    <label for="title">Title</label>
    <input type="text" class="form-control" name="title" id="title" aria-describedby="emailHelp" placeholder="Title">
  </div>
  <div class="form-group">
    <label for="description">Description</label>
    <textarea class="form-control" id="description" name="description" rows="4" cols="50">Description for card</textarea>
  </div>
  <div class="form-group">
    <label for="price">Title</label>
    <input type="number" class="form-control" name="price" id="price" placeholder="Price">
  </div>
  <button type="submit" class="btn btn-primary my-3">Create</button>
</form>