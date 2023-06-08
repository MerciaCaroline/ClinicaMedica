package dataaccess;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import models.ResultadoExame;

public class ResultadoExameDAO {
    private static Connection connection;

    public ResultadoExameDAO() {
        super();
    }

    public ResultadoExameDAO(Connection connection) {
        ResultadoExameDAO.connection = connection;
    }

    public void inserir(ResultadoExame resultadoExame) throws SQLException {
        String sql = "INSERT INTO resultado_exame (paciente, medico, exame, tipo, data_resultado, resultado) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, resultadoExame.getPaciente());
            statement.setInt(2, resultadoExame.getMedico());
            statement.setInt(3, resultadoExame.getExame());
            statement.setString(4, resultadoExame.getTipo());
            statement.setDate(5, (Date) resultadoExame.getDataResultado());
            statement.setString(6, resultadoExame.getResultado());
            statement.executeUpdate();
        }
    }

    public ResultadoExame buscarResultadoExame(int exame) throws SQLException{
        String sql = "SELECT * FROM resultado_exame WHERE exame = ?";

        ResultadoExame resultadoExame = null;

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, exame);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {

                    int pacienteId = resultSet.getInt("paciente");
                    int medicoId = resultSet.getInt("medico");
                    int exameId = resultSet.getInt("exame");
                    String tipo = resultSet.getString("tipo");
                    Date dataResultado = resultSet.getDate("data_resultado");
                    String resultado = resultSet.getString("resultado");

                    resultadoExame = new ResultadoExame(exameId, pacienteId, medicoId, tipo, dataResultado, resultado);
                }
            }
        }

        return resultadoExame;
    }
    
    public void atualizar(ResultadoExame resultadoExame) throws SQLException {
        String sql = "UPDATE resultado_exame SET paciente = ?, medico = ?, tipo = ?, data_resultado = ?, resultado = ? WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, resultadoExame.getPaciente());
            statement.setInt(2, resultadoExame.getMedico());
            statement.setString(3, resultadoExame.getTipo());
            statement.setDate(4, (Date) resultadoExame.getDataResultado());
            statement.setString(5, resultadoExame.getResultado());
            statement.setInt(6, resultadoExame.getId());
            statement.executeUpdate();
        }
    }

    public void excluir(int id) throws SQLException {
        String sql = "DELETE resultado_exame WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }
}
