package controllers;

public class RecepcionistaController {
    
    // Método para marcar uma consulta
    public boolean marcarConsulta(String nomeMedico, String nomePaciente, String dataConsulta, String horarioConsulta) {
        // Lógica para marcar a consulta: 
        //verifica disponibilidade na agenda
        // insere na tabela de consultas
        //retorna true ou false
        return true;
    }

    // Método para cancelar uma consulta
    public boolean cancelarConsulta(int codigoConsulta) {
        // Lógica para cancelar a consulta
        // update na tabela de consultas marcando o status para cancelado
        //retorna true ou false
        return true;
    }
}
