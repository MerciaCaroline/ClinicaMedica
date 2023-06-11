package dataaccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Laboratorio;

public class LaboratorioDAO {
    private Connection connection;

    public LaboratorioDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Laboratorio> buscarTodos() throws SQLException {
        String sql = "SELECT * FROM \"Laboratorio\" ";

        List<Laboratorio> laboratorios = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Laboratorio laboratorio = criarLaboratorio(resultSet);
                    laboratorios.add(laboratorio);
                }
            }
        }

        return laboratorios;
    }

    public Laboratorio buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM \"Laboratorio\" WHERE id = ?";
        Laboratorio laboratorio = null;

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    laboratorio = criarLaboratorio(resultSet);
                }
            }
        }

        return laboratorio;
    }

    public Laboratorio buscarPorUsuario(String usuario) throws SQLException {
        String sql = "SELECT * FROM \"Laboratorio\" WHERE usuario = ?";

        Laboratorio laboratorio = null;

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, usuario);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    laboratorio = criarLaboratorio(resultSet);
                }
            }
        }

        return laboratorio;
    }

    private Laboratorio criarLaboratorio(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String usuario = resultSet.getString("usuario");
        String senha = resultSet.getString("senha");
        String nome = resultSet.getString("nome");
        String telefone = resultSet.getString("telefone");
        String endereco = resultSet.getString("endereco");

        return new Laboratorio(id, usuario, senha, nome, telefone, endereco);
    }
}
