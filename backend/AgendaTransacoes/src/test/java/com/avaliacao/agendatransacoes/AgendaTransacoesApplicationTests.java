package com.avaliacao.agendatransacoes;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.util.Calendar;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.avaliacao.agendatransacoes.entities.AgendamentoTransferencia;
import com.avaliacao.agendatransacoes.factory.TaxaCalculatorFactory;
import com.avaliacao.agendatransacoes.model.TaxaCalculator;

@SpringBootTest
class AgendaTransacoesApplicationTests {

	@Test
	void testTaxaImplA() {
		AgendamentoTransferencia agendamento = AgendamentoTransferencia.builder()
				.contaOrigem(123456)
				.contaDestino(321321)
				.valor(new BigDecimal(1000))
				.dataAgendamento(Calendar.getInstance().getTime())
				.dataTransferencia(Calendar.getInstance().getTime())
				.build();
		try {
			TaxaCalculator calculator = TaxaCalculatorFactory.getTaxaCalculator(agendamento);
			BigDecimal taxa = calculator.calculate(agendamento).setScale(2, RoundingMode.HALF_UP);
			BigDecimal expectedTaxa = new BigDecimal(33);

			assertTrue(expectedTaxa.compareTo(taxa) == 0);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	@Test
	void testTaxaImplB() {
		Calendar tomorrow = Calendar.getInstance();
		tomorrow.add(Calendar.DAY_OF_MONTH, 1);
		AgendamentoTransferencia agendamento = AgendamentoTransferencia.builder()
				.contaOrigem(123456)
				.contaDestino(321321)
				.valor(new BigDecimal(1000))
				.dataAgendamento(Calendar.getInstance().getTime())
				.dataTransferencia(tomorrow.getTime())
				.build();
		try {
			TaxaCalculator calculator = TaxaCalculatorFactory.getTaxaCalculator(agendamento);
			BigDecimal taxa = calculator.calculate(agendamento).setScale(2, RoundingMode.HALF_UP);
			BigDecimal expectedTaxa = new BigDecimal(12);

			assertTrue(expectedTaxa.compareTo(taxa) == 0);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testTaxaImplC11Days() {
		Calendar dataTransferencia = Calendar.getInstance();
		dataTransferencia.add(Calendar.DAY_OF_MONTH, 11);

		AgendamentoTransferencia agendamento = AgendamentoTransferencia.builder()
				.contaOrigem(123456)
				.contaDestino(321321)
				.valor(new BigDecimal(1000))
				.dataAgendamento(Calendar.getInstance().getTime())
				.dataTransferencia(dataTransferencia.getTime())
				.build();
		try {
			TaxaCalculator calculator = TaxaCalculatorFactory.getTaxaCalculator(agendamento);
			BigDecimal taxa = calculator.calculate(agendamento).setScale(2, RoundingMode.HALF_UP);
			BigDecimal expectedTaxa = new BigDecimal(82);

			assertTrue(expectedTaxa.compareTo(taxa) == 0);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testTaxaImplC21Days() {
		Calendar dataTransferencia = Calendar.getInstance();
		dataTransferencia.add(Calendar.DAY_OF_MONTH, 21);

		AgendamentoTransferencia agendamento = AgendamentoTransferencia.builder()
				.contaOrigem(123456)
				.contaDestino(321321)
				.valor(new BigDecimal(1000))
				.dataAgendamento(Calendar.getInstance().getTime())
				.dataTransferencia(dataTransferencia.getTime())
				.build();
		try {
			TaxaCalculator calculator = TaxaCalculatorFactory.getTaxaCalculator(agendamento);
			BigDecimal taxa = calculator.calculate(agendamento).setScale(2, RoundingMode.HALF_UP);
			BigDecimal expectedTaxa = new BigDecimal(69);

			assertTrue(expectedTaxa.compareTo(taxa) == 0);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testTaxaImplC31Days() {
		Calendar dataTransferencia = Calendar.getInstance();
		dataTransferencia.add(Calendar.DAY_OF_MONTH, 31);

		AgendamentoTransferencia agendamento = AgendamentoTransferencia.builder()
				.contaOrigem(123456)
				.contaDestino(321321)
				.valor(new BigDecimal(1000))
				.dataAgendamento(Calendar.getInstance().getTime())
				.dataTransferencia(dataTransferencia.getTime())
				.build();
		try {
			TaxaCalculator calculator = TaxaCalculatorFactory.getTaxaCalculator(agendamento);
			BigDecimal taxa = calculator.calculate(agendamento).setScale(2, RoundingMode.HALF_UP);
			BigDecimal expectedTaxa = new BigDecimal(47);

			assertTrue(expectedTaxa.compareTo(taxa) == 0);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testTaxaImplC41Days() {
		Calendar dataTransferencia = Calendar.getInstance();
		dataTransferencia.add(Calendar.DAY_OF_MONTH, 41);

		AgendamentoTransferencia agendamento = AgendamentoTransferencia.builder()
				.contaOrigem(123456)
				.contaDestino(321321)
				.valor(new BigDecimal(1000))
				.dataAgendamento(Calendar.getInstance().getTime())
				.dataTransferencia(dataTransferencia.getTime())
				.build();
		try {
			TaxaCalculator calculator = TaxaCalculatorFactory.getTaxaCalculator(agendamento);
			BigDecimal taxa = calculator.calculate(agendamento).setScale(2, RoundingMode.HALF_UP);
			BigDecimal expectedTaxa = new BigDecimal(17);

			assertTrue(expectedTaxa.compareTo(taxa) == 0);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

}
