package models;

public class Recepcionista extends Usuario{
    private int id;

    // Construtores
    public Recepcionista() {
        super();
    }

    public Recepcionista(String usuario, String senha, String nome) {
        super(usuario, senha, nome);
    }

    public Recepcionista(int id, String usuario, String senha, String nome) {
        super(usuario, senha, nome);
        this.id = id;
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
}
