package dataaccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Medico;

public class MedicoDAO {
    private static Connection connection;

    public MedicoDAO() {
        super();
    }

    public MedicoDAO(Connection connection) {
        MedicoDAO.connection = connection;
    }

    public void inserir(Medico medico) throws SQLException {
        String sql = "INSERT INTO medico (nome, especialidade, usuario, senha, telefone) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, medico.getNome());
            statement.setString(2, medico.getEspecialidade());
            statement.setString(2, medico.getUsuario());
            statement.setString(2, medico.getSenha());
            statement.setString(2, medico.getTelefone());
            statement.executeUpdate();
        }
    }

    public Medico buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM \"Medico\" WHERE id = ?";
        Medico medico = null;

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    medico = criarMedico(resultSet);
                }
            }
        }

        return medico;
    }

    public Medico buscarPorUsuario(String usuario) throws SQLException {
        String sql = "SELECT * FROM \"Medico\" WHERE usuario = ?";

        Medico medico = null;

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, usuario);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    medico = criarMedico(resultSet);
                }
            }
        }

        return medico;
    }

    public Medico buscarPorNome(String nome_buscar) throws SQLException {
        String sql = "SELECT * FROM \"Medico\" WHERE nome = ?";

        Medico medico = null;

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, nome_buscar);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return criarMedico(resultSet);
                }
            }
        }

        return medico;
    }

    public List<Medico> buscarTodos() throws SQLException {
        String sql = "SELECT * FROM \"Medico\"";
        List<Medico> medicos = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Medico medico = criarMedico(resultSet);
                medicos.add(medico);
            }
        }

        return medicos;
    }

    public void atualizar(Medico medico) throws SQLException {
        String sql = "UPDATE medico SET nome = ?, especialidade = ?, telefone = ? WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, medico.getNome());
            statement.setString(2, medico.getEspecialidade());
            statement.setString(3, medico.getTelefone());
            statement.setInt(3, medico.getId());
            statement.executeUpdate();
        }
    }

    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM medico WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }

    private Medico criarMedico(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String usuario = resultSet.getString("usuario");
        String senha = resultSet.getString("senha");
        String nome = resultSet.getString("nome");
        String especialidade = resultSet.getString("especialidade");
        String telefone = resultSet.getString("telefone");
        String registroCrm = resultSet.getString("registroCrm");

        return new Medico(id, usuario, senha, nome, especialidade, telefone, registroCrm);
    }

}
