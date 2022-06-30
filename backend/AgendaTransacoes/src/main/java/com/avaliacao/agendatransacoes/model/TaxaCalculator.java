package com.avaliacao.agendatransacoes.model;

import java.math.BigDecimal;

import com.avaliacao.agendatransacoes.entities.AgendamentoTransferencia;

public interface TaxaCalculator {
    public BigDecimal calculate(AgendamentoTransferencia agendamento);
}
