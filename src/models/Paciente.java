package models;

import java.util.ArrayList;
import java.util.List;

public class Paciente extends Usuario {
    private String telefone;
    private String endereco;
    private List<Consulta> historicoConsultas;
    private List<ResultadoExame> resultadosExames;

    public Paciente(String usuario, String senha, String nome, String telefone, String endereco) {
        super(usuario, senha, nome);
        this.telefone = telefone;
        this.endereco = endereco;
        this.historicoConsultas = new ArrayList<>();
        this.resultadosExames = new ArrayList<>();
    }

    public Paciente(int id, String usuario, String senha, String nome, String telefone, String endereco) {
        super(usuario, senha, nome);
        this.id = id;
        this.telefone = telefone;
        this.endereco = endereco;
        this.historicoConsultas = new ArrayList<>();
        this.resultadosExames = new ArrayList<>();
    }

    // Getters and Setters

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public List<Consulta> getHistoricoConsultas() {
        return historicoConsultas;
    }

    public void adicionarConsulta(Consulta consulta) {
        historicoConsultas.add(consulta);
    }

    public List<ResultadoExame> getResultadosExames() {
        return resultadosExames;
    }

    public void adicionarResultadoExame(ResultadoExame resultadoExame) {
        resultadosExames.add(resultadoExame);
    }
}