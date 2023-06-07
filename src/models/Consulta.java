package models;

import java.util.Date;

public class Consulta {
    private Paciente paciente;
    private Medico medico;
    private Date data;
    private String descricao;
    private int id;

    public Consulta(Paciente paciente, Medico medico, Date data, String descricao) {
        this.paciente = paciente;
        this.medico = medico;
        this.data = data;
        this.descricao = descricao;
    }

    public Consulta(String nomePaciente, Date dataConsulta) {
        this.paciente.setNome(nomePaciente);
        this.data = dataConsulta;
    }

    // Construtor, getters e setters
    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}