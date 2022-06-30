package com.avaliacao.agendatransacoes.controllers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.avaliacao.agendatransacoes.dto.AgendamentoTransferenciaDTO;
import com.avaliacao.agendatransacoes.entities.AgendamentoTransferencia;
import com.avaliacao.agendatransacoes.factory.TaxaCalculatorFactory;
import com.avaliacao.agendatransacoes.services.AgendamentoTransferenciaService;

@RestController
@RequestMapping("/agendamento")
public class AgendamentoRestController {
    @Autowired
    private AgendamentoTransferenciaService agendamentoService;

    @PostMapping("/add")
    public String addAgendamento(
            @Valid @RequestBody AgendamentoTransferenciaDTO agendamentoDTO, BindingResult bindingResult)
            throws MethodArgumentNotValidException {
        if (bindingResult.hasErrors()) {
            throw new MethodArgumentNotValidException(null, bindingResult);
        }

        try {
            // Calcula a taxa
            AgendamentoTransferencia agendamentoTransferencia = agendamentoDTO.toAgendamentoTransferencia();
            agendamentoTransferencia.setDataAgendamento(Calendar.getInstance().getTime());

            BigDecimal taxa = TaxaCalculatorFactory.getTaxaCalculator(agendamentoTransferencia)
                    .calculate(agendamentoTransferencia);
            if (Objects.nonNull(taxa)) {
                agendamentoTransferencia.setTaxa(taxa);
            } else
                throw new Exception();
            // Salva o agendamento
            agendamentoService.save(agendamentoTransferencia);
        } catch (Exception e) {
            bindingResult.addError(new ObjectError("operation", "Erro ao salvar o agendamento"));
            throw new MethodArgumentNotValidException(null, bindingResult);
        }

        return "Adicionado com sucesso";
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        List<String> errors = new ArrayList<String>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String errorMessage = error.getDefaultMessage();
            errors.add(errorMessage);
        });
        return errors;
    }
}
