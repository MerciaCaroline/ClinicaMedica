package controllers;

import java.sql.Connection;
import java.sql.SQLException;

import dataaccess.RecepcionistaDAO;
import dataaccess.MedicoDAO;
import dataaccess.PacienteDAO;
import models.Consulta;
import models.Medico;
import models.Paciente;
import models.Recepcionista;

public class RecepcionistaController {
    private RecepcionistaDAO recepcionistaDAO;
    private MedicoDAO medicoDAO;
    private PacienteDAO pacienteDAO;
    private Connection connection;

    public RecepcionistaController(Connection connection) {
        this.connection = connection;
    }

    public RecepcionistaController(RecepcionistaDAO recepcionistaDAO) {
        this.recepcionistaDAO = recepcionistaDAO;
    }

    public boolean registrarPaciente(Paciente paciente) {
        try {
            // Verificar se o paciente já está cadastrado
            if (recepcionistaDAO.pacienteExistente(paciente.getNome())) {
                System.out.println("O paciente já está cadastrado.");
                return false; 
            }

            // Realizar o registro do paciente
            if (recepcionistaDAO.inserirPaciente(paciente)) {
                System.out.println("Paciente registrado com sucesso.");
                return true;
            } else {
                System.out.println("Erro ao registrar o paciente.");
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao acessar o banco de dados.");
            return false;
        }
    }

    public boolean marcarConsulta(Consulta consulta) throws SQLException {
        try {
            //Verificar se o médico já tem uma consulta nessa hora
            if(recepcionistaDAO.consultaExistente(consulta)){
                System.out.println("O médico já tem consulta marcada para esse dia e hora.");
                return false;
            }

            //Realizar a criação da consulta
            if(recepcionistaDAO.criarConsulta(consulta)){
                System.out.println("Consulta registrada com sucesso.");
                return true;
            }
            else{
                System.out.println("Erro ao registrar a consulta.");
                return false;
            }

        } catch (Exception e) {
            System.out.println("Erro ao acessar o banco de dados.");
            return false;
        }
    }

    public Medico buscarMedicoPorNome(String nome) throws SQLException{
        try {
           Medico medico = medicoDAO.buscarPorNome(nome);
           return medico;
        } catch (SQLException e) {
            System.out.println("Erro ao acessar o banco de dados.");
            return null;
        }
    }

    public Paciente buscarPacientePorNome(String nome) throws SQLException{
        try {
            Paciente paciente = pacienteDAO.buscarPorNome(nome);
           return paciente;
        } catch (Exception e) {
            System.out.println("Erro ao acessar o banco de dados.");
            return null;
        }
    }

    public boolean cancelarConsulta(int CodigoConsulta){
        try {
            if(recepcionistaDAO.cancelarConsulta(CodigoConsulta)){
                System.out.println("Consulta cancelada.");
                return true;
            }
            else{
                System.out.println("Erro, consulta inexistente.");
                return false;
            }
        }
        catch (SQLException e){
            System.out.println("Erro ao acessar o banco de dados.");
            return false;
        }
    }

    public Recepcionista buscarPorNome(String nome) throws SQLException {
        return recepcionistaDAO.buscarPorNome(nome);
    }

    public Recepcionista buscarPorId(int id) throws SQLException {
        return recepcionistaDAO.buscarPorId(id);
    }

    public Recepcionista buscarPorUsuario(String usuario) throws SQLException {
        return recepcionistaDAO.buscarPorUsuario(usuario);
    }
}