package models;

import java.util.Date;

public class Exame {
    private int id;
    private Paciente paciente;
    private Medico medico;
    private String tipo;
    private Date data;
    private boolean autorizado;

    public Exame() {
        super();
    }

    public Exame(int id, Paciente paciente, Medico medico, String tipo, Date data, boolean autorizado) {
        this.id = id;
        this.paciente = paciente;
        this.medico = medico;
        this.tipo = tipo;
        this.data = data;
        this.autorizado = false;
    }

    // Construtor, getters e setters
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Date getData() {
        return this.data;
    }

    public boolean getAutorizado() {
        return this.autorizado;
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

    public void setData(Date data) {
        this.data = data;
    }

    public void setAutorizado(boolean autorizado) {
        this.autorizado = autorizado;
    }
}
