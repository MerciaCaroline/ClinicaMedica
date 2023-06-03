package models;

import java.util.Date;

public class Consulta {
    private Paciente paciente;
    private Medico medico;
    private Date data;
    private String descricao;

    public Consulta(Paciente paciente, Medico medico, Date data, String descricao) {
        this.paciente = paciente;
        this.medico = medico;
        this.data = data;
        this.descricao = descricao;
    }

    // Construtor, getters e setters
    public Paciente getPaciente() {
        return this.paciente;
    }

    public Medico getMedico() {
        return this.medico;
    }

    public Date getData() {
        return this.data;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}