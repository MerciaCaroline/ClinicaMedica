package models;

import java.util.Date;

public class ResultadoExame {
    private int id;
    private Paciente paciente;
    private Medico medico;
    private String tipo;
    private Date dataResultado;
    private String resultado;

    // Construtor, getters e setters
    public int getId() {
        return this.id;
    }

    public Paciente getPaciente() {
        return this.paciente;
    }

    public Medico getMedico() {
        return this.medico;
    }

    public String getTipo() {
        return this.tipo;
    }

    public Date getDataResultado() {
        return this.dataResultado;
    }

    public String getResultado() {
        return this.resultado;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setDataResultado(Date dataResultado) {
        this.dataResultado = dataResultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }
}