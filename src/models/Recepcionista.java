package models;

public class Recepcionista extends Usuario{

    public Recepcionista(String usuario, String senha, String nome) {
        super(usuario, senha, nome);
    }

    public Recepcionista(int id, String usuario, String senha, String nome) {
        super(usuario, senha, nome);
        this.id = id;
    }
    
    // public String getUsuario(){
    //     return this.usuario;
    // }

    // public void setUsuario(String usuario){
    //     this.usuario = usuario;
    // }

    // public String getSenha(){
    //     return this.senha;
    // }
}
