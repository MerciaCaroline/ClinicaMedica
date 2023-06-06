package controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import models.Consulta;
import models.Exame;
import models.Medico;
import models.Paciente;
import models.ResultadoExame;

public class PacienteController {
    private Paciente paciente;
    private List<Consulta> consultas;
    private List<ResultadoExame> resultadosExames;

    public PacienteController(Paciente paciente) {
        this.paciente = paciente;
        this.consultas = new ArrayList<>();
        this.resultadosExames = new ArrayList<>();
    }

    public void marcarConsulta(Medico medico, Date data, String descricao) {
        Consulta consulta = new Consulta(paciente, medico, data, descricao);
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