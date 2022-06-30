package com.avaliacao.agendatransacoes.services;

import java.util.List;
import java.util.Optional;

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

    @Override
    public List<AgendamentoTransferencia> findAll() {
        return agendamentoRepository.findAll();
    }

    @Override
    public Optional<AgendamentoTransferencia> findById(Long id) {
        return agendamentoRepository.findById(id);
    }

    @Override
    public void delete(AgendamentoTransferencia agendamentoTransferencia) {
        agendamentoRepository.delete(agendamentoTransferencia);
    }
}
