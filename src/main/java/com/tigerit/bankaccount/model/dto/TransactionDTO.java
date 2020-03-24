package com.tigerit.bankaccount.model.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Builder
@Data
public class TransactionDTO implements Serializable {

	@ApiModelProperty(value = "Identificador da conta")
	@JsonProperty(value = "account_id", required =  true)
	private Long accountId;

	@ApiModelProperty(value = "Identificador da operação: 1 - ",
			notes = "1 - Compra a Vista, 2 - Compra Parcelada, 3- Saque, 4 - Pagamento")
	@JsonProperty(value = "operation_type_id", required = true)
	private Long operationTypeId;

	@ApiModelProperty(value = "Valor")
	@JsonProperty(value = "amount", required = true)
	private BigDecimal amount;

}
