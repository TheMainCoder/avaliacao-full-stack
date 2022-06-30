package com.avaliacao.agendatransacoes.dto;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.avaliacao.agendatransacoes.entities.AgendamentoTransferencia;
import com.avaliacao.agendatransacoes.validation.CheckDateFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AgendamentoTransferenciaDTO {
    @Min(value = 1, message = "O campo 'Conta de Origem' é obrigatório")
    private Integer contaOrigem;

    @Min(value = 1, message = "O campo 'Conta de Destino' é obrigatório")
    private Integer contaDestino;

    @NotNull(message = "O campo 'Valor' é obrigatório")
    @Min(value = 0, message = "O campo 'Valor' deve ser maior ou igual a 0")
    private BigDecimal valor;

    @NotBlank(message = "O campo 'Data da Transferência' é obrigatório")
    @CheckDateFormat(pattern = "yyyyMMdd", message = "O campo 'Data de Transferência' deve ser uma data válida no formato yyyy-MM-dd")
    private String dataTransferencia;

    @AssertTrue(message = "A conta de origem deve ser diferente da conta de destino")
    private boolean isValid() {
        return !Objects.equals(contaOrigem, contaDestino);
    }

    public AgendamentoTransferencia toAgendamentoTransferencia() throws ParseException {
        Date dataTransferencia = new SimpleDateFormat("yyyy-MM-dd").parse(this.dataTransferencia);

        return AgendamentoTransferencia.builder()
                .contaOrigem(contaOrigem)
                .contaDestino(contaDestino)
                .valor(valor)
                .dataTransferencia(dataTransferencia)
                .build();
    }
}
