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

                                                        <input type="hidden" name="acao" id="acao" value="">

                                                        <div class="form-group form-default form-static-label">
                                                            <input type="text" name="id" id="id"
                                                                   value="${modelLogin.id}" class="form-control"
                                                                   readonly="readonly">
                                                            <span class="form-bar"></span>
                                                            <label class="float-label">ID</label>
                                                        </div>
                                                        <div class="form-group form-default form-static-label">
                                                            <input type="text" name="nome" id="nome"
                                                                   value="${modelLogin.nome}"
                                                                   class="form-control" required="required">
                                                            <span class="form-bar"></span>
                                                            <label class="float-label">Nome</label>
                                                        </div>
                                                        <div class="form-group form-default form-static-label">
                                                            <input type="email" name="email" id="email"
                                                                   value="${modelLogin.email}"
                                                                   class="form-control" required="required">
                                                            <span class="form-bar"></span>
                                                            <label class="float-label">E-mail</label>
                                                        </div>
                                                        <div class="form-group form-default form-static-label">
                                                            <input type="text" name="login" id="login"
                                                                   value="${modelLogin.login}"
                                                                   class="form-control" required="required">
                                                            <span class="form-bar"></span>
                                                            <label class="float-label">Login</label>
                                                        </div>
                                                        <div class="form-group form-default form-static-label">
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

                                                        <button class="btn btn-primary waves-effect waves-light"
                                                                type="button" onclick="criarDeleteComAjax();">
                                                            Excluir
                                                        </button>

                                                        <button type="button" class="btn btn-primary"
                                                                data-toggle="modal"
                                                                data-target="#modalPesquisarUsuario">
                                                            Pesquisar
                                                        </button>

                                                    </form>
                                                </div>
                                            </div>
                                        </div>

                                        <span id="msg">${msg}</span>

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

        <div class="modal fade" id="modalPesquisarUsuario" tabindex="-1" role="dialog"
             aria-labelledby="exampleModalLabel"
             aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Pesquisar</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="input-group mb-3">
                            <input type="text" id="inputNome" class="form-control" placeholder="Nome"
                                   aria-label="Recipient's username" aria-describedby="basic-addon2">
                            <div class="input-group-append">
                                <button class="btn btn-outline-secondary" type="button" onclick="buscarUsuario()">
                                    Pesquisar
                                </button>
                            </div>
                        </div>

                        <div style="height: 300px; overflow: scroll;">
                            <table class="table" id="tabelaUsuarios">
                                <thead>
                                <tr>
                                    <th scope="col">ID</th>
                                    <th scope="col">Nome</th>
                                    <th scope="col">Ver</th>
                                </tr>
                                </thead>
                                <tbody>

                                </tbody>
                            </table>
                        </div>
                        <span id="totalResultados"></span>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>

        <script type="text/javascript">

            function buscarUsuario() {
                var nome = document.getElementById("inputNome").value;
                var urlAction = document.getElementById("formCadastro").action;

                if (nome != null && nome !== '' && nome.trim() !== '') {
                    $.ajax({
                        method: "get",
                        url: urlAction,
                        data: "nome=" + nome + '&acao=pesquisarAjax',
                        success: function (response) {
                            var json = JSON.parse(response);

                            $('#tabelaUsuarios > tbody > tr').remove();

                            for (var p = 0; p < json.length; p++) {
                                $('#tabelaUsuarios > tbody').append('<tr> <td>' + json[p].id + '</td> <td>' + json[p].nome + '</td> <td> <button type="button" class="btn btn-info" onclick="verEditar(' + json[p].id + ')">Ver</button> </td> </tr>');
                            }

                            document.getElementById("totalResultados").textContent = "Resultados: " + json.length;

                        }
                    }).fail(function (xhr, status, errorThrown) {
                        alert('Erro ao pesquisar usuário por nome: ' + xhr.responseText);
                    });
                }
            }

            function verEditar(id) {
                var urlAction = document.getElementById("formCadastro").action;
                window.location.href = urlAction + '?acao=buscarEditar&id='+id;
            }

            function criarDeleteComAjax() {
                if (confirm('Deseja realmente excluir os dados?')) {

                    var urlAction = document.getElementById("formCadastro").action;
                    var idUser = document.getElementById("id").value;

                    $.ajax({
                        method: "get",
                        url: urlAction,
                        data: "id=" + idUser + '&acao=excluirAjax',
                        success: function (response) {
                            alert(response);
                            limparForm();
                        }
                    }).fail(function (xhr, status, errorThrown) {
                        alert('Erro ao deletar usuário por id: ' + xhr.responseText);
                    });
                }
            }

            function criarDeletePorSubmit() {
                if (confirm('Deseja realmente excluir os dados?')) {
                    document.getElementById("formCadastro").method = 'get';
                    document.getElementById("acao").value = 'excluir';
                    document.getElementById("formCadastro").submit();
                }
            }

            function limparForm() {
                var elementos = document.getElementById("formCadastro").elements;
                for (p = 0; p < elementos.length; p++) {
                    elementos[p].value = '';
                }
            }

        </script>

</body>

</html>



