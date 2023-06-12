package controllers;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import dataaccess.ExameDAO;
import models.Exame;
import models.Laboratorio;
import models.Medico;
import models.Paciente;

public class ExameController {
    private ExameDAO exameDAO;
    private Scanner scanner;

    public ExameController(Connection connection) {
        this.exameDAO = new ExameDAO(connection);
        scanner = new Scanner(System.in);
    }

    // CRUD
    public void solicitarExame(String tipo, Paciente paciente, Medico medico, Laboratorio laboratorio) throws SQLException{
        Exame exame = new Exame(paciente, medico, laboratorio, tipo);
        exameDAO.inserir(exame);
    }

    public Exame buscarExame(int id) throws SQLException{
        return exameDAO.buscarPorId(id);
    }

    public List<Exame> buscarTodosExames() throws SQLException{
        return exameDAO.buscarTodos();
    }

    public List<Exame> obterPendentesPorLaboratorio(Laboratorio laboratorio) throws SQLException{
        var exames =  exameDAO.buscarPorLaboratorio(laboratorio.getId());
        return  exames.stream().filter(
            p ->  p.getResultado() == null)
        .collect(Collectors.toList());
    }

    public void removerExame(int id) throws SQLException{
        exameDAO.excluir(id);
    }

    public void enviarResultadoExame(Laboratorio laboratorio) {
        Exame exame = null;
        try {
            List<Exame> exames = obterPendentesPorLaboratorio(laboratorio);

            if(exames.isEmpty()){
                System.out.print("Não possui Exames Pendentes de resultado \n ");
                return;
            }

            System.out.print("Exames Pendentes: \n ");

            for (Exame ex : exames) {
                int id = ex.getId();
                String nomePaciente = ex.getPaciente().getNome(); 
                String tipo = ex.getTipo();
                Date dataSolicitacao = ex.getDataSolicitacao();
                System.out.print(id + " | " +" | "+ tipo +" | "+ dataSolicitacao +" | " + nomePaciente + "\n");
            }
            System.out.print("Informe o numero do exame que deseja disponibilizar resultado: ");
            int exameId = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Digite o resultado do exame: ");
            String resultado = scanner.nextLine();

            exame = exames.stream().filter( p -> p.getId() == exameId).findFirst().get();
            exame.setResultado(resultado);
            this.exameDAO.inserirResultado(exame);
        } 
        catch (Exception e) {
            // TODO: handle exception
        }   
    }

    public List<Exame> consultarResultadosExames(Laboratorio laboratorio) {
        List<Exame> examesEnviados = null;

        try {
            List<Exame> exames =  exameDAO.buscarPorLaboratorio(laboratorio.getId());
            examesEnviados = exames.stream().filter(
                p -> p.getResultado() != null)
            .collect(Collectors.toList());
        } 
        catch (Exception e) {
            // TODO: handle exception
        }

        return examesEnviados;        
    }

    public boolean disponibilizarExameOnline(int codigoExame) {
        try {
            Exame exame = buscarExame(codigoExame);
            exame.setDisponivelOnline(true);
            this.exameDAO.atualizarFlags(exame.getId(), exame.isDisponivelOnline(), exame.isEntreguePaciente());
            return true;
        } 
        catch (Exception e) {
            // TODO: handle exception
        }
        return false;
    }

    public void marcarComoEntregue(Exame exame) {
        try {
            exame.setEntreguePaciente(true);
            this.exameDAO.atualizarFlags(exame.getId(), exame.isDisponivelOnline(), exame.isEntreguePaciente());
        } 
        catch (Exception e) {
            // TODO: handle exception
        }
    }
}
