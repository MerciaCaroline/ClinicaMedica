package views;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

// Pacote visão (View)

import java.util.Scanner;
import java.util.stream.Collectors;

import controllers.ExameController;
import controllers.LaboratorioController;
import controllers.MedicoController;
import controllers.PacienteController;
import models.Exame;
import models.Laboratorio;
import models.Medico;
import models.Paciente;

public class MedicoView {
    private Medico medico;
    private ExameController exameController;
    private PacienteController pacienteController;
    private MedicoController medicoController;
    private LaboratorioController laboratorioController;
    private SimpleDateFormat formato;
    private Paciente paciente;
    private Scanner scanner;

    // Construtor
    public MedicoView(Connection connection, Medico medico) {
        this.medico = medico;
        this.exameController = new ExameController(connection);
        this.medicoController = new MedicoController(connection, medico);
        this.pacienteController = new PacienteController(connection);
        this.laboratorioController = new LaboratorioController(connection);
        formato = new SimpleDateFormat("dd/MM/yyyy"); 
        this.scanner = new Scanner(System.in);
    }
    
    // Método para exibir o menu de opções do médico
    public void exibirMenuMedico() throws ParseException, SQLException {     
        System.out.println("Digite o nome do paciente que está em atendimento: ");
        String nomePaciente = scanner.nextLine();
        //String nomePaciente = "Fulano de Tal";
        paciente = this.pacienteController.buscarPacientePorNome(nomePaciente);

        if(paciente == null){
            System.out.println("Paciente não encontrado!");
            System.out.println("Deseja cadastrá-lo? (S/N)");
            String opcao = scanner.next();

            switch (opcao.toUpperCase()) {
                case "S":
                    pacienteController.cadastrarPaciente(nomePaciente);
                    paciente = pacienteController.buscarPacientePorNome(nomePaciente);
                    break;
                case "N":
                    System.out.println("Voltando ao menu do médico...");
                    return;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
                    return;
            }
        }
        
        int opcao = 0;    

        do {
            System.out.println("=== MENU DO MÉDICO ===\n");   
            System.out.println("1. Autorizar exame para consulta online");
            System.out.println("2. Registrar histórico de paciente");
            System.out.println("3. Consultar resultados de exames por paciente");
            System.out.println("4. Solicitar exame para o paciente");
            System.out.println("5. Sair");
            System.out.println("Digite a opção desejada: ");
            opcao = scanner.nextInt();
            
            switch (opcao) {
                case 1: //Autorizar exame online
                    List<Exame> exames = this.exameController.buscarTodosExames()
                        .stream().filter( p ->  
                            p.getMedico().getId() == this.medico.getId()
                            & p.isDisponivelOnline() == false 
                            & p.getPaciente().getId() == paciente.getId())
                        .collect(Collectors.toList());
                        
                    for (Exame ex : exames) {
                        int id = ex.getId();
                        String tipo = ex.getTipo();
                        Date dataSolicitacao = ex.getDataSolicitacao();
                        System.out.print(id + " | " + tipo +" | "+ dataSolicitacao +" | " + "\n");
                    }

                    System.out.println(this.medicoController.autorizarExameOnline());
                    break;
                case 2: // Registrar historico Paciente              
                    System.out.println("Digite o histórico do paciente: ");
                    String historicoPaciente = scanner.nextLine();

                    medicoController.registrarHistoricoPaciente(paciente, historicoPaciente);
                    break;
                case 3: // Consultar exames
                    List<Exame> examesPaciente = this.medicoController.consultarResultadosExames(medico, paciente);

                    for (Exame ex : examesPaciente) {
                        int id = ex.getId();
                        String tipo = ex.getTipo();
                        Date dataResultado = ex.getDataResultado();
                        String resultado = ex.getResultado();
                        System.out.print(id + " | " + tipo +" | "+ dataResultado +" | " + resultado + "\n");
                    }
                    break;
                case 4: // Solicitacao de exame                        
                    System.out.println("Em qual laboratorio deseja solicitar um exame? Digite a opção desejada: ");
                    List<Laboratorio> laboratorios = this.laboratorioController.buscarTodos();
                    for (Laboratorio laboratorio : laboratorios) {
                        int id = laboratorio.getId();
                        String nome = laboratorio.getNome();
                        System.out.print(id + " | " + nome+"\n");
                    }                        
                    int laboratorio_id = scanner.nextInt();
                    Laboratorio laboratorioEscolhido = laboratorios
                        .stream()
                        .filter( p ->  
                            p.getId() == laboratorio_id)
                        .findFirst().get();
                                    
                    System.out.println("Digite o tipo de exame que deseja solicitar: ");
                    String tipoExame = scanner.nextLine();

                    this.exameController.solicitarExame(tipoExame, paciente, medico, laboratorioEscolhido);
                    break;
                case 5:
                    System.out.println("Saindo do menu do médico...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }            
        } while (opcao != 5);
    }
}

