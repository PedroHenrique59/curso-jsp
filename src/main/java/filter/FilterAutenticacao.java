package filter;

import connection.SingleConnection;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebFilter(urlPatterns = {"/principal/*"})
public class FilterAutenticacao implements Filter {

    private static Connection connection;

    /* Executado quando o servidor sobe */
    public void init(FilterConfig config) throws ServletException {
        connection = SingleConnection.getConnection();
    }

    /* Executado quando o servidor para */
    public void destroy() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /* Intercepta as requisições e as respostas no sistema */
    /* Ex: Validar autenticação, fazer redirecionamento de páginas */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        try {
            HttpServletRequest req = (HttpServletRequest) request;
            HttpSession session = req.getSession();

            String usuarioLogado = (String) session.getAttribute("usuario");

            String urlAcessada = req.getServletPath(); //Url que está sendo acessada

            if (usuarioLogado == null && !urlAcessada.equalsIgnoreCase("/principal/ServletLogin")) {
                RequestDispatcher redireciona = request.getRequestDispatcher("/index.jsp?url=" + urlAcessada);
                request.setAttribute("msg", "Realize o login para acessar essa página!");
                redireciona.forward(request, response);
            } else {
                chain.doFilter(request, response);
            }
            connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
            RequestDispatcher redirecionar = request.getRequestDispatcher("erro.jsp");
            request.setAttribute("msg", e.getMessage());
            redirecionar.forward(request, response);
            try {
                connection.rollback();
            } catch (SQLException ex) {
                e.printStackTrace();
            }
        }
    }
}
