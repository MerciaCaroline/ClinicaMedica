package dataaccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Medico;

public class MedicoDAO {
    private Connection connection;

    public MedicoDAO(Connection connection) {
        this.connection = connection;
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

    public Medico buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM medico WHERE id = ?";

        Medico medico = null;

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {

                    String usuario = resultSet.getString("usuario");
                    String senha = resultSet.getString("senha");
                    String nome = resultSet.getString("nome");
                    String especialidade = resultSet.getString("especialidade");
                    String telefone = resultSet.getString("telefone");
                    String registroCrm = resultSet.getString("registroCrm");

                    medico = new Medico(id, usuario, senha, nome, especialidade, telefone, registroCrm);
                }
            }
        }

        return medico;
    }

    public List<Medico> listarTodos() throws SQLException {
        String sql = "SELECT * FROM medico";
        List<Medico> medicos = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String usuario = resultSet.getString("usuario");
                    String senha = resultSet.getString("senha");
                    String nome = resultSet.getString("nome");
                    String especialidade = resultSet.getString("especialidade");
                    String telefone = resultSet.getString("telefone");
                    String registroCrm = resultSet.getString("registroCrm");

                Medico medico = new Medico(id, usuario, senha, nome, especialidade, telefone, registroCrm);
                medicos.add(medico);
            }
        }

        return medicos;
    }
}
