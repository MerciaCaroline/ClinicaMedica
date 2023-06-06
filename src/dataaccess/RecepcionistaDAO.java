package dataaccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Recepcionista;

public class RecepcionistaDAO {
    private Connection connection;

    public RecepcionistaDAO(Connection connection) {
        this.connection = connection;
    }

    public void adicionarRecepcionista(Recepcionista recepcionista) throws SQLException {
        String sql = "INSERT INTO recepcionista (usuario, senha, nome) VALUES (?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, recepcionista.getUsuario());
            statement.setString(2, recepcionista.getSenha());
            statement.setString(3, recepcionista.getNome());

            statement.executeUpdate();
        }
    }

    public void atualizarRecepcionista(Recepcionista recepcionista) throws SQLException {
        String sql = "UPDATE recepcionista SET usuario = ?, senha = ?, nome = ? WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, recepcionista.getUsuario());
            statement.setString(2, recepcionista.getSenha());
            statement.setString(3, recepcionista.getNome());
            statement.setInt(4, recepcionista.getId());

            statement.executeUpdate();
        }
    }

    public void removerRecepcionista(int id) throws SQLException {
        String sql = "DELETE FROM recepcionista WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);

            statement.executeUpdate();
        }
    }

    public Recepcionista obterRecepcionistaPorId(int id) throws SQLException {
        String sql = "SELECT * FROM recepcionista WHERE id = ?";
        Recepcionista recepcionista = null;

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String usuario = resultSet.getString("usuario");
                    String senha = resultSet.getString("senha");
                    String nome = resultSet.getString("nome");
                    
                    recepcionista = new Recepcionista(id, usuario, senha, nome);
                }
            }
        }

        return recepcionista;
    }

    public List<Recepcionista> listarRecepcionistas() throws SQLException {
        String sql = "SELECT * FROM recepcionista";
        List<Recepcionista> recepcionistas = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String usuario = resultSet.getString("usuario");
                String senha = resultSet.getString("senha");
                String nome = resultSet.getString("nome");

                Recepcionista recepcionista = new Recepcionista(id, usuario, senha, nome);
                recepcionistas.add(recepcionista);
            }
        }

        return recepcionistas;
    }
}

