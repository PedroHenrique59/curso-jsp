<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">

<jsp:include page="head.jsp"/>

<body>

<jsp:include page="theme-loader.jsp"/>

<!-- Pre-loader end -->
<div id="pcoded" class="pcoded">
    <div class="pcoded-overlay-box"></div>
    <div class="pcoded-container navbar-wrapper">

        <jsp:include page="navbar.jsp"/>

        <div class="pcoded-main-container">
            <div class="pcoded-wrapper">

                <jsp:include page="navbarmainmenu.jsp"/>

                <div class="pcoded-content">
                    <!-- Page-header start -->

                    <jsp:include page="pageheader.jsp"/>

                    <!-- Page-header end -->

                    <div class="pcoded-inner-content">
                        <!-- Main-body start -->
                        <div class="main-body">
                            <div class="page-wrapper">
                                <!-- Page-body start -->
                                <div class="page-body">

                                    <div class="row">
                                        <div class="col-sm-12">
                                            <!-- Basic Form Inputs card start -->
                                            <div class="card">

                                                <div class="card-block">

                                                    <h4 class="sub-title">Cadastro de Usuário</h4>

                                                    <form class="form-material" action="ServletCadastro" method="post"
                                                          id="formCadastro">

                                                        <div class="form-group form-default">
                                                            <input type="text" name="id" id="id"
                                                                   value="${modelLogin.id}" class="form-control"
                                                                   readonly="readonly">
                                                            <span class="form-bar"></span>
                                                            <label class="float-label">ID</label>
                                                        </div>
                                                        <div class="form-group form-default">
                                                            <input type="text" name="nome" id="nome"
                                                                   value="${modelLogin.nome}"
                                                                   class="form-control" required="required">
                                                            <span class="form-bar"></span>
                                                            <label class="float-label">Nome</label>
                                                        </div>
                                                        <div class="form-group form-default">
                                                            <input type="email" name="email" id="email"
                                                                   value="${modelLogin.email}"
                                                                   class="form-control" required="required">
                                                            <span class="form-bar"></span>
                                                            <label class="float-label">E-mail</label>
                                                        </div>
                                                        <div class="form-group form-default">
                                                            <input type="text" name="login" id="login"
                                                                   value="${modelLogin.login}"
                                                                   class="form-control" required="required">
                                                            <span class="form-bar"></span>
                                                            <label class="float-label">Login</label>
                                                        </div>
                                                        <div class="form-group form-default">
                                                            <input type="password" name="senha" id="senha"
                                                                   value="${modelLogin.senha}"
                                                                   class="form-control" required="required">
                                                            <span class="form-bar"></span>
                                                            <label class="float-label">Senha</label>
                                                        </div>

                                                        <button class="btn btn-primary waves-effect waves-light"
                                                                type="button"
                                                                onclick="limparForm();">Novo
                                                        </button>

                                                        <button class="btn btn-primary waves-effect waves-light">
                                                            Salvar
                                                        </button>

                                                        <button class="btn btn-primary waves-effect waves-light">
                                                            Excluir
                                                        </button>

                                                    </form>
                                                </div>
                                            </div>
                                        </div>
                                        <span>${msg}</span>
                                        <!-- Page-body end -->
                                    </div>
                                    <div id="styleSelector"></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <jsp:include page="javascriptfile.jsp"/>

        <script type="text/javascript">

            function limparForm() {
                var elementos = document.getElementById("formCadastro").elements;
                for (p = 0; p < elementos.length; p++) {
                    elementos[p].value = '';
                }
            }

        </script>


</body>

</html>



