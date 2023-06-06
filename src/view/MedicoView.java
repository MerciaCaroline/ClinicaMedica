package view;

// Pacote visão (View)

import java.util.List;
import java.util.Scanner;

import controllers.MedicoController;

public class MedicoView {
    private MedicoController controller;
    private Scanner scanner;
    
    // Construtor
    public MedicoView(MedicoController controller) {
        this.controller = controller;
        this.scanner = new Scanner(System.in);
    }
    
    // Método para exibir o menu de opções do médico
    public void exibirMenuMedico() {
        int opcao = 0;
        
        do {
            System.out.println("=== MENU DO MÉDICO ===");
            System.out.println("1. Visualizar histórico do paciente");
            System.out.println("2. Autorizar disponibilidade de exame online");
            System.out.println("3. Sair");
            System.out.println("Digite a opção desejada: ");
            opcao = scanner.nextInt();
            
            switch (opcao) {
                case 1:
                    visualizarHistoricoPaciente();
                    break;
                case 2:
                    autorizarExameOnline();
                    break;
                case 3:
                    System.out.println("Saindo do menu do médico...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 3);
    }
    
    // Método para visualizar o histórico do paciente
    public void visualizarHistoricoPaciente() {
        System.out.println("Digite o nome do paciente: ");
        String nomePaciente = scanner.next();
        
        List<String> historico = controller.getHistoricoPaciente(nomePaciente);
        
        if (historico.isEmpty()) {
            System.out.println("Histórico não encontrado para o paciente " + nomePaciente);
        } else {
            System.out.println("=== HISTÓRICO DO PACIENTE ===");
            for (String registro : historico) {
                System.out.println(registro);
            }
        }
    }
    
    // Método para autorizar a disponibilidade de exame online
    public void autorizarExameOnline() {
        System.out.println("Digite o código do exame: ");
        int codigoExame = scanner.nextInt();
        
        controller.autorizarExameOnline(codigoExame);
        
        System.out.println("Exame autorizado para disponibilidade online.");
    }
}

