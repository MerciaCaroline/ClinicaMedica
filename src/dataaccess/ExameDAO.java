package dataaccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Exame;

public class ExameDAO {
    private static Connection connection;
    
    public ExameDAO(Connection connection) {
        ExameDAO.connection = connection;
    }

    public void inserir(Exame exame) throws SQLException {
        String sql = "INSERT INTO Exame (paciente_id, medico_id, laboratorio_id, datasolicitacao, tipo) VALUES(?, ?, ?, CURRENT_DATE, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, exame.getPaciente().getId());
            statement.setInt(2, exame.getMedico().getId());
            statement.setInt(3, exame.getLaboratorio().getId());
            statement.setString(5, exame.getTipo());

            statement.executeUpdate();
        }
    }

    public Exame buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM \"Exame\" WHERE id = ?";

        Exame exame = null;

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    exame = criarExame(resultSet);
                }
                else{
                    return null;
                }
            }
            catch (Exception e) {
                System.out.println(e);
                //erro
            }
        }

        return exame;
    }

    public List<Exame> buscarTodos() throws SQLException {
        String sql = "SELECT * FROM \"Exame\" ";

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
        String sql = "SELECT * FROM \"Exame\" WHERE medico_id = ?";

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

    public List<Exame> buscarPorLaboratorio(int laboratorio_id){
        String sql = "SELECT * FROM \"Exame\" WHERE laboratorio_id = ?";

        List<Exame> exames = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, laboratorio_id);
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

    public void inserirResultado(Exame exame) throws SQLException {
        String sql = "UPDATE exame SET resultado = ? , dataresultado = CURRENT_DATE WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, exame.getResultado());
            statement.setInt(2, exame.getId());
            statement.executeUpdate();
        }
    }

    public void atualizarFlags(int exameId, boolean disponivelOnline, boolean entreguePaciente) throws SQLException {
        String sql = "UPDATE exame SET disponivelonline = ? , entreguePaciente = ? WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setBoolean(1, disponivelOnline);
            statement.setBoolean(2, entreguePaciente);
            statement.setInt(3, exameId);
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
