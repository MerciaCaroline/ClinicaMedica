package models;

import java.util.Date;

public class ResultadoExame {
    private int id;
    private Paciente paciente;
    private Medico medico;
    private String tipo;
    private Date dataResultado;
    private String resultado;

    // Construtores
    public ResultadoExame() {
        super();
    }

    public ResultadoExame(Paciente paciente, Medico medico, String tipo, Date dataResultado, String resultado) {
        this.paciente = paciente;
        this.medico = medico;
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

    public Paciente getPaciente() {
        return this.paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Medico getMedico() {
        return this.medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
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