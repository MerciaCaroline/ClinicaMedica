package controllers;

import java.util.Date;

import models.Consulta;

public class ConsultaController {
    private static Consulta _consulta;

    public ConsultaController(Consulta consulta) {
        _consulta = consulta;
    }

    public static void atualizarDescricao(String novaDescricao) {
        _consulta.setDescricao(novaDescricao);
        // Lógica para atualizar a descrição da consulta
    }

    public void realizarConsulta(String diagnostico) {
        // Lógica para registrar o diagnóstico da consulta
    }

    public void remarcarConsulta(Date novaData) {
        // Lógica para remarcar a consulta para uma nova data
    }

    public void realizarAtendimento() {
        // Lógica para realizar o atendimento do paciente durante a consulta
    }

    public void finalizarConsulta() {
        // Lógica para finalizar a consulta e atualizar o status
    }
}
