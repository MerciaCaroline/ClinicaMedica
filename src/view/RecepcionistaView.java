package view;
// Pacote visão (View)
import java.util.Scanner;

import controllers.RecepcionistaController;

public class RecepcionistaView {
    private RecepcionistaController controller;
    private Scanner scanner;

    // Construtor
    public RecepcionistaView(RecepcionistaController controller) {
        this.controller = controller;
        this.scanner = new Scanner(System.in);
    }

    // Método para exibir o menu de opções do recepcionista
    public void exibirMenuRecepcionista() {
        int opcao = 0;

        do {
            System.out.println("=== MENU DO RECEPCIONISTA ===");
            System.out.println("1. Marcar consulta");
            System.out.println("2. Cancelar consulta");
            System.out.println("3. Sair");
            System.out.println("Digite a opção desejada: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    marcarConsulta();
                    break;
                case 2:
                    cancelarConsulta();
                    break;
                case 3:
                    System.out.println("Saindo do menu do recepcionista...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 3);
    }

    // Método para marcar uma nova consulta
    public void marcarConsulta() {
        System.out.println("Digite o nome do médico: ");
        String nomeMedico = scanner.next();

        System.out.println("Digite o nome do paciente: ");
        String nomePaciente = scanner.next();

        System.out.println("Digite a data da consulta (no formato yyyy-MM-dd): ");
        String dataConsulta = scanner.next();

        System.out.println("Digite o horário da consulta (no formato HH:mm): ");
        String horarioConsulta = scanner.next();

        boolean sucesso = controller.marcarConsulta(nomeMedico, nomePaciente, dataConsulta, horarioConsulta);

        if (sucesso) {
            System.out.println("Consulta marcada com sucesso!");
        } else {
            System.out.println("Erro ao marcar consulta. Verifique os dados informados.");
        }
    }

    // Método para cancelar uma consulta
    public void cancelarConsulta() {
        System.out.println("Digite o código da consulta: ");
        int codigoConsulta = scanner.nextInt();

        boolean sucesso = controller.cancelarConsulta(codigoConsulta);

        if (sucesso) {
            System.out.println("Consulta cancelada com sucesso!");
        } else {
            System.out.println("Erro ao cancelar consulta. Verifique o código informado.");
        }
    }
}
