package models;

import java.util.Date;

public class ResultadoExame {
    private int id;
    private int pacienteId;
    private int medicoId;
    private int exameId;
    private String tipo;
    private Date dataResultado;
    private String resultado;

    // Construtores
    public ResultadoExame() {
        super();
    }

    public ResultadoExame(int pacienteId, int medicoId, int exameId, String tipo, Date dataResultado, String resultado) {
        this.pacienteId = pacienteId;
        this.medicoId = medicoId;
        this.exameId = exameId;
        this.tipo = tipo;
        this.dataResultado = dataResultado;
        this.resultado = resultado;
    }
    
    // Getters e setters
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPaciente() {
        return this.pacienteId;
    }

    public void setPaciente(int pacienteId) {
        this.pacienteId = pacienteId;
    }

    public int getMedico() {
        return this.medicoId;
    }

    public void setMedico(int medicoId) {
        this.medicoId = medicoId;
    }

    public int getExame() {
        return this.exameId;
    }

    public void setExame(int exameId) {
        this.exameId = exameId;
    }

    public String getTipo() {
        return this.tipo;
    }

    public void setTipo(String tipo) {
        if (tipo.length() > 0) {
            this.tipo = tipo;
        }
    }

    public Date getDataResultado() {
        return this.dataResultado;
    }

    public void setDataResultado(Date dataResultado) {
        this.dataResultado = dataResultado;
    }

    public String getResultado() {
        return this.resultado;
    }

    public void setResultado(String resultado) {
        if (resultado.length() > 0) {
            this.resultado = resultado;
        }
    }
}