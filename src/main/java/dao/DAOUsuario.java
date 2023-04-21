package dao;

import connection.SingleConnection;
import model.ModelLogin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DAOUsuario {

    private Connection connection;

    public DAOUsuario() {
        connection = SingleConnection.getConnection();
    }

    public void salvar(ModelLogin objeto) throws SQLException {

        String sql = "INSERT INTO model_login (login, senha, nome, email) VALUES (?, ?, ?, ?)";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, objeto.getLogin());
        preparedStatement.setString(2, objeto.getSenha());
        preparedStatement.setString(3, objeto.getNome());
        preparedStatement.setString(4, objeto.getEmail());

        preparedStatement.executeUpdate();

        connection.commit();
    }
}
