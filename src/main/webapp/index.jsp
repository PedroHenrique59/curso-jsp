<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

    <title>Curso JSP</title>

</head>

<body>

<div class="col-md-6 offset-md-3">
    <h1>Bem vindo ao Curso de JSP</h1>
</div>

<form class="row g-3" action="ServletLogin" method="post">

    <input type="hidden" value="<%= request.getParameter("url") %>" name="url">

    <div class="col-md-6 offset-md-3">
        <label class="form-label">Login</label>
        <input type="text" class="form-control" name="Login">
    </div>

    <div class="col-md-6 offset-md-3">
        <label class="form-label">Senha</label>
        <input type="password" class="form-control" name="Senha">
    </div>

    <div class="col-md-6 offset-md-3">
        <button type="submit" class="btn btn-primary">Acessar</button>
    </div>

</form>

<h4>${msg}</h4>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous">
</script>

</body>

</html>
