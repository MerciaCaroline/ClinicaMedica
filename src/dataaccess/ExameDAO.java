package dataaccess;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Exame;

public class ExameDAO {
    private static Connection connection;
    
    public ExameDAO(Connection connection) {
        this.connection = connection;
    }

    public void inserir(Exame exame) throws SQLException {
        String sql = "INSERT INTO exame (nome, descricao, preco) VALUES (?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, exame.getTipo());
            statement.setBoolean(2, exame.getAutorizado());
            statement.setDate(3, (Date) exame.getData());
            statement.setInt(4, exame.getMedico().getId());
            statement.setInt(5, exame.getPaciente().getId());
            statement.executeUpdate();
        }
    }

    public Exame buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM exame WHERE id = ?";

        Exame exame = null;

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    exame = new Exame();
                    exame.setId(resultSet.getInt("id"));
                    exame.setTipo(resultSet.getString("tipo"));
                    exame.setAutorizado(resultSet.getBoolean("autorizado"));
                    exame.setData(resultSet.getDate("data"));
                    exame.setMedico(new MedicoDAO(connection).buscarPorId(resultSet.getInt("medico_id")));
                    exame.setPaciente(new PacienteDAO(connection).buscarPorId(resultSet.getInt("paciente_id")));
                }
            }
        }

        return exame;
    }

    public List<Exame> buscarTodos() throws SQLException {
        String sql = "SELECT * FROM exame";

        List<Exame> exames = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Exame exame = new Exame();
                    exame.setId(resultSet.getInt("id"));
                    exame.setTipo(resultSet.getString("tipo"));
                    exame.setAutorizado(resultSet.getBoolean("autorizado"));
                    exame.setData(resultSet.getDate("data"));
                    exame.setMedico(new MedicoDAO(connection).buscarPorId(resultSet.getInt("medico_id")));
                    exame.setPaciente(new PacienteDAO(connection).buscarPorId(resultSet.getInt("paciente_id")));
                    exames.add(exame);
                }
            }
        }

        return exames;
    }

    public List<Exame> buscarResultadoExames(int medicoId){
        String sql = "SELECT * FROM exame WHERE medico_id = $medicoId AND autorizado = true";

        List<Exame> exames = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, medicoId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Exame exame = new Exame();
                    exame.setId(resultSet.getInt("id"));
                    exame.setTipo(resultSet.getString("tipo"));
                    exame.setAutorizado(resultSet.getBoolean("autorizado"));
                    exame.setData(resultSet.getDate("data"));
                    exame.setMedico(new MedicoDAO(connection).buscarPorId(resultSet.getInt("medico_id")));
                    exame.setPaciente(new PacienteDAO(connection).buscarPorId(resultSet.getInt("paciente_id")));
                    exames.add(exame);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return exames;
    }

    public void atualizar(Exame exame) throws SQLException {
        String sql = "UPDATE exame SET nome = ?, descricao = ?, preco = ? WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, exame.getTipo());
            statement.setBoolean(2, exame.getAutorizado());
            statement.setDate(3, (Date) exame.getData());
            statement.setInt(4, exame.getMedico().getId());
            statement.setInt(5, exame.getPaciente().getId());
            statement.setInt(6, exame.getId());
            statement.executeUpdate();
        }
    }

    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM exame WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }
}
