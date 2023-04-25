package dao;

import connection.SingleConnection;
import model.ModelLogin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOUsuario {

    private Connection connection;

    public DAOUsuario() {
        connection = SingleConnection.getConnection();
    }

    public ModelLogin salvar(ModelLogin objeto) throws SQLException {

        if (objeto.isNovo()) {
            String sql = "INSERT INTO model_login (login, senha, nome, email) VALUES (?, ?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, objeto.getLogin());
            preparedStatement.setString(2, objeto.getSenha());
            preparedStatement.setString(3, objeto.getNome());
            preparedStatement.setString(4, objeto.getEmail());

            preparedStatement.executeUpdate();
            connection.commit();
        } else {
            String sql = "UPDATE model_login SET login = ?, senha = ?, nome = ?, email = ? WHERE id = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, objeto.getLogin());
            preparedStatement.setString(2, objeto.getSenha());
            preparedStatement.setString(3, objeto.getNome());
            preparedStatement.setString(4, objeto.getEmail());
            preparedStatement.setLong(5, objeto.getId());

            preparedStatement.executeUpdate();
            connection.commit();
        }


        return obterPorLogin(objeto.getLogin());
    }

    public List<ModelLogin> obterPorNome(String nome) throws SQLException {
        List<ModelLogin> usuarios = new ArrayList<>();

        String sql = "SELECT * FROM model_login WHERE UPPER(nome) LIKE UPPER(?)";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, "%" + nome + "%");

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            ModelLogin modelLogin = new ModelLogin();
            modelLogin.setId(resultSet.getLong("id"));
            modelLogin.setNome(resultSet.getString("nome"));
            modelLogin.setEmail(resultSet.getString("email"));
            modelLogin.setLogin(resultSet.getString("login"));
            modelLogin.setSenha(resultSet.getString("senha"));
            usuarios.add(modelLogin);
        }

        return usuarios;
    }


    public ModelLogin obterPorLogin(String login) throws SQLException {
        String sql = "SELECT * FROM model_login WHERE UPPER(login) = UPPER(?)";

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

    public void excluir(String id) throws SQLException {
        String sql = "DELETE FROM model_login WHERE id = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setLong(1, Long.parseLong(id));

        preparedStatement.executeUpdate();

        connection.commit();
    }

    public boolean usuarioExiste(String login) throws SQLException {
        String sql = "SELECT COUNT (1) > 0 as existe from model_login WHERE UPPER(login) = UPPER(?)";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, login);

        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();

        return resultSet.getBoolean("existe");
    }

}
