package dataaccess;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Exame;
import models.Laboratorio;

public class ExameDAO {
    private static Connection connection;
    
    public ExameDAO(Connection connection) {
        ExameDAO.connection = connection;
    }

    public void inserir(Exame exame) throws SQLException {


        String sql = "INSERT INTO Exame (paciente_id, medico_id, laboratorio_id, datasolicitacao, tipo) VALUES(?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, exame.getPaciente().getId());
            statement.setInt(2, exame.getMedico().getId());
            statement.setInt(3, exame.getLaboratorio().getId());
            statement.setDate(4, new java.sql.Date(exame.getDataSolicitacao().getTime()));
            statement.setString(5, exame.getTipo());

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
                    exame = criarExame(resultSet);
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
                    Exame exame = criarExame(resultSet);
                    exames.add(exame);
                }
            }
        }

        return exames;
    }

    public List<Exame> buscarPorMedico(int medicoId){
        String sql = "SELECT * FROM exame WHERE medico_id = ?";

        List<Exame> exames = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, medicoId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Exame exame = criarExame(resultSet);
                    exames.add(exame);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return exames;
    }

    public void atualizar(Exame exame) throws SQLException {
        String sql = "UPDATE exame SET resultado = ? WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, exame.getResultado());
            statement.setInt(2, exame.getId());
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

    private Exame criarExame(ResultSet resultSet) throws SQLException{
        
        Exame exame = new Exame();
        exame.setId(resultSet.getInt("id"));
        exame.setTipo(resultSet.getString("tipo"));
        exame.setDataSolicitacao(resultSet.getDate("datasolicitacao"));
        exame.setDataResultado(resultSet.getDate("dataresultado"));
        exame.setResultado(resultSet.getString("resultado"));
        exame.setDisponivelOnline(resultSet.getBoolean("disponivelonline"));
        exame.setEntreguePaciente(resultSet.getBoolean("entreguepaciente"));
        exame.setMedico(new MedicoDAO(connection).buscarPorId(resultSet.getInt("medico_id")));
        exame.setPaciente(new PacienteDAO(connection).buscarPorId(resultSet.getInt("paciente_id")));
        //exame.setLaboratorio(new LaboratorioDAO(connection).buscarPorId(resultSet.getInt("laboratorio_id")));

        return exame;
    }
}
