package controllers;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import dataaccess.PacienteDAO;
import models.Consulta;
import models.Exame;
import models.Medico;
import models.Paciente;

public class PacienteController {
    private Paciente paciente;
    private List<Consulta> consultas;
    private PacienteDAO pacienteDAO;
    private Scanner scanner;

    public PacienteController(Connection connection) {
        this.pacienteDAO = new PacienteDAO(connection);
    }

    public PacienteController(Paciente paciente) {
        this.paciente = paciente;
        this.consultas = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    // CRUD
    public void cadastrarPaciente(String nome) throws SQLException
    {
        System.out.println("\n Digite a idade: ");
        int idade = scanner.nextInt();

        System.out.println("\n Digite o cpf: ");
        String cpf = scanner.nextLine();

        System.out.println("\n Digite o telefone: ");
        String telefone = scanner.nextLine();

        System.out.println("\n Digite o email: ");
        String email = scanner.nextLine();

        System.out.println("\n Digite o endereco: ");
        String endereco = scanner.nextLine();

        System.out.println("\n Exolha o sexo (M) - Masculino, (F) - Feminino: ");
        String sexo = scanner.nextLine();
        
        System.out.println("\n Escolha um usuario: ");
        String usuario = scanner.nextLine();

        System.out.println("\n Escolha uma senha: ");
        String senha = scanner.nextLine();


        Paciente paciente = new Paciente(usuario, senha, nome, telefone, cpf, sexo.charAt(0), email, idade, endereco);
        pacienteDAO.criar(paciente);
    }

    public Paciente buscarPaciente(int id) throws SQLException{
        return pacienteDAO.buscarPorId(id);
    }

    public List<Paciente> buscarTodosPacientes() throws SQLException{
        return pacienteDAO.lerTodos();
    }

    public Paciente buscarPacientePorNome(String nome) throws SQLException{
        return pacienteDAO.buscarPorNome(nome);
    }

    public void atualizarPaciente(Paciente paciente) throws SQLException{
        pacienteDAO.atualizar(paciente);
    }

    public void removerPaciente(int id) throws SQLException{
        pacienteDAO.excluir(id);
    }

    // Outros métodos
    public void marcarConsulta(Medico medico, Date data, Time horarioConsulta, String descricao) {
        Consulta consulta = new Consulta(paciente, medico, data, horarioConsulta, descricao);
        consultas.add(consulta);
    }
    public void cancelarConsulta(Consulta consulta) {
        consultas.remove(consulta);
    }

    public List<Consulta> consultarConsultas() {
        return consultas;
    }

    public List<Exame> solicitarExames(List<String> tiposExame) {
        List<Exame> examesSolicitados = new ArrayList<>();
        // Lógica para solicitar exames ao médico ou laboratório
        // Criação dos objetos Exame e adição na lista examesSolicitados
        return examesSolicitados;
    }

    public void autorizarExamesOnline() {
        // Lógica para autorizar que os resultados de exames sejam disponibilizados online
        // Atualizar os atributos dos ResultadoExame correspondentes
    }
}