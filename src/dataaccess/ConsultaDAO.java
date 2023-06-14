package dataaccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.Consulta;
import models.Paciente;
import models.Medico;

public class ConsultaDAO {
    private Connection connection;

    public ConsultaDAO(Connection connection) {
        this.connection = connection;
    }

    public void marcarConsulta(Consulta consulta) throws SQLException{
        String sql = "INSERT INTO consulta (paciente_id, medico_id, data, horario, telefone) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, consulta.getPaciente().getId());
            statement.setInt(2, consulta.getMedico().getId());
            statement.setDate(3, consulta.getData());
            statement.setTime(4, consulta.getHora());
            statement.setString(5, consulta.getTelefone());

            statement.executeUpdate();
        }
    }

    public void desmarcarConsulta(Consulta consulta) throws SQLException{
        String sql = "DELETE FROM consulta WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, consulta.getId());

            statement.executeUpdate();
        }
    }

    public boolean remarcarConsulta(Consulta consulta) throws SQLException{
        String sql = "UPDATE consulta SET data = ?, horario = ? WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDate(1, consulta.getData());
            statement.setTime(2, consulta.getHora());
            statement.setInt(3, consulta.getId());

            return statement.executeUpdate() > 0;
        }
    }

    public Consulta buscarConsultaPorId(int id) throws SQLException{
        String sql = "SELECT * FROM consulta WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return criarConsulta(resultSet);
                }
            }

            return criarConsulta(statement.executeQuery());
        }
    }

    public Consulta buscarPorId(int id) throws SQLException {
        String query = "SELECT * FROM \"Paciente\" WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return criarConsulta(resultSet);
                }
            }
        }
        return null;
    }

    private Consulta criarConsulta(ResultSet resultSet) throws SQLException {
        Consulta consulta = new Consulta();
        consulta.setId(resultSet.getInt("id"));
        consulta.setMedico(new MedicoDAO(connection).buscarPorId(resultSet.getInt("medico_id")));
        consulta.setPaciente(new PacienteDAO(connection).buscarPorId(resultSet.getInt("paciente_id")));
        consulta.setData(resultSet.getDate("data"));
        consulta.setHora(resultSet.getTime("horario"));
        consulta.setTelefone(resultSet.getString("telefone"));

        return consulta;
    }
}