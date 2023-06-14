package controllers;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import dataaccess.HistoricoPacienteDAO;
import dataaccess.MedicoDAO;
import models.Exame;
import models.HistoricoPaciente;
import models.Medico;
import models.Paciente;

public class MedicoController {
    public ExameController exameController;
    private MedicoDAO medicoDAO;
    private HistoricoPacienteDAO historicoDAO;
    private Scanner scanner;
    private Medico medico;

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public MedicoController(Connection connection) {
        medicoDAO = new MedicoDAO(connection);
        exameController = new ExameController(connection);
        historicoDAO = new HistoricoPacienteDAO(connection);
        this.scanner = new Scanner(System.in);
    }

    public MedicoController(Connection connection, Medico medico) {
        exameController = new ExameController(connection);
        medicoDAO = new MedicoDAO(connection);
        historicoDAO = new HistoricoPacienteDAO(connection);
        this.scanner = new Scanner(System.in);
        this.medico = medico;
    }
    
    public boolean registrarHistoricoPaciente(Paciente paciente, String historicoPaciente) throws SQLException {
        if(paciente != null){        
            HistoricoPaciente historico = new HistoricoPaciente(paciente, historicoPaciente);
            this.historicoDAO.adicionarHistoricoPaciente(historico);
            return true;
        }

        return false;
    }

    public List<Exame> consultarResultadosExames(Medico medico, Paciente paciente) 
    {    
        List<Exame> examesComResultados = null;
        try {
            List<Exame> exames =  this.exameController.buscarTodosExames();
            examesComResultados = exames.stream().filter(
                p -> p.getResultado() != null 
                & p.getMedico().getId() == medico.getId() 
                & p.getPaciente().getId() == paciente.getId())
            .collect(Collectors.toList());
        } 
        catch (Exception e) {
            // TODO: handle exception
        }

        return examesComResultados;
    }

    public Medico buscarMedicoPorNome(String nome) throws SQLException {
        return medicoDAO.buscarPorNome(nome);
    }

    public Medico buscarMedicoPorId(int id) throws SQLException {
        return medicoDAO.buscarPorId(id);
    }

    public Medico buscarMedicoPorUsuario(String usuario) throws SQLException {
        return medicoDAO.buscarPorUsuario(usuario);
    }

    public String autorizarExameOnline() throws SQLException {
        System.out.println("Digite o código do exame: ");
        int codigoExame = scanner.nextInt();

        Exame exame = this.exameController.buscarExame(codigoExame);

        if(exame == null | exame.getMedico().getId() != this.medico.getId()){
            return "Exame não encontrado.";
        }
        else if(exame.isDisponivelOnline() | exameController.disponibilizarExameOnline(codigoExame))
        {
            return "Exame autorizado para disponibilidade .";
        }
        else{
            return "Não foi possivel disponibilizar o exame";
        }
    }

    public void setHistoricoDAO(HistoricoPacienteDAO historicoDAO) {
        this.historicoDAO = historicoDAO;
    }

    public HistoricoPacienteDAO getHistoricoDAO() {
        return historicoDAO;
    }

    public void setMedicoDAO(MedicoDAO medicoDAO) {
        this.medicoDAO = medicoDAO;
    }

    public MedicoDAO getMedicoDAO() {
        return medicoDAO;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public Scanner getScanner() {
        return scanner;
    }
}
