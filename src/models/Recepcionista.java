package models;

public class Recepcionista extends Usuario{

    public Recepcionista(String usuario, String senha, String nome) {
        super(usuario, senha, nome);
    }

    public Recepcionista(int id, String usuario, String senha, String nome) {
        super(usuario, senha, nome);
        this.id = id;
    }
    
}
