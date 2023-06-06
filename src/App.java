import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean sair = false;

        System.out.println("\nBem Vindo! \n\n");

        while (!sair) {
            System.out.println("Escolha uma função:\n1 - Paciente\n2 - Médico\n3 - Recepcionista\n4 - Financeiro\n5 - Laboratório\n6 - RH\n7 - Admin\n");

            int escolha = scanner.nextInt();

            switch (escolha) {
                case 1:
                    System.out.println("Para visualizar o resultado do seu exame, digite o código disponibilizado pelo seu médico:");
                    String codigo = scanner.next();
                    if (codigo == "123"){
                        System.out.println("Aqui está o resultado do seu exame");
                    }
                    else{
                        System.out.println("Exame não encontrado, confira o código digitado e tente novamente. Caso seja necessário entre em contato com seu médico");
                    }
                    // Faça algo relacionado ao paciente
                    break;
                case 2:
                    System.out.println("Você escolheu a função: Médico");
                    // Faça algo relacionado ao médico
                    break;
                case 3:
                    System.out.println("Você escolheu a função: Recepcionista");

                    System.out.println("Bem-vindo! Por favor, faça login.");
                    boolean autenticado = fazerLogin();

                    if (autenticado) {
                        System.out.println("Login bem-sucedido! Acesso concedido.");
                        // Faça algo após o login bem-sucedido
                        // Faça algo relacionado à recepcionista
                    } else {
                        System.out.println("Falha no login! Acesso negado.");
                        // Faça algo após a falha no login
                    }
                    break;
                case 4:
                    System.out.println("Você escolheu a função: Financeiro");
                    // Faça algo relacionado ao setor financeiro
                    break;
                case 5:
                    System.out.println("Você escolheu a função: Laboratório");
                    // Faça algo relacionado ao laboratório
                    break;
                case 6:
                    System.out.println("Você escolheu a função: RH");
                    // Faça algo relacionado ao setor de RH
                    break;
                case 7:
                    System.out.println("Você escolheu a função: Admin");
                    // Faça algo relacionado à administração
                    break;
                default:
                    System.out.println("Escolha inválida! Por favor, escolha um número de 1 a 7.");
                    break;
            }
            System.out.println("Pressione 'Esc' para sair ou qualquer outra tecla para continuar...");
            String tecla = scanner.next();
            if (tecla.equalsIgnoreCase("Esc")) {
                sair = true;
            }
        }
        scanner.close();
    }

    public static boolean fazerLogin() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Usuário: ");
        String usuario = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        // Verifique as credenciais do usuário e senha
        // Neste exemplo, faremos uma verificação simples com valores fixos
        String usuarioCorreto = "admin";
        String senhaCorreta = "123456";

        return usuario.equals(usuarioCorreto) && senha.equals(senhaCorreta);
    }
}
