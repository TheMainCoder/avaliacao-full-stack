package com.avaliacao.agendatransacoes.model;

import java.math.BigDecimal;

import com.avaliacao.agendatransacoes.entities.AgendamentoTransferencia;

public class TaxaCalculatorImplA implements TaxaCalculator {
    @Override
    public BigDecimal calculate(AgendamentoTransferencia agendamento) {
        return new BigDecimal(3).add(agendamento.getValor().multiply(new BigDecimal(0.03)));
    }
}
