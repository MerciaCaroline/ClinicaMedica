package dataaccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import models.Consulta;
import models.Paciente;
import models.Recepcionista;

public class RecepcionistaDAO {
    private Connection connection;

    // Construtor que recebe a conexão com o banco de dados
    public RecepcionistaDAO(Connection connection) {
        this.connection = connection;
    }

    // Método para verificar se o medico já está cadastrado no banco de dados
    public boolean medicoExistente(String nome) throws SQLException {
        String query = "SELECT COUNT(*) FROM medico WHERE nome = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, nome);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count > 0; 
                }
            }
        }
        return false;
    }

    // Método para verificar se o paciente já está cadastrado no banco de dados
    public boolean pacienteExistente(String nome) throws SQLException {
        String query = "SELECT COUNT(*) FROM paciente WHERE nome = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, nome);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count > 0; 
                }
            }
        }
        return false;
    }

    public boolean consultaExistente(Consulta consulta) throws SQLException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");  
        String strDate = dateFormat.format(consulta.getData());  

        String query = "SELECT COUNT(*) FROM consulta WHERE data_consulta = ? AND hora_consulta = ? AND id_medico = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, strDate);
            statement.setTime(2, consulta.getHora());
            statement.setInt(3, consulta.getMedico().getId());
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count > 0; 
                }
            }
        }
        return false;
    }

    // Método para inserir um novo paciente no banco de dados
    public boolean inserirPaciente(Paciente paciente) throws SQLException {
        String query = "INSERT INTO paciente (nome, telefone, endereco) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, paciente.getNome());
            statement.setString(2, paciente.getTelefone());
            statement.setString(3, paciente.getEndereco());
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        }
    }

    //Metodo para criar uma consulta
    public boolean criarConsulta(Consulta consulta) throws SQLException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");  
        String strDate = dateFormat.format(consulta.getData());  

        String query = "INSERT INTO consulta (id_paciente, id_medico, data_consulta, hora_consulta, telefone) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, consulta.getPaciente().getId());
            statement.setInt(2, consulta.getMedico().getId());
            statement.setString(3, strDate);
            statement.setTime(4, consulta.getHora());
            statement.setString(5, consulta.getTelefone());
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        }
    }

    //Metodo para cancelar uma consulta
    public boolean cancelarConsulta(int codigoConsulta) throws SQLException {
        String query = "DELETE FROM consulta WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, codigoConsulta);
            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0;
        }
    }

    public Recepcionista buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM \"Recepcionista\" WHERE id = ?";
        Recepcionista recepcionista = null;

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    recepcionista = criarRecepcionista(resultSet);
                }
            }
        }

        return recepcionista;
    }

    public Recepcionista buscarPorUsuario(String usuario) throws SQLException {
        String sql = "SELECT * FROM \"Recepcionista\" WHERE usuario = ?";

        Recepcionista recepcionista = null;

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, usuario);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    recepcionista = criarRecepcionista(resultSet);
                }
            }
        }

        return recepcionista;
    }

    public Recepcionista buscarPorNome(String nome) throws SQLException {
        String sql = "SELECT * FROM \"Recepcionista\" WHERE nome = ?";

        Recepcionista recepcionista = null;

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, nome);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    recepcionista = criarRecepcionista(resultSet);
                }
            }
        }

        return recepcionista;
    }

    private Recepcionista criarRecepcionista(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String usuario = resultSet.getString("usuario");
        String senha = resultSet.getString("senha");
        String nome = resultSet.getString("nome");

        return new Recepcionista(id, usuario, senha, nome);
    }
}