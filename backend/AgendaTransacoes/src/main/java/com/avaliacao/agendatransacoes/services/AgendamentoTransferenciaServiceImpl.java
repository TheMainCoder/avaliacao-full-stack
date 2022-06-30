package com.avaliacao.agendatransacoes.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avaliacao.agendatransacoes.entities.AgendamentoTransferencia;
import com.avaliacao.agendatransacoes.repositories.AgendamentoTransferenciaRepository;

@Service
public class AgendamentoTransferenciaServiceImpl implements AgendamentoTransferenciaService {
    @Autowired
    private AgendamentoTransferenciaRepository agendamentoRepository;

    @Override
    public void save(AgendamentoTransferencia agendamentoTransferencia) {
        agendamentoRepository.save(agendamentoTransferencia);
    }
}
