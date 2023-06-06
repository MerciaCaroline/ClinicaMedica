package models;

public class Usuario {
    protected int id;
    protected String usuario;
    protected String senha;
    protected String nome;

    public Usuario(String usuario, String senha, String nome){
        this.usuario = usuario;
        this.senha = senha;
        this.nome = nome;
    }

    // Construtor, getters e setters
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

}
