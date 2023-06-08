package controllers;

import java.sql.SQLException;

import dataaccess.ResultadoExameDAO;
import models.ResultadoExame;

public class ResultadoExameController {
    public ResultadoExameDAO resultadoExameDAO;

    public ResultadoExameController() {
        resultadoExameDAO = new ResultadoExameDAO();
    }

    public ResultadoExame disponibilizarResultadoExame(int exameId) throws SQLException {
        ResultadoExame resultadoExame = resultadoExameDAO.buscarResultadoExame(exameId);
        if (resultadoExame != null) {
            return resultadoExame;
        }

        return null;
    }

    public void registrarResultadoExame(ResultadoExame resultadoExame) throws SQLException {
        resultadoExameDAO.inserir(resultadoExame);
    }
}
