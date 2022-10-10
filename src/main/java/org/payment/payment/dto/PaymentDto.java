package org.payment.payment.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDto {

    @Schema(description = "Валюта")
    private String currency;

    @Schema(description = "Сумма (целое число)")
    private Long amount;

    @Schema(description = "Счет, с которого происходит списание")
    private String fromAccountId;

    @Schema(description = "Счет, на который происходит перевод")
    private String toAccountId;
}
