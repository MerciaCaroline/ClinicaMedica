package views;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.sql.Time;
import java.util.Scanner;

import controllers.RecepcionistaController;
import models.Medico;
import models.Paciente;
import models.Consulta;

public class RecepcionistaView {
    private RecepcionistaController recepcionistaController;
    private Scanner scanner;

    // Construtor
    public RecepcionistaView(Connection connection) {
        this.recepcionistaController = new RecepcionistaController(connection);
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
 
        scanner.nextLine();
        System.out.println("Digite o nome do médico: ");
        String nomeMedico = scanner.nextLine();

        // Buscar o médico no banco de dados
        Medico medico = recepcionistaController.buscarMedicoPorNome(nomeMedico);
        if (medico == null) {
            System.out.println("Médico não encontrado.");
            return;
        }

        // Solicitar informações do paciente
        scanner.nextLine();
        System.out.println("Digite o nome do paciente: ");
        String nomePaciente = scanner.nextLine();

        // Buscar o paciente no banco de dados
        Paciente paciente = recepcionistaController.buscarPacientePorNome(nomePaciente);
        if (paciente == null) {
            System.out.println("Paciente não encontrado.");
            return;
        }

        System.out.println("Digite a data no formato yyyy-MM-dd: ");
        String dataString = scanner.next();
        Date dataConsulta = new Date(formato.parse(dataString).getTime()); 

        System.out.println("Digite a hora no formato HH:mm: ");
        String horaString = scanner.next();
        Date format_hora = new Date(sdf.parse(horaString).getTime());
        Time horaConsulta = new Time(format_hora.getTime());

        // Solicitar descrição da consulta
        System.out.println("Digite a descricao sobre a consulta: ");
        String descricaoConsulta = scanner.next();
        
        // Criar a consulta
        Consulta consulta = new Consulta(paciente, medico, dataConsulta, horaConsulta, descricaoConsulta);

        // Lógica para marcar a consulta (exemplo: salvar no banco de dados)
        boolean consultaMarcada = recepcionistaController.marcarConsulta(consulta);
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

        boolean sucesso = recepcionistaController.cancelarConsulta(codigoConsulta);

        if (sucesso) {
            System.out.println("Consulta cancelada com sucesso!");
        } else {
            System.out.println("Erro ao cancelar consulta. Verifique o código informado.");
        }
    }
}

