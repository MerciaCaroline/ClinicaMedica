package views;

import java.util.Scanner;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import controllers.ExameController;
import models.Exame;

public class PacienteView {
    private Scanner scanner;
    private Connection connection;

    public PacienteView(Connection connection) {
        this.scanner = new Scanner(System.in);
        this.connection = connection;
    }

    public void exibirMenuPaciente() throws ParseException, SQLException {
        int opcao = 0;

        do {
            System.out.println("=== MENU DO PACIENTE ===");
            System.out.println("1. Consultar resultados de exames");
            System.out.println("2. Sair");
            System.out.println("Digite a opção desejada: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    receberResultadoExame();
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 2);
    }

    public void receberResultadoExame() {
        System.out.println("Digite o código do resultado do exame: ");
        int codigoExame = scanner.nextInt();

        try {
            ExameController exameController = new ExameController(connection);
            Exame exame = exameController.buscarExame(codigoExame);
            if (exame != null) {
                System.out.println("Resultado de exame recebido com sucesso: " + exame.getResultado());                
            }
            else{
                System.out.println("Exame não encontrado, verifique o código informado.\n\n3");   
            }
        } 
        catch (Exception e) {
            // TODO: handle exception
        }
    }
}