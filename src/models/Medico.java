package models;

public class Medico extends Usuario{
    private String especialidade;
    private String telefone;
    private String registroCrm;

    // Construtor, getters e setters

    public Medico(String usuario, String senha, String nome, String especialidade, String registroCrm) {
        super(usuario, senha, nome);
        this.especialidade = especialidade;
        this.registroCrm = registroCrm;
    }

    public Medico(int id, String usuario, String senha, String nome, String especialidade, String telefone, String registroCrm) {
        super(usuario, senha, nome);
        this.id = id;
        this.especialidade = especialidade;
        this.telefone = telefone;
        this.registroCrm = registroCrm;
    }

    // Getters
    public String getRegistroCrm() {
        return registroCrm;
    }

    public String getEspecialidade() {
        return this.especialidade;
    }

    public String getTelefone() {
        return this.telefone;
    }

    //Setters
    public void setRegistroCrm(String registroCrm) {
        this.registroCrm = registroCrm;
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