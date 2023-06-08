package models;

public class Paciente extends Usuario {
    private String telefone;
    private String endereco;
    private String historicoPaciente;

    // Construtores
    public Paciente(){
        super();
    }

    public Paciente(String nomePaciente, String historicoPaciente) {
        super();
        this.nome = nomePaciente;
        this.historicoPaciente = historicoPaciente;
    }

    public Paciente(String usuario, String senha, String nome, String telefone, String endereco) {
        super(usuario, senha, nome);
        this.telefone = telefone;
        this.endereco = endereco;
    }

    public Paciente(int id, String usuario, String senha, String nome, String telefone, String endereco) {
        super(usuario, senha, nome);
        this.id = id;
        this.telefone = telefone;
        this.endereco = endereco;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id > 0) {
            this.id = id;
        }
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        if (usuario.length() > 0) {
            this.usuario = usuario;
        }
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        if (senha.length() > 0) {
            this.senha = senha;
        }
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome.length() > 0) {
            this.nome = nome;
        }
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getHistoricoPaciente() {
        return historicoPaciente;
    }

    public void setHistoricoPaciente(String historicoPaciente) {
        this.historicoPaciente = historicoPaciente;
    }
}