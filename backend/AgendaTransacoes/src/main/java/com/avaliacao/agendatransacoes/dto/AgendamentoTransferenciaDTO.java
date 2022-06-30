package com.avaliacao.agendatransacoes.dto;

import java.math.BigDecimal;
import java.util.Objects;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AgendamentoTransferenciaDTO {
    @NotBlank(message = "O campo 'Conta de Origem' é obrigatório")
    private String contaOrigem;

    @NotBlank(message = "O campo 'Conta de Destino' é obrigatório")
    private String contaDestino;

    @NotNull(message = "O campo 'Valor' é obrigatório")
    @Min(value = 0, message = "O campo 'Valor' deve ser maior ou igual a 0")
    private BigDecimal valor;

    private BigDecimal taxa;

    @NotBlank(message = "O campo 'Data de Agendamento' é obrigatório")
    private String dataAgendamento;

    @NotBlank(message = "O campo 'Data da Transferência' é obrigatório")
    private String dataTransferencia;

    @AssertTrue(message = "A conta de origem deve ser diferente da conta de destino")
    private boolean isValid() {
        return !Objects.equals(contaOrigem, contaDestino);
    }
}
