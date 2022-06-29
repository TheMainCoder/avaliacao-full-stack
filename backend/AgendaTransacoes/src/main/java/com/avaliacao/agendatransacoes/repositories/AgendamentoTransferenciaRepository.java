package com.avaliacao.agendatransacoes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.avaliacao.agendatransacoes.entities.AgendamentoTransferencia;

public interface AgendamentoTransferenciaRepository extends JpaRepository<AgendamentoTransferencia, Long> {

}