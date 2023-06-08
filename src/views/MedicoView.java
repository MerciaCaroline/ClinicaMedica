package views;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

// Pacote visão (View)

import java.util.Scanner;

import controllers.MedicoController;
import controllers.PacienteController;
import models.Paciente;
import models.ResultadoExame;

public class MedicoView {
    private MedicoController controller;
    private PacienteController pacienteController;
    private Scanner scanner;
    private SimpleDateFormat formato;
    
    // Construtor
    public MedicoView(MedicoController controller) {
        this.controller = controller;
        this.pacienteController = new PacienteController();
        this.scanner = new Scanner(System.in);
        formato = new SimpleDateFormat("dd/MM/yyyy"); 
    }
    
    // Método para exibir o menu de opções do médico
       // Método para exibir o menu de opções do médico
       public void exibirMenuMedico() throws ParseException, SQLException {
        int opcao = 0;

        do {
            System.out.println("=== MENU DO MÉDICO ===");
            System.out.println("1. Autorizar exame");
            System.out.println("2. Registrar histórico de paciente");
            System.out.println("3. Consultar resultados de exames");
            System.out.println("4. Sair");
            System.out.println("Digite a opção desejada: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    autorizarExame();
                    break;
                case 2:
                    registrarHistoricoPaciente();
                    break;
                case 3:
                    receberResultadoExame();
                    break;
                case 4:
                    System.out.println("Saindo do menu do médico...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 7);
    }

    public void autorizarExame() throws SQLException {
        System.out.println("Digite o código do exame: ");
        int codigoExame = scanner.nextInt();

        if (controller.autorizarExame(codigoExame)){
            System.out.println("Exame autorizado para disponibilidade .");
        }
        else{
            System.out.println("Exame não encontrado.");
        }  
    }

    public void registrarHistoricoPaciente() throws SQLException, ParseException {
        System.out.println("Digite o nome do paciente: ");
        String nomePaciente = scanner.next();

        System.out.println("Digite o histórico do paciente: ");
        String historicoPaciente = scanner.next();

        if(controller.registrarHistoricoPaciente(nomePaciente, historicoPaciente)){
            System.out.println("Histórico registrado com sucesso!");
        }
        else{
            System.out.println("Paciente não encontrado!");
            System.out.println("Deseja cadastrá-lo? (S/N)");
            String opcao = scanner.next();

            switch (opcao.toUpperCase()) {
                case "S":
                    PacienteController pacienteController = new PacienteController();
                    pacienteController.cadastrarPaciente(new Paciente(nomePaciente, historicoPaciente));
                break;
                case "N":
                    System.out.println("Voltando ao menu do médico...");
                    exibirMenuMedico();
                break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
                break;
            }
        }
    }

    public void receberResultadoExame() throws SQLException{
        System.out.println("Digite o código do exame: ");
        int exameId = scanner.nextInt();

        ResultadoExame resultadoExame = controller.consultarResultadoExame(exameId);

        if(resultadoExame != null){
            System.out.println("======== RESULTADOS DO EXAME ========");
            System.out.println("Tipo: " + resultadoExame.getTipo());
            System.out.println("Data Resultado: " + formato.format(resultadoExame.getDataResultado()));
            System.out.println("Médico: " + controller.buscarMedicoPorId(resultadoExame.getMedico()));
            System.out.println("Paciente: " + pacienteController.buscarPaciente(resultadoExame.getPaciente()));
            System.out.println("Resultado:" + resultadoExame.getResultado());
            System.out.println("====================================\n");
            
        }
    }
}

