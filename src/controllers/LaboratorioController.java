package controllers;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dataaccess.LaboratorioDAO;
import models.Laboratorio;

public class LaboratorioController {
    Connection connection;
    LaboratorioDAO LaboratorioDAO;
    
    public LaboratorioController(Connection connection) {
        this.connection = connection;
        this.LaboratorioDAO = new LaboratorioDAO(connection);
    }

    public Laboratorio buscarPorId(int id) throws SQLException {
        return LaboratorioDAO.buscarPorId(id);
    }

    public Laboratorio buscarPorUsuario(String usuario) throws SQLException {
        return LaboratorioDAO.buscarPorUsuario(usuario);
    }

    public List<Laboratorio> buscarTodos() throws SQLException{
        return LaboratorioDAO.buscarTodos();
    }
}
