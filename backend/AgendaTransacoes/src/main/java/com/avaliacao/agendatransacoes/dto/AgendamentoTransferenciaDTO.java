package com.avaliacao.agendatransacoes.dto;

import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AgendamentoTransferenciaDTO {
    private String contaOrigem;

    private String contaDestino;

    private BigDecimal valor;

    private BigDecimal taxa;
    
    private Date dataAgendamento;
    
    private Date dataTransferencia;
}
