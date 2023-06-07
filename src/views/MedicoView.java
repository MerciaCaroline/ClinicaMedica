package views;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

// Pacote visão (View)

import java.util.List;
import java.util.Scanner;

import controllers.MedicoController;
import models.Consulta;
import models.Exame;
import models.Medico;
import models.Paciente;

public class MedicoView {
    private MedicoController controller;
    private Scanner scanner;
    private SimpleDateFormat formato;
    private Medico medico;
    
    // Construtor
    public MedicoView(MedicoController controller, Medico medico) {
        this.controller = controller;
        this.scanner = new Scanner(System.in);
        this.medico = medico;
        formato = new SimpleDateFormat("dd/MM/yyyy"); 
    }
    
    // Método para exibir o menu de opções do médico
       // Método para exibir o menu de opções do médico
       public void exibirMenuMedico() throws ParseException {
        int opcao = 0;

        do {
            System.out.println("=== MENU DO MÉDICO ===");
            System.out.println("1. Visualizar histórico de pacientes");
            System.out.println("2. Registrar histórico de paciente");
            System.out.println("3. Consultar resultados de exames pendentes");
            System.out.println("4. Consultar consultas agendadas");
            System.out.println("5. Autorizar exame");
            System.out.println("6. Registrar consulta");
            System.out.println("7. Sair");
            System.out.println("Digite a opção desejada: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    visualizarHistoricoPacientes();
                    break;
                case 2:
                    registrarHistoricoPaciente();
                    break;
                case 3:
                    consultarResultadosExamesPendentes();
                    break;
                case 4:
                    consultarConsultasAgendadas();
                    break;
                case 5:
                    autorizarExame();
                    break;
                case 6:
                    registrarConsulta();
                    break;
                case 7:
                    System.out.println("Saindo do menu do médico...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 7);
    }
    
    // Método para visualizar o histórico do paciente
    public void visualizarHistoricoPacientes() {
        System.out.println("Digite o nome do paciente: ");
        String nomePaciente = scanner.next();
        
        List<Paciente> historico = controller.getHistoricoPacientes();
        
        if (historico.isEmpty()) {
            System.out.println("Histórico não encontrado para o paciente " + nomePaciente);
        } else {
            System.out.println("=== HISTÓRICO DO PACIENTE ===");
            for (Paciente paciente : historico) {
                System.out.println("Nome: " + paciente.getNome());
                System.out.println("Telefone: " + paciente.getTelefone());
                System.out.println("Endereço: " + paciente.getEndereco());
                System.out.println("Histórico: " + paciente.getHistoricoPaciente());
            }
        }
    }

    // Método para registrar o histórico de um paciente
    public void registrarHistoricoPaciente() {
        System.out.println("Digite o nome do paciente: ");
        String nomePaciente = scanner.next();

        System.out.println("Digite o histórico do paciente: ");
        String historicoPaciente = scanner.next();

        controller.registrarHistoricoPaciente(nomePaciente, historicoPaciente);

        System.out.println("Histórico registrado com sucesso!");
    }

    // Método para consultar os resultados de exames pendentes
    public void consultarResultadosExamesPendentes() {
        List<Exame> examesPendentes = controller.consultarResultadosExames(this.medico);

        if (examesPendentes.isEmpty()) {
            System.out.println("Não há resultados de exames pendentes para você.");
        } else {
            System.out.println("=== RESULTADOS DE EXAMES PENDENTES ===");
            for (Exame exame : examesPendentes) {
                System.out.println("Paciente: " + exame.getPaciente().getNome());
                System.out.println("Telefone: " + exame.getPaciente().getTelefone());
                System.out.println("Médico: " + exame.getMedico().getNome());
                System.out.println("Tipo do Exame: " + exame.getTipo());
                System.out.println("Data: " + exame.getData());
                System.out.println("Exame autorizado: " + exame.getAutorizado());
            }
        }
    }

    // Método para consultar as consultas agendadas do médico
    public void consultarConsultasAgendadas() {
        List<Consulta> consultasAgendadas = controller.consultarConsultas(this.medico);

        if (consultasAgendadas.isEmpty()) {
            System.out.println("Não há consultas agendadas para você.");
        } else {
            System.out.println("=== CONSULTAS AGENDADAS ===");
            for (Consulta consulta : consultasAgendadas) {
                System.out.println("Paciente: " + consulta.getPaciente().getNome());
                System.out.println("Data: " + consulta.getData());
                if(consulta.getDescricao() != null){
                    System.out.println("Descrição: " + consulta.getDescricao());
                } else{
                    System.out.println("Descrição: Não há descrição");
                }
            }
        }
    }
    
    // Método para autorizar a disponibilidade de exame online
    public void autorizarExame() {
        System.out.println("Digite o código do exame: ");
        int codigoExame = scanner.nextInt();

        if (controller.autorizarExame(codigoExame)){
            System.out.println("Exame autorizado para disponibilidade .");
        }
        else{
            System.out.println("Exame não encontrado.");
        }  
    }

    // Método para registrar uma nova consulta
    public void registrarConsulta() throws ParseException {
        System.out.println("Digite o nome do paciente: ");
        String nomePaciente = scanner.next();

        System.out.println("Digite a data da consulta: ");
        Date dataConsulta = formato.parse(scanner.next());

        Consulta consulta = new Consulta(nomePaciente, dataConsulta);

        controller.registrarConsulta(consulta);

        System.out.println("Consulta registrada com sucesso!");
    }
}

