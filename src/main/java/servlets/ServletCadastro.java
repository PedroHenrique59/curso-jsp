package servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import dao.DAOUsuario;
import model.ModelLogin;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/ServletCadastro")
public class ServletCadastro extends HttpServlet {

    private DAOUsuario daoUsuario = new DAOUsuario();

    public ServletCadastro() {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String acao = request.getParameter("acao");
            if (acao != null && !acao.isEmpty()) {
                if (acao.equalsIgnoreCase("acessarPagina")) {
                    List<ModelLogin> usuarios = daoUsuario.obterTodos();
                    request.setAttribute("listaUsuarios", usuarios);
                    request.getRequestDispatcher("/principal/cadastro.jsp").forward(request, response);
                } else if (acao.equalsIgnoreCase("excluir")) {
                    String id = request.getParameter("id");
                    if (id != null && !id.isEmpty()) {
                        daoUsuario.excluir(id);
                        List<ModelLogin> usuarios = daoUsuario.obterTodos();
                        request.setAttribute("listaUsuarios", usuarios);
                        request.setAttribute("msg", "Excluído com sucesso!");
                        request.getRequestDispatcher("/principal/cadastro.jsp").forward(request, response);
                    }
                } else if (acao.equalsIgnoreCase("excluirAjax")) {
                    String id = request.getParameter("id");
                    if (id != null && !id.isEmpty()) {
                        daoUsuario.excluir(id);
                        response.getWriter().write("Excluído com sucesso!");
                    }
                } else if (acao.equalsIgnoreCase("pesquisarAjax")) {
                    String nome = request.getParameter("nome");
                    if (nome != null && !nome.isEmpty()) {
                        List<ModelLogin> usuarios = daoUsuario.obterPorNome(nome);
                        ObjectMapper objectMapper = new ObjectMapper();
                        String json = objectMapper.writeValueAsString(usuarios);
                        response.getWriter().write(json);
                    }
                } else if (acao.equalsIgnoreCase("buscarEditar")) {
                    String id = request.getParameter("id");
                    ModelLogin modelLogin = daoUsuario.obterPorId(id);
                    List<ModelLogin> usuarios = daoUsuario.obterTodos();
                    request.setAttribute("listaUsuarios", usuarios);
                    request.setAttribute("msg", "Usuário em edição");
                    request.setAttribute("modelLogin", modelLogin);
                    request.getRequestDispatcher("/principal/cadastro.jsp").forward(request, response);
                } else {
                    request.getRequestDispatcher("/principal/cadastro.jsp").forward(request, response);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            RequestDispatcher redirecionar = request.getRequestDispatcher("erro.jsp");
            request.setAttribute("msg", e.getMessage());
            redirecionar.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String mensagem = "Operação relaizada com sucesso";

            String id = request.getParameter("id");
            String nome = request.getParameter("nome");
            String email = request.getParameter("email");
            String login = request.getParameter("login");
            String senha = request.getParameter("senha");

            ModelLogin modelLogin = new ModelLogin();

            modelLogin.setId(id != null && !id.isEmpty() ? Long.parseLong(id) : null);
            modelLogin.setNome(nome);
            modelLogin.setEmail(email);
            modelLogin.setLogin(login);
            modelLogin.setSenha(senha);

            if (daoUsuario.usuarioExiste(modelLogin.getLogin()) && modelLogin.getId() == null) {
                mensagem = "Já existe um usuário com esse login. Favor informar outro!";
            } else {
                modelLogin = daoUsuario.salvar(modelLogin);
            }

            RequestDispatcher redirecionar = request.getRequestDispatcher("principal/cadastro.jsp");
            request.setAttribute("modelLogin", modelLogin);
            request.setAttribute("msg", mensagem);
            redirecionar.forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            RequestDispatcher redirecionar = request.getRequestDispatcher("erro.jsp");
            request.setAttribute("msg", e.getMessage());
            redirecionar.forward(request, response);
        }
    }
}
