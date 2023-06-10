package views;

import java.util.List;
import java.util.Scanner;

import controllers.LaboratorioController;
import models.ResultadoExame;

public class LaboratorioView {
    
    private Scanner scanner;
    private LaboratorioController controller;
    
    public LaboratorioView(LaboratorioController controller) {
        scanner = new Scanner(System.in);
        this.controller = controller;
    }
    
    public void exibirMenu() {
        int opcao = 0;
        
        while (opcao != 3) {
            System.out.println("----- Menu do Laboratório -----");
            System.out.println("1. Enviar Resultado do Exame");
            System.out.println("2. Consultar Exames Enviados");
            System.out.println("3. Sair");
            System.out.print("Escolha uma opção: ");
            
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer
            
            switch (opcao) {
                case 1:
                    // pedir pra digitar os resultados e enviar;
                    //controller.EnviarResultadoExame();
                    break;
                case 2:
                    List<ResultadoExame> resultados = controller.consultarResultadosExames();
                    // exibir os resultados
                    break;
                case 3:
                    System.out.println("Encerrando o programa.");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
            
            System.out.println();
        }
    }
}
