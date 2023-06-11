package models;

public class Laboratorio extends Usuario {
    private String telefone;
    private String endereco;

    public Laboratorio(String usuario, String senha, String nome, String telefone, String endereco) {
        super(usuario, senha, nome);
        this.telefone = telefone;
        this.endereco = endereco;
    }

    public Laboratorio(int id, String usuario, String senha, String nome, String telefone, String endereco) {
        super(usuario, senha, nome);
        this.telefone = telefone;
        this.endereco = endereco;
        this.id = id;
    }

    // Construtores 
    
    
    // Getters e setters
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        if (nome.length() > 0) {
            this.nome = nome;
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

    public String getEndereco() {
        return this.endereco;
    }

    public void setEndereco(String endereco) {
        if (endereco.length() > 0) {
            this.endereco = endereco;
        }
    }
}