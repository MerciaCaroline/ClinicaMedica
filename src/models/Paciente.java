package models;

public class Paciente extends Usuario {
    private String telefone;
    private String cpf;
    private char sexo;
    private String email;
    private int idade;
    private String endereco;
    private String historicoPaciente;

    // Construtores
    public Paciente(){
        super();
    }

    public Paciente(int id, String usuario, String senha, String nome, 
            String telefone, String cpf, char sexo, 
            String email, int idade, String endereco) {
        super(usuario, senha, nome);
        this.id = id;
        this.telefone = telefone;
        this.cpf = cpf;
        this.sexo = sexo;
        this.email = email;
        this.idade = idade;
        this.endereco = endereco;
    }

    public Paciente(String usuario, String senha, String nome, 
            String telefone, String cpf, char sexo, 
            String email, int idade, String endereco) {
        super(usuario, senha, nome);
        this.telefone = telefone;
        this.cpf = cpf;
        this.sexo = sexo;
        this.email = email;
        this.idade = idade;
        this.endereco = endereco;
    }

    // Getters and Setters
    
    //remover
    public String getHistoricoPaciente() {
        return historicoPaciente;
    }

    public void setHistoricoPaciente(String historicoPaciente) {
        this.historicoPaciente = historicoPaciente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id > 0) {
            this.id = id;
        }
    }
    
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
    
    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}