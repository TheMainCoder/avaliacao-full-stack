package com.avaliacao.agendatransacoes.model;

import java.math.BigDecimal;

import com.avaliacao.agendatransacoes.entities.AgendamentoTransferencia;
import com.avaliacao.agendatransacoes.utils.DateUtils;

public class TaxaCalculatorImplC implements TaxaCalculator {

    @Override
    public BigDecimal calculate(AgendamentoTransferencia agendamento) {
        long daysBetween = DateUtils.daysBetween(agendamento.getDataAgendamento(), agendamento.getDataTransferencia());

        if (daysBetween > 10 && daysBetween <= 20) {
            return agendamento.getValor().multiply(new BigDecimal(0.082));
        }
        if (daysBetween > 20 && daysBetween <= 30) {
            return agendamento.getValor().multiply(new BigDecimal(0.069));
        }
        if (daysBetween > 30 && daysBetween <= 40) {
            return agendamento.getValor().multiply(new BigDecimal(0.047));
        }
        if (daysBetween > 40) {
            return agendamento.getValor().multiply(new BigDecimal(0.017));
        }

        return null;
    }

}
