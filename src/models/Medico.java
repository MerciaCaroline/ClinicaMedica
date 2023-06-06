package models;

public class Medico extends Usuario{
    private String especialidade;
    private String telefone;

    // Construtor, getters e setters
    public String getEspecialidade() {
        return this.especialidade;
    }

    public String getTelefone() {
        return this.telefone;
    }

    public void setEspecialidade(String especialidade) {
        if (especialidade.length() > 0) {
            this.especialidade = especialidade;
        }
    }

    public void setTelefone(String telefone) {
        if (telefone.length() > 0) {
            this.telefone = telefone;
        }
    }
}