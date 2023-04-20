package servlets;

import dao.DAOLogin;
import model.ModelLogin;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/principal/ServletLogin", "/ServletLogin"})
public class ServletLogin extends HttpServlet {

    private DAOLogin daoLogin = new DAOLogin();

    public ServletLogin() {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("Login");
        String senha = request.getParameter("Senha");
        String url = request.getParameter("url");
        try {
            if (login != null && !login.isEmpty() && senha != null && !senha.isEmpty()) {
                ModelLogin modelLogin = new ModelLogin();
                modelLogin.setLogin(login);
                modelLogin.setSenha(senha);
                if (daoLogin.validarAutenticacao(modelLogin)) {
                    request.getSession().setAttribute("usuario", modelLogin.getLogin());
                    if (url == null || url.equals("null")) {
                        url = "principal/principal.jsp";
                    }
                    RequestDispatcher redirecionar = request.getRequestDispatcher(url);
                    redirecionar.forward(request, response);
                } else {
                    RequestDispatcher redirecionar = request.getRequestDispatcher("/index.jsp");
                    request.setAttribute("msg", "Informe o login e senha corretamente");
                    redirecionar.forward(request, response);
                }
            } else {
                RequestDispatcher redirecionar = request.getRequestDispatcher("index.jsp");
                request.setAttribute("msg", "Informe o login e senha corretamente");
                redirecionar.forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            RequestDispatcher redirecionar = request.getRequestDispatcher("erro.jsp");
            request.setAttribute("msg", e.getMessage());
            redirecionar.forward(request, response);
        }
    }
}
