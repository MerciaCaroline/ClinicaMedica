package models;

public class Usuario {
    protected int id;
    protected String usuario;
    protected String senha;
    protected String nome;

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
