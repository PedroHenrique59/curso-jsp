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

    public ModelLogin salvar(ModelLogin objeto, Long idUsuarioLogado) throws SQLException {

        if (objeto.isNovo()) {
            String sql = "INSERT INTO model_login (login, senha, nome, email, usuario_id) VALUES (?, ?, ?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, objeto.getLogin());
            preparedStatement.setString(2, objeto.getSenha());
            preparedStatement.setString(3, objeto.getNome());
            preparedStatement.setString(4, objeto.getEmail());
            preparedStatement.setLong(5, idUsuarioLogado);

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

        return obterPorLogin(objeto.getLogin(), idUsuarioLogado);
    }

    public List<ModelLogin> obterPorNome(String nome, Long idUsuarioLogado) throws SQLException {
        List<ModelLogin> usuarios = new ArrayList<>();

        String sql = "SELECT * FROM model_login WHERE UPPER(nome) LIKE UPPER(?) AND useradmin IS FALSE AND usuario_id = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, "%" + nome + "%");
        preparedStatement.setLong(2, idUsuarioLogado);

        return montarListaUsuarios(usuarios, preparedStatement);
    }

    public List<ModelLogin> obterTodos(Long idUsuarioLogado) throws SQLException {
        List<ModelLogin> usuarios = new ArrayList<>();

        String sql = "SELECT * FROM model_login WHERE useradmin IS FALSE AND usuario_id = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setLong(1, idUsuarioLogado);

        return montarListaUsuarios(usuarios, preparedStatement);
    }

    public ModelLogin obterPorLogin(String login, Long idUsuarioLogado) throws SQLException {
        String sql = "SELECT * FROM model_login WHERE UPPER(login) = UPPER(?) AND useradmin IS FALSE AND usuario_id = ?";

        ModelLogin modelLogin = new ModelLogin();

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, login);
        preparedStatement.setLong(2, idUsuarioLogado);

        return montarModelLogin(modelLogin, preparedStatement);
    }

    public ModelLogin obterPorLogin(String login) throws SQLException {
        String sql = "SELECT * FROM model_login WHERE UPPER(login) = UPPER(?) AND useradmin IS FALSE";

        ModelLogin modelLogin = new ModelLogin();

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, login);

        return montarModelLogin(modelLogin, preparedStatement);
    }

    public ModelLogin consultaUsuarioLogado(String login) throws SQLException {
        String sql = "SELECT * FROM model_login WHERE UPPER(login) = UPPER(?)";

        ModelLogin modelLogin = new ModelLogin();

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, login);

        return montarModelLogin(modelLogin, preparedStatement);
    }

    public ModelLogin obterPorId(String id, Long idUsuarioLogado) throws SQLException {
        String sql = "SELECT * FROM model_login WHERE id = (?) AND useradmin IS FALSE AND usuario_id = ?";

        ModelLogin modelLogin = new ModelLogin();

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setLong(1, Long.parseLong(id));
        preparedStatement.setLong(2, idUsuarioLogado);

        return montarModelLogin(modelLogin, preparedStatement);
    }

    public void excluir(String id) throws SQLException {
        String sql = "DELETE FROM model_login WHERE id = ? AND useradmin IS FALSE";

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

    private ModelLogin montarModelLogin(ModelLogin modelLogin, PreparedStatement preparedStatement) throws SQLException {
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

    private List<ModelLogin> montarListaUsuarios(List<ModelLogin> usuarios, PreparedStatement preparedStatement) throws SQLException {
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
}
