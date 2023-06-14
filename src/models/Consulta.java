package models;

import java.sql.Date;
import java.sql.Time;

public class Consulta {
    private Paciente paciente;
    private Medico medico;
    private Date data;
    private Time hora;
    private String telefone;
    private int id;

    // Construtores
    public Consulta() {
        super();
    }

    public Consulta(Paciente paciente, Medico medico, Date dataConsulta, Time hora, String telefone) {
        this.paciente = paciente;
        this.medico = medico;
        this.data = dataConsulta;
        this.hora = hora;
        this.telefone = telefone;
    }

    public Consulta(String nomePaciente, Date dataConsulta) {
        this.paciente.setNome(nomePaciente);
        this.data = dataConsulta;
    }

    // Getters e setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Medico getMedico() {
        return medico;
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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}