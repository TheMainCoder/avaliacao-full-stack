package com.avaliacao.agendatransacoes.factory;

import java.text.ParseException;

import com.avaliacao.agendatransacoes.entities.AgendamentoTransferencia;
import com.avaliacao.agendatransacoes.model.TaxaCalculator;
import com.avaliacao.agendatransacoes.model.TaxaCalculatorImplA;
import com.avaliacao.agendatransacoes.model.TaxaCalculatorImplB;
import com.avaliacao.agendatransacoes.model.TaxaCalculatorImplC;
import com.avaliacao.agendatransacoes.utils.DateUtils;

public class TaxaCalculatorFactory {
    public static TaxaCalculator getTaxaCalculator(AgendamentoTransferencia agendamento) throws ParseException {
        long daysBetween = DateUtils.daysBetween(agendamento.getDataAgendamento(), agendamento.getDataTransferencia());

        if (daysBetween <= 0) {
            // System.out.println("TaxaCalculatorImplA");
            return new TaxaCalculatorImplA();
        }

        if (daysBetween <= 10) {
            // System.out.println("TaxaCalculatorImplB");
            return new TaxaCalculatorImplB();
        }
        
        if (daysBetween > 10) {
            // System.out.println("TaxaCalculatorImplC");
            return new TaxaCalculatorImplC();
        }

        return null;
    }
}
