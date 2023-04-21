package servlets;

import model.ModelLogin;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ServletCadastro")
public class ServletCadastro extends HttpServlet {

    public ServletCadastro() {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher redirecionar = request.getRequestDispatcher("/principal/cadastro.jsp");
        redirecionar.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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

        RequestDispatcher redirecionar = request.getRequestDispatcher("principal/cadastro.jsp");
        request.setAttribute("modelLogin", modelLogin);
        redirecionar.forward(request, response);

    }
}
