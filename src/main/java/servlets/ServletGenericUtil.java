package servlets;

import dao.DAOUsuario;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.sql.SQLException;

public class ServletGenericUtil extends HttpServlet implements Serializable {

    private DAOUsuario daoUsuario = new DAOUsuario();

    public Long getUsuarioLogado(HttpServletRequest request) throws SQLException {
        HttpSession session = request.getSession();
        String usuarioLogado = (String) session.getAttribute("usuario");
        return daoUsuario.consultaUsuarioLogado(usuarioLogado).getId();
    }

}
