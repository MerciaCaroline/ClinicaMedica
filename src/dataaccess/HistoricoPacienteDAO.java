package dataaccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.HistoricoPaciente;
import models.Paciente;

public class HistoricoPacienteDAO {
    private Connection connection;

    public HistoricoPacienteDAO(Connection connection) {
        this.connection = connection;
    }

    public void adicionarHistoricoPaciente(HistoricoPaciente historico) throws SQLException {
        String sql = "INSERT INTO historico_paciente (paciente_id, data, descricao) VALUES (?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, historico.getPaciente().getId());
            statement.setDate(2, new java.sql.Date(historico.getData().getTime()));
            statement.setString(3, historico.getDescricao());

            statement.executeUpdate();
        }
    }

    public void removerHistoricoPaciente(int id) throws SQLException {
        String sql = "DELETE FROM historico_paciente WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);

            statement.executeUpdate();
        }
    }

    public List<HistoricoPaciente> obterHistoricoPorPaciente(int pacienteId) throws SQLException {
        String sql = "SELECT * FROM historico_paciente WHERE paciente_id = ?";
        List<HistoricoPaciente> historicos = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, pacienteId);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    HistoricoPaciente historico = new HistoricoPaciente();
                    historico.setId(resultSet.getInt("id"));
                    historico.setPaciente(obterPacientePorId(resultSet.getInt("paciente_id")));
                    historico.setData(resultSet.getDate("data"));
                    historico.setDescricao(resultSet.getString("descricao"));
                    historicos.add(historico);
                }
            }
        }

        return historicos;
    }

    private Paciente obterPacientePorId(int id) throws SQLException {
        PacienteDAO dataAccess = new PacienteDAO();
        return dataAccess.buscarPorId(id);
    }
}
