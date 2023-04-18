package servlets;

import model.ModelLogin;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/servletLogin")
public class ServletLogin extends HttpServlet {

    public ServletLogin() {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("Login");
        String senha = request.getParameter("Senha");

        if (login != null && !login.isEmpty() && senha != null && !senha.isEmpty()) {
            ModelLogin modelLogin = new ModelLogin();
            modelLogin.setLogin(login);
            modelLogin.setSenha(senha);
            if (modelLogin.getLogin().equalsIgnoreCase("admin") && modelLogin.getSenha().equalsIgnoreCase("admin")) {
                request.getSession().setAttribute("usuario", modelLogin.getLogin());
                RequestDispatcher redirecionar = request.getRequestDispatcher("principal/principal.jsp");
                redirecionar.forward(request, response);
            } else {
                RequestDispatcher redirecionar = request.getRequestDispatcher("index.jsp");
                request.setAttribute("msg", "Informe o login e senha corretamente");
                redirecionar.forward(request, response);
            }
        } else {
            RequestDispatcher redirecionar = request.getRequestDispatcher("index.jsp");
            request.setAttribute("msg", "Informe o login e senha corretamente");
            redirecionar.forward(request, response);
        }
    }
}