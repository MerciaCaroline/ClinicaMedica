package controllers;

import java.sql.Connection;
import java.sql.Time;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Scanner;

import dataaccess.ConsultaDAO;
import models.Consulta;
import models.Medico;
import models.Paciente;

public class ConsultaController {
    private ConsultaDAO consultaDAO;
    private Scanner scanner;

    public ConsultaController(Connection connection) {
        this.consultaDAO = new ConsultaDAO(connection);
        scanner = new Scanner(System.in);
    }

    public void marcarConsulta(Paciente paciente, Medico medico, Date data, Time hora, String telefone) throws SQLException {
        Consulta consulta = new Consulta(paciente, medico, data, hora, telefone);
        consultaDAO.marcarConsulta(consulta);
    }

    public void remarcarConsulta(int consultaId, Date data, Time hora) throws SQLException {
        Consulta consulta = new Consulta();
        consulta.setId(consultaId);

        consulta  = consultaDAO.buscarConsultaPorId(consulta.getId());
        if(consulta == null) {
            System.out.println("Consulta não encontrada");
            return;
        }

        consulta.setData(data);
        consulta.setHora(hora);
        consultaDAO.remarcarConsulta(consulta);
    }

    public void desmarcarConsulta(int consultaId) throws SQLException {
        Consulta consulta = new Consulta();
        consulta.setId(consultaId);

        consulta  = consultaDAO.buscarConsultaPorId(consulta.getId());
        if(consulta == null) {
            System.out.println("Consulta não encontrada");
            return;
        }
        
        consultaDAO.remarcarConsulta(consulta);
    }

    public void setConsultaDAO(ConsultaDAO consultaDAO) {
        this.consultaDAO = consultaDAO;
    }

    public ConsultaDAO getConsultaDAO() {
        return consultaDAO;
    }
}
