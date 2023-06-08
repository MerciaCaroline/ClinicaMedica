package models;

import java.util.Date;
import java.sql.Time;

public class Consulta {
    private Paciente paciente;
    private Medico medico;
    private Date data;
    private Time hora;
    private String observacao;
    private int id;

    public Consulta() {
        super();
    }

    public Consulta(Paciente paciente, Medico medico, Date dataConsulta, Time hora, String observacao) {
        this.paciente = paciente;
        this.medico = medico;
        this.data = dataConsulta;
        this.hora = hora;
        this.observacao = observacao;
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

    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
}