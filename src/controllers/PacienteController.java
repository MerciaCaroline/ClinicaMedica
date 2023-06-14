package controllers;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import dataaccess.ExameDAO;
import dataaccess.PacienteDAO;
import models.Exame;
import models.Paciente;

public class PacienteController {
    private PacienteDAO pacienteDAO;
    private ExameDAO exameDAO;
    private Scanner scanner;

    public PacienteController(Connection connection) {
        this.pacienteDAO = new PacienteDAO(connection);
        this.exameDAO = new ExameDAO(connection);
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
    public Exame solicitarExame(int exameId) throws SQLException {
        return exameDAO.buscarPorId(exameId);
    }

    public void setPacienteDAO(PacienteDAO pacienteDAO) {
        this.pacienteDAO = pacienteDAO;
    }

    public PacienteDAO getPacienteDAO() {
        return pacienteDAO;
    }

    public void setExameDAO(ExameDAO exameDAO) {
        this.exameDAO = exameDAO;
    }

    public ExameDAO getExameDAO() {
        return exameDAO;
    }
}