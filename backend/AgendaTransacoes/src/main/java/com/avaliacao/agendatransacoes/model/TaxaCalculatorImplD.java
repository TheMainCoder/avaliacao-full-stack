package com.avaliacao.agendatransacoes.model;

import java.math.BigDecimal;

import com.avaliacao.agendatransacoes.entities.AgendamentoTransferencia;

public class TaxaCalculatorImplD implements TaxaCalculator {

    @Override
    public BigDecimal calculate(AgendamentoTransferencia agendamento) {
        if (agendamento.getValor().compareTo(new BigDecimal(1000)) <= 0) {
            return new TaxaCalculatorImplA().calculate(agendamento);
        }

        if (agendamento.getValor().compareTo(new BigDecimal(1000)) > 0
                && agendamento.getValor().compareTo(new BigDecimal(2000)) <= 0) {
            return new TaxaCalculatorImplB().calculate(agendamento);
        }

        if (agendamento.getValor().compareTo(new BigDecimal(2000)) > 0) {
            return new TaxaCalculatorImplC().calculate(agendamento);
        }

        return null;
    }
}
