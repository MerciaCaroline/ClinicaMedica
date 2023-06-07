package models;

public class Paciente extends Usuario{
    private String telefone;
    private String endereco;
    private String historicoPaciente;

    public Paciente(int id, String nome, String telefone, String endereco) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
    }

    public Paciente(String nome, String historicoPaciente) {
        this.nome = nome;
        this.historicoPaciente = historicoPaciente;
    }

    // Construtor, getters e setters
    public Paciente() {
        super();
    }

    public String getTelefone() {
        return this.telefone;
    }

    public String getEndereco() {
        return this.endereco;
    }

    public String getHistoricoPaciente() {
        return this.historicoPaciente;
    }

    public void setTelefone(String telefone) {
        if (telefone.length() > 0) {
            this.telefone = telefone;
        }
    }

    public void setEndereco(String endereco) {
        if (endereco.length() > 0) {
            this.endereco = endereco;
        }
    }

    public void setHistoricoPaciente(String historicoPaciente) {
        if (historicoPaciente.length() > 0) {
            this.historicoPaciente = historicoPaciente;
        }
    }
}
