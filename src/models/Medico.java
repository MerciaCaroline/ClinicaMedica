package models;

import java.util.Date;
import java.util.List;

public class Medico {
    private int id;
    private String nome;
    private String especialidade;
    private String telefone;

    // Construtor, getters e setters
    public int getId() {
        return this.id;
    }

    public String getNome() {
        return this.nome;
    }

    public String getEspecialidade() {
        return this.especialidade;
    }

    public String getTelefone() {
        return this.telefone;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        if (nome.length() > 0) {
            this.nome = nome;
        }
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