package controllers;

import java.util.ArrayList;
import java.util.List;

import models.Consulta;
import models.Exame;
import models.Medico;
import models.Paciente;

public class MedicoController {
    private List<Paciente> historicoPacientes;
    private List<Consulta> consultas;
    private List<Exame> examesPendentes;

     public MedicoController() {
        this.historicoPacientes = new ArrayList<>();
        this.consultas = new ArrayList<>();
        this.examesPendentes = new ArrayList<>();
    }

    public boolean autorizarExame(int codigo) {
        // Lógica para autorizar o exame para o paciente
        examesPendentes.stream().filter(exame -> exame.getId() == codigo).findFirst().get().setAutorizado(true);
        return examesPendentes.stream().filter(exame -> exame.getId() == codigo).findFirst() != null;
    }

    public List<Consulta> consultarConsultas(Medico medico) {
        // Lógica para consultar as consultas agendadas para o médico
        List<Consulta> consultasMedico = new ArrayList<>();
        
        for (Consulta consulta : consultas) {
            if (consulta.getMedico().equals(medico)) {
                consultasMedico.add(consulta);
            }
        }
        
        return consultasMedico;
    }

    public void registrarHistoricoPaciente(String nomePaciente, String historicoPaciente) {
        Paciente paciente = new Paciente(nomePaciente, historicoPaciente);
        historicoPacientes.add(paciente);
    }

    public List<Paciente> getHistoricoPacientes() {
        // Lógica para consultar as consultas agendadas para o médico
        return historicoPacientes;
    }

    public List<Exame> consultarResultadosExames(Medico medico) {
        // Lógica para consultar os resultados de exames dos pacientes atendidos pelo médico
        List<Exame> examesPendentesMedico = new ArrayList<>();
        
        for (Exame exame : examesPendentes) {
            if (exame.getMedico().equals(medico) && !exame.getAutorizado() == true) {
                examesPendentesMedico.add(exame);
            }
        }
        
        return examesPendentesMedico;
    }

    // Método para registrar uma nova consulta
    public void registrarConsulta(Consulta consulta) {
        consultas.add(consulta);
    }
}
