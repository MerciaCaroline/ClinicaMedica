package controllers;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dataaccess.ExameDAO;
import models.Exame;
import models.Medico;
import models.Paciente;
import models.ResultadoExame;

public class ExameController {
    private static ExameDAO exameDAO;
    private Connection connection;


    public ExameController(Connection connection) {
        this.connection = connection;
        this.exameDAO = new ExameDAO(connection);
    }

    public ExameController() {
        super();
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    // CRUD
    public void cadastrarExame(Exame exame) throws SQLException{
        exameDAO.inserir(exame);
    }

    public Exame buscarExame(int id) throws SQLException{
        return exameDAO.buscarPorId(id);
    }

    public List<Exame> buscarTodosExames() throws SQLException{
        return exameDAO.buscarTodos();
    }

    public void atualizarExame(Exame exame) throws SQLException{
        exameDAO.atualizar(exame);
    }

    public void removerExame(int id) throws SQLException{
        exameDAO.excluir(id);
    }

    public void enviarResultadoExame(ResultadoExame resultadoExame, Medico medico) {
        // Lógica para enviar o resultado do exame para o médico
    }

    public List<ResultadoExame> consultarResultadosExames() {
        // Lógica para consultar os resultados de exames enviados pelo laboratório
        return null;
    }

    public void enviarResultadoExame(ResultadoExame resultadoExame, Medico medico, Paciente paciente) {
        // Lógica para enviar o resultado do exame diretamente ao médico ou ao paciente
    }
}
