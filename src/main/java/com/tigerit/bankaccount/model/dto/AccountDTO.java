package com.tigerit.bankaccount.model.dto;

import java.io.Serializable;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Store data of an account")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Builder
@Data
public class AccountDTO implements Serializable {

	@ApiModelProperty(value = "Identificador da conta")
	@JsonProperty("account_id")
	private Long accountId;

	@ApiModelProperty(value = "Número do documento")
	@JsonProperty("document_number")
	private String documentNumber;

}
