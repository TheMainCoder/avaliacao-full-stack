package com.avaliacao.agendatransacoes.entities;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "agendamentos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AgendamentoTransferencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer contaOrigem;

    private Integer contaDestino;

    private BigDecimal valor;

    private BigDecimal taxa;

    private Date dataAgendamento;
    
    private Date dataTransferencia;
}
