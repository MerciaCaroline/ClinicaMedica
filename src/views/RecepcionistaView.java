package views;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.Time;
// Pacote visão (View)
import java.util.Scanner;


import controllers.RecepcionistaController;
import models.Medico;
import models.Paciente;
import models.Consulta;

public class RecepcionistaView {
    private RecepcionistaController controller;
    private Scanner scanner;

    // Construtor
    public RecepcionistaView(RecepcionistaController controller) {
        this.controller = controller;
        this.scanner = new Scanner(System.in);
    }

    // Método para exibir o menu de opções do recepcionista
    public void exibirMenuRecepcionista() throws ParseException, SQLException {
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
    public void marcarConsulta() throws ParseException, SQLException {

        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
 

        System.out.println("Digite o nome do médico: ");
        String nomeMedico = scanner.next();

        // Buscar o médico no banco de dados
        Medico medico = controller.buscarMedicoPorNome(nomeMedico);
        if (medico == null) {
            System.out.println("Médico não encontrado.");
            return;
        }

        // Solicitar informações do paciente
        System.out.println("Digite o nome do paciente: ");
        String nomePaciente = scanner.next();

        // Buscar o paciente no banco de dados
        Paciente paciente = controller.buscarPacientePorNome(nomePaciente);
        if (paciente == null) {
            System.out.println("Paciente não encontrado.");
            return;
        }

        System.out.println("Digite a data no formato yyyy-MM-dd: ");
        String dataString = scanner.next();
        Date dataConsulta = formato.parse(dataString); 

        System.out.println("Digite a hora no formato HH:mm: ");
        String horaString = scanner.next();
        Date format_hora = sdf.parse(horaString);
        Time horaConsulta = new Time(format_hora.getTime());

        // Solicitar descrição da consulta
        System.out.println("Digite a descricao sobre a consulta: ");
        String descricaoConsulta = scanner.next();
        
        // Criar a consulta
        Consulta consulta = new Consulta(paciente, medico, dataConsulta, horaConsulta, descricaoConsulta);

        // Lógica para marcar a consulta (exemplo: salvar no banco de dados)
        boolean consultaMarcada = controller.marcarConsulta(consulta);
        if (consultaMarcada) {
            System.out.println("Consulta marcada com sucesso.");
        } else {
            System.out.println("Erro ao marcar a consulta.");
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

