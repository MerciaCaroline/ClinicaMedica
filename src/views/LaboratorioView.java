package views;

import java.sql.Connection;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import controllers.ExameController;
import models.Exame;
import models.Laboratorio;

public class LaboratorioView {
    
    private Scanner scanner;
    private ExameController exameController;
    private Laboratorio laboratorio;
    
    public LaboratorioView(Connection connection, Laboratorio laboratorio) {
        scanner = new Scanner(System.in);
        this.exameController = new ExameController(connection);
        this.laboratorio = laboratorio;
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
                    this.exameController.enviarResultadoExame(laboratorio);
                    break;
                case 2:
                    List<Exame> resultados = this.exameController.consultarResultadosExames(laboratorio);
                    
                    System.out.print("Exames enviados \n: ");

                    for (Exame exame : resultados) {
                        int id = exame.getId();
                        String nomePaciente = exame.getPaciente().getNome(); 
                        String tipo = exame.getTipo();
                        Date dataResultado = exame.getDataResultado();
                        String resultado = exame.getResultado();
                        System.out.print(id + " | " +" | "+ tipo +" | " + nomePaciente + " | " + dataResultado + " | "+ resultado + "\n");
                    }
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
