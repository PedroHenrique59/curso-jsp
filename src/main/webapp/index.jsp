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

<form class="row g-3 needs-validation" action="ServletLogin" method="post" novalidate>

    <input type="hidden" value="<%= request.getParameter("url") %>" name="url">

    <div class="col-md-6 offset-md-3">
        <label class="form-label">Login</label>
        <input type="text" class="form-control" name="Login" required="required">
        <div class="valid-feedback">
            Ok
        </div>
    </div>

    <div class="col-md-6 offset-md-3">
        <label class="form-label">Senha</label>
        <input type="password" class="form-control" name="Senha" required="required">
        <div class="valid-feedback">
            Ok
        </div>
    </div>

    <div class="col-md-6 offset-md-3">
        <button type="submit" class="btn btn-primary">Acessar</button>
    </div>

</form>

<div class="col-md-6 offset-md-3">
    <h4>${msg}</h4>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous">
</script>

<script type="text/javascript">
    // Example starter JavaScript for disabling form submissions if there are invalid fields
    (function () {
        'use strict'

        // Fetch all the forms we want to apply custom Bootstrap validation styles to
        var forms = document.querySelectorAll('.needs-validation')

        // Loop over them and prevent submission
        Array.prototype.slice.call(forms)
            .forEach(function (form) {
                form.addEventListener('submit', function (event) {
                    if (!form.checkValidity()) {
                        event.preventDefault()
                        event.stopPropagation()
                    }

                    form.classList.add('was-validated')
                }, false)
            })
    })()
</script>

</body>

</html>
