package dao;

import connection.SingleConnection;
import model.ModelLogin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAOUsuario {

    private Connection connection;

    public DAOUsuario() {
        connection = SingleConnection.getConnection();
    }

    public ModelLogin salvar(ModelLogin objeto) throws SQLException {

        String sql = " INSERT INTO model_login (login, senha, nome, email) VALUES (?, ?, ?, ?) ";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, objeto.getLogin());
        preparedStatement.setString(2, objeto.getSenha());
        preparedStatement.setString(3, objeto.getNome());
        preparedStatement.setString(4, objeto.getEmail());

        preparedStatement.executeUpdate();

        connection.commit();

        return obterPorLogin(objeto.getLogin());
    }

    public ModelLogin obterPorLogin(String login) throws SQLException {
        String sql = " SELECT * FROM model_login WHERE UPPER(login) = UPPER(?) ";

        ModelLogin modelLogin = new ModelLogin();

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, login);

        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            modelLogin.setId(resultSet.getLong("id"));
            modelLogin.setNome(resultSet.getString("nome"));
            modelLogin.setEmail(resultSet.getString("email"));
            modelLogin.setLogin(resultSet.getString("login"));
            modelLogin.setSenha(resultSet.getString("senha"));
        }

        return modelLogin;
    }
}
