package com.avaliacao.agendatransacoes.services;

import java.util.List;
import java.util.Optional;

import com.avaliacao.agendatransacoes.entities.AgendamentoTransferencia;

public interface AgendamentoTransferenciaService {

    void save(AgendamentoTransferencia agendamentoTransferencia);

    List<AgendamentoTransferencia> findAll();

    Optional<AgendamentoTransferencia> findById(Long id);

    void delete(AgendamentoTransferencia agendamentoTransferencia);
    
}
