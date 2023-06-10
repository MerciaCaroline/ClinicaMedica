import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import controllers.LaboratorioController;
import controllers.MedicoController;
import controllers.PacienteController;
import controllers.RecepcionistaController;
import views.LaboratorioView;
import views.MedicoView;
import views.PacienteView;
import views.RecepcionistaView;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean sair = false;

        System.out.println("\nBem Vindo! \n\n");

        try {
            PostgresDBConnection database = new PostgresDBConnection();
            Connection connection = database.getConnection();
            // Use a conexão para realizar operações no banco de dados PostgreSQL
            // ...

            while (!sair) {
                System.out.println("Escolha uma função:\n1 - Paciente\n2 - Médico\n3 - Recepcionista\n4 - Laboratório\n\n");
    
                int escolha = scanner.nextInt();
    
                switch (escolha) {
                    case 1:
                        System.out.println("Você escolheu a função: Paciente");
                        PacienteView pacienteView = new PacienteView(connection);
                        try {
                            pacienteView.exibirMenuPaciente();                           
                        } 
                        catch (Exception e) {
                            // TODO: handle exception
                        }
                        break;
                    case 2:
                        System.out.println("Você escolheu a função: Médico");

                        if (fazerLogin(2, connection)) {
                            System.out.println("Login bem-sucedido! Acesso concedido.");
                            MedicoView medicoView = new MedicoView(connection);
                            try {
                                medicoView.exibirMenuMedico();                            
                            } 
                            catch (Exception e) {

                                // TODO: handle exception
                            }
                        } 
                        else {
                            System.out.println("Falha no login! Acesso negado.");
                            // Faça algo após a falha no login
                        }
                        break;
                    case 3:
                        System.out.println("Você escolheu a função: Recepcionista");

                        if (fazerLogin(3, connection)) {
                            System.out.println("Login bem-sucedido! Acesso concedido.");
                            RecepcionistaView recepcionistaView = new RecepcionistaView(connection);
                            try {
                                recepcionistaView.exibirMenuRecepcionista();                 
                            } 
                            catch (Exception e) {

                                // TODO: handle exception
                            }
                        } 
                        else {
                            System.out.println("Falha no login! Acesso negado.");
                            // Faça algo após a falha no login
                        }
                        break;
                    case 4:
                        System.out.println("Você escolheu a função: Laboratório");

                        if (fazerLogin(4, connection)) {
                            System.out.println("Login bem-sucedido! Acesso concedido.");
                            LaboratorioController laboratorioController = new LaboratorioController(connection);
                            LaboratorioView LaboratorioView = new LaboratorioView(laboratorioController);
                            try {
                                LaboratorioView.exibirMenu();                 
                            } 
                            catch (Exception e) {

                                // TODO: handle exception
                            }
                        } 
                        else {
                            System.out.println("Falha no login! Acesso negado.");
                            // Faça algo após a falha no login
                        }
                        break;
                    default:
                        System.out.println("Escolha inválida! Por favor, escolha um número de 1 a 4");
                        break;
                }
                System.out.println("Pressione '0' para sair ou qualquer outra tecla para continuar...");
                String tecla = scanner.next();
                if (tecla.equalsIgnoreCase("0")) {
                    sair = true;
                }
            }
            scanner.close();
            connection.close();
            System.out.println("Conexão com o banco de dados fechada.");
        } catch (SQLException e) {
            e.printStackTrace();
        }        
    }

    public static boolean fazerLogin(int tipoUsuario, Connection connection) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Bem-vindo! Por favor, faça login.");

            System.out.print("Usuario: ");
            String usuario = scanner.nextLine();
            System.out.print("Senha: ");
            String senha = scanner.nextLine();

            String usuarioFake = "admin";
            String SenhaFake = "123456";

            switch (tipoUsuario) {
                case 2:
                    //Acessar MEDICODAO para validar usuario e senha
                    return usuario.equals(usuarioFake) && senha.equals(SenhaFake);
                case 3:
                    //Acessar RECEPCIONISTADAO para validar usuario e senha
                    return usuario.equals(usuarioFake) && senha.equals(SenhaFake);
                default:
                    return false;
            }
        }
    }
}
