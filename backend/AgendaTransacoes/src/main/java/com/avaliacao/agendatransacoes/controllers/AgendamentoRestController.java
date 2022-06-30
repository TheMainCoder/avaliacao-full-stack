package com.avaliacao.agendatransacoes.controllers;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

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

    @GetMapping("/list")
    public List<AgendamentoTransferencia> getAgendamentos() {
        return agendamentoService.findAll();
    }

    @GetMapping("{id}")
    public AgendamentoTransferencia getAgendamento(@PathVariable("id") Long id, BindingResult bindingResult) throws MethodArgumentNotValidException {
        Optional<AgendamentoTransferencia> agendamento = agendamentoService.findById(id);
        if (agendamento.isPresent()) {
            return agendamento.get();
        }

        bindingResult.addError(new ObjectError("operation", "Agendamento não encontrado"));
        throw new MethodArgumentNotValidException(null, bindingResult);
    }

    @PutMapping("{id}")
    public String updateAgendamento(@PathVariable("id") Long id, @Valid @RequestBody AgendamentoTransferenciaDTO agendamentoDTO, BindingResult bindingResult) throws MethodArgumentNotValidException {
        if (bindingResult.hasErrors()) {
            throw new MethodArgumentNotValidException(null, bindingResult);
        }
        
        Optional<AgendamentoTransferencia> opAgendamento = agendamentoService.findById(id);
        if(opAgendamento.isPresent()) {
            try {
                AgendamentoTransferencia agendamento = opAgendamento.get();
                agendamento.setContaOrigem(agendamentoDTO.getContaOrigem());
                agendamento.setContaDestino(agendamentoDTO.getContaDestino());
                agendamento.setValor(agendamentoDTO.getValor());
                agendamento.setDataTransferencia(new SimpleDateFormat("yyyy-MM-dd").parse(agendamentoDTO.getDataTransferencia()));
            
                BigDecimal taxa = TaxaCalculatorFactory.getTaxaCalculator(agendamento)
                        .calculate(agendamento);
                if (Objects.nonNull(taxa)) {
                    agendamento.setTaxa(taxa);
                } else
                    throw new Exception();

                agendamentoService.save(agendamento);
            } catch(Exception e) {
                bindingResult.addError(new ObjectError("operation", "Erro ao atualizar o agendamento"));
                throw new MethodArgumentNotValidException(null, bindingResult);
            }
        } else {
            bindingResult.addError(new ObjectError("operation", "Agendamento não encontrado"));
            throw new MethodArgumentNotValidException(null, bindingResult);
        }

        return "Atualizado com sucesso";
    }

    @DeleteMapping("{id}")
    public String deleteAgendamento(@PathVariable("id") Long id, HttpServletResponse response) {
        Optional<AgendamentoTransferencia> agendamento = agendamentoService.findById(id);
        if (agendamento.isPresent()) {
            agendamentoService.delete(agendamento.get());
            return "Excluído com sucesso";
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Agendamento não encontrado", new Exception());
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
