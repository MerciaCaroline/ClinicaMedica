package controllers;

import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dataaccess.PacienteDAO;
import models.Consulta;
import models.Exame;
import models.Medico;
import models.Paciente;
import models.ResultadoExame;

public class PacienteController {
    private Paciente paciente;
    private List<Consulta> consultas;
    private List<ResultadoExame> resultadosExames;
    private PacienteDAO pacienteDAO;

    public PacienteController() {
        super();
    }

    public PacienteController(Paciente paciente) {
        this.paciente = paciente;
        this.consultas = new ArrayList<>();
        this.resultadosExames = new ArrayList<>();
        this.pacienteDAO = new PacienteDAO();
    }

    // CRUD
    public void cadastrarPaciente(Paciente paciente) throws SQLException{
        pacienteDAO.criar(paciente);
    }

    public Paciente buscarPaciente(int id) throws SQLException{
        return pacienteDAO.ler(id);
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

    public void receberResultadoExame(ResultadoExame resultadoExame) {
        resultadosExames.add(resultadoExame);
    }

    public void cancelarConsulta(Consulta consulta) {
        consultas.remove(consulta);
    }

    public List<Consulta> consultarConsultas() {
        return consultas;
    }

    public List<ResultadoExame> consultarResultadosExames() {
        return resultadosExames;
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