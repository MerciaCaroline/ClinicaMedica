package models;

public class Medico extends Usuario{
    private String especialidade;
    private String telefone;
    private String registroCrm;

    // Construtores
    public Medico() {
        super();
    }
    
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

    // Getters e Setters
    public String getUsuario() {
        return this.usuario;
    }

    public void setUsuario(String usuario) {
        if (usuario.length() > 0) {
            this.usuario = usuario;
        }
    }

    public String getSenha() {
        return this.senha;
    }

    public void setSenha(String senha) {
        if (senha.length() > 0) {
            this.senha = senha;
        }
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        if (nome.length() > 0) {
            this.nome = nome;
        }
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        if (id > 0) {
            this.id = id;
        }
    }

    public String getEspecialidade() {
        return this.especialidade;
    }

    public void setEspecialidade(String especialidade) {
        if (especialidade.length() > 0) {
            this.especialidade = especialidade;
        }
    }

    public String getTelefone() {
        return this.telefone;
    }

    public void setTelefone(String telefone) {
        if (telefone.length() > 0) {
            this.telefone = telefone;
        }
    }

    public String getRegistroCrm() {
        return registroCrm;
    }

    public void setRegistroCrm(String registroCrm) {
        this.registroCrm = registroCrm;
    }
}