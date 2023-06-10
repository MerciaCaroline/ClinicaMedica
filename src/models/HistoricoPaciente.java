package models;

import java.util.Date;

public class HistoricoPaciente {
    private int id;
    private Paciente paciente;
	private Date data;
	private String descricao;
    
    public HistoricoPaciente() {
    }

    public HistoricoPaciente(Paciente paciente, Date data, String descricao) {
        this.paciente = paciente;
        this.data = data;
        this.descricao = descricao;
    }

    public HistoricoPaciente(int id, Paciente paciente, Date data, String descricao) {
        this.id = id;
        this.paciente = paciente;
        this.data = data;
        this.descricao = descricao;
    }

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


