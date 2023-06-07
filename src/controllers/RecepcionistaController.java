package controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import models.Consulta;
import models.Medico;
import models.Paciente;

public class RecepcionistaController {
    
    private List<Paciente> pacientes;
    private List<Consulta> consultas;
    
    // Construtor
    public RecepcionistaController() {
        this.pacientes = new ArrayList<>();
    }
    
    // Método para marcar uma consulta
    public boolean marcarConsulta(Paciente paciente, Medico medico, Date dataConsulta, String descricao) {
        // Lógica para marcar a consulta: 
        //verifica disponibilidade na agenda
        // insere na tabela de consultas
        //retorna true ou false
        Consulta consulta = new Consulta(paciente, medico, dataConsulta, descricao);
        pacientes.add(paciente);
        consultas.add(consulta);

        return true;
    }

    // Método para cancelar uma consulta
    public boolean cancelarConsulta(int idConsulta) {
        // Lógica para cancelar a consulta
        // update na tabela de consultas marcando o status para cancelado
        //retorna true ou false
        Consulta obterConsulta = consultas.stream().filter(consulta -> consulta.getId() == idConsulta).findFirst().get();
        
        if(obterConsulta != null){
            consultas.remove(obterConsulta);
            return true;
        }

        return false;
    }
}
