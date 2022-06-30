package com.avaliacao.agendatransacoes.model;

import java.math.BigDecimal;

import com.avaliacao.agendatransacoes.entities.AgendamentoTransferencia;

public class TaxaCalculatorImplB implements TaxaCalculator {

    @Override
    public BigDecimal calculate(AgendamentoTransferencia agendamentoDTO) {
        return new BigDecimal(12);
    }

}
