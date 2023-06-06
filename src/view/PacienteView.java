package view;

import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import controllers.MedicoController;
import controllers.PacienteController;
import models.Consulta;
import models.Exame;
import models.Medico;
import models.Paciente;
import models.ResultadoExame;

public class PacienteView {
    private PacienteController controller;
    private Scanner scanner;

    public PacienteView(PacienteController controller) {
        this.controller = controller;
        this.scanner = new Scanner(System.in);
    }

    public void exibirMenuPaciente() throws ParseException {
        int opcao = 0;

        do {
            System.out.println("=== MENU DO PACIENTE ===");
            System.out.println("1. Marcar consulta");
            System.out.println("2. Receber resultado de exame");
            System.out.println("3. Cancelar consulta");
            System.out.println("4. Consultar consultas marcadas");
            System.out.println("5. Consultar resultados de exames");
            System.out.println("6. Solicitar exames");
            System.out.println("7. Autorizar exames online");
            System.out.println("8. Sair");
            System.out.println("Digite a opção desejada: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    marcarConsulta();
                    break;
                case 2:
                    receberResultadoExame();
                    break;
                case 3:
                    cancelarConsulta();
                    break;
                case 4:
                    consultarConsultas();
                    break;
                case 5:
                    consultarResultadosExames();
                    break;
                case 6:
                    solicitarExames();
                    break;
                case 7:
                    autorizarExamesOnline();
                    break;
                case 8:
                    System.out.println("Saindo do menu do paciente...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 8);
    }

    public void marcarConsulta() throws ParseException {
        System.out.println("Digite o nome do médico: ");
        String nomeMedico = scanner.next();
        Medico medico = MedicoController.getMedicoPorNome(nomeMedico);
        // Lógica adicional para obter o médico a partir do nome

        System.out.println("Digite a data da consulta (dd/mm/aaaa): ");
        String dataStr = scanner.next();
        // Lógica adicional para converter a data de string para Date

        System.out.println("Digite a descrição da consulta: ");
        String descricao = scanner.next();

        Date dataConsulta = new SimpleDateFormat("dd/MM/yyyy").parse(dataStr);
        controller.marcarConsulta(medico, dataConsulta, descricao); 

        System.out.println("Consulta marcada com sucesso.");
    }

    public void receberResultadoExame() {
        System.out.println("Digite o código do resultado do exame: ");
        int codigoResultadoExame = scanner.nextInt();
        // Lógica adicional para obter o resultado do exame a partir do código

        ResultadoExame resultadoExame = new ResultadoExame(/* parâmetros do resultado do exame */);
        controller.receberResultadoExame(resultadoExame);

        System.out.println("Resultado de exame recebido com sucesso.");
    }

    public void cancelarConsulta() {
        System.out.println("Digite o código da consulta: ");
        int codigoConsulta = scanner.nextInt();
        // Lógica adicional para obter a consulta a partir do código

        Consulta consulta = new Consulta(/* parâmetros da consulta */);
        controller.cancelarConsulta(consulta);

        System.out.println("Consulta cancelada com sucesso.");
    }

    public void consultarConsultas() {
        List<Consulta> consultas = controller.consultarConsultas();

        if (consultas.isEmpty()) {
            System.out.println("Nenhuma consulta encontrada.");
        } else {
            System.out.println("=== CONSULTAS MARCADAS ===");
            for (Consulta consulta : consultas) {
                System.out.println(consulta);
            }
        }
    }

    public void consultarResultadosExames() {
        List<ResultadoExame> resultadosExames = controller.consultarResultadosExames();

        if (resultadosExames.isEmpty()) {
            System.out.println("Nenhum resultado de exame encontrado.");
        } else {
            System.out.println("=== RESULTADOS DE EXAMES ===");
            for (ResultadoExame resultadoExame : resultadosExames) {
                System.out.println(resultadoExame);
            }
        }
    }

    public void solicitarExames() {
        // Lógica para solicitar exames
        // Código adicional para criar e enviar solicitações de exames
        System.out.println("Exames solicitados com sucesso.");
    }

    public void autorizarExamesOnline() {
        controller.autorizarExamesOnline();
        System.out.println("Exames autorizados para disponibilidade online.");
    }
}