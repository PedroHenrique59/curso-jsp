package dao;

import connection.SingleConnection;
import model.ModelLogin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAOLogin {

    private Connection connection;

    public DAOLogin() {
        connection = SingleConnection.getConnection();
    }

    public Boolean validarAutenticacao(ModelLogin modelLogin) throws SQLException {
        String sql = "SELECT * FROM model_login WHERE UPPER(login) = UPPER(?) AND UPPER(senha) = UPPER(?) ";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, modelLogin.getLogin());
        preparedStatement.setString(2, modelLogin.getSenha());

        ResultSet resultSet = preparedStatement.executeQuery();

        return resultSet.next();
    }
}


