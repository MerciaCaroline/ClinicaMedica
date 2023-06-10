package dataaccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Paciente;

public class PacienteDAO {
    private Connection connection;

    public PacienteDAO() {
        super();
    }

    public PacienteDAO(Connection connection) {
        this.connection = connection;
    }

    public void criar(Paciente paciente) throws SQLException {
        String query = "INSERT INTO pacientes (nome, telefone, endereco) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, paciente.getNome());
            statement.setString(2, paciente.getTelefone());
            statement.setString(3, paciente.getEndereco());
            statement.executeUpdate();
        }
    }

    public Paciente buscarPorCpf(int cpf) throws SQLException {
        String query = "SELECT * FROM pacientes WHERE cpf = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, cpf);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return criarPaciente(resultSet);
                }
            }
        }
        return null;
    }

    public List<Paciente> lerTodos() throws SQLException {
        List<Paciente> pacientes = new ArrayList<>();
        String query = "SELECT * FROM pacientes";
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Paciente paciente = criarPaciente(resultSet);
                pacientes.add(paciente);
            }
        }
        return pacientes;
    }

    public Paciente buscarPorId(int id) throws SQLException {
        String query = "SELECT * FROM pacientes WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return criarPaciente(resultSet);
                }
            }
        }
        return null;
    }

    public void atualizar(Paciente paciente) throws SQLException {
        String query = "UPDATE pacientes SET nome = ?, relefone = ?, endereco = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, paciente.getNome());
            statement.setString(2, paciente.getTelefone());
            statement.setString(3, paciente.getEndereco());
            statement.setInt(4, paciente.getId());
            statement.executeUpdate();
        }
    }

    public Paciente buscarPorNome(String nome_buscar) throws SQLException {
        String sql = "SELECT * FROM medico WHERE nome = ?";

        Paciente paciente = null;

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    paciente = criarPaciente(resultSet);
                }
            }
        }
        
        return paciente;
    }

    public void excluir(int id) throws SQLException {
        String query = "DELETE FROM pacientes WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }

    private Paciente criarPaciente(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String nome = resultSet.getString("nome");
        String telefone = resultSet.getString("telefone");
        String usuario = resultSet.getString("usuario");
        String senha = resultSet.getString("senha");
        String endereco = resultSet.getString("endereco");
        char sexo = resultSet.getString("sexo").charAt(0);
        int idade = resultSet.getInt("idade");
        String email = resultSet.getString("email");

        return new Paciente(id, usuario, senha, nome, telefone, endereco, sexo, idade, email);
    }
}

