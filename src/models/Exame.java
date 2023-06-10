package models;

import java.util.Date;

public class Exame {
    private int id;
    private Paciente paciente;
    private Medico medico;
    private Laboratorio laboratorio;
    private String tipo;
    private Date dataSolicitacao;
    private Date dataResultado;
    private String resultado;
    private boolean entreguePaciente;
    private boolean disponivelOnline;

    // Construtor para solicitar exame
    public Exame(Paciente paciente, Medico medico, Laboratorio laboratorio, String tipo, Date dataSolicitacao) {
        this.paciente = paciente;
        this.medico = medico;
        this.laboratorio = laboratorio;
        this.tipo = tipo;
        this.dataSolicitacao = dataSolicitacao;
    }
    
    // Construtor completo
    public Exame(int id, Paciente paciente, Medico medico, Laboratorio laboratorio, String tipo, Date dataSolicitacao,
            Date dataResultado, String resultado, boolean entreguePaciente, boolean disponivelOnline) {
        this.id = id;
        this.paciente = paciente;
        this.medico = medico;
        this.laboratorio = laboratorio;
        this.tipo = tipo;
        this.dataSolicitacao = dataSolicitacao;
        this.dataResultado = dataResultado;
        this.resultado = resultado;
        this.entreguePaciente = entreguePaciente;
        this.disponivelOnline = disponivelOnline;
    }

    // Construtor vazio
    public Exame() {
        super();
    }
    
    public Laboratorio getLaboratorio() {
        return laboratorio;
    }

    public void setLaboratorio(Laboratorio laboratorio) {
        this.laboratorio = laboratorio;
    }
    
    public Date getDataSolicitacao() {
        return dataSolicitacao;
    }

    public void setDataSolicitacao(Date dataSolicitacao) {
        this.dataSolicitacao = dataSolicitacao;
    }
    
    public Date getDataResultado() {
        return dataResultado;
    }

    public void setDataResultado(Date dataResultado) {
        this.dataResultado = dataResultado;
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
        this.tipo = tipo;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public boolean isEntreguePaciente() {
        return entreguePaciente;
    }

    public void setEntreguePaciente(boolean entreguePaciente) {
        this.entreguePaciente = entreguePaciente;
    }

    public boolean isDisponivelOnline() {
        return disponivelOnline;
    }

    public void setDisponivelOnline(boolean disponivelOnline) {
        this.disponivelOnline = disponivelOnline;
    }
}
