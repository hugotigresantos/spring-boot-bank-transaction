package com.tigerit.bankaccount.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.tigerit.bankaccount.model.enums.OperationType;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "operation_type")
public class OperationTypeEntity implements Serializable {

	@Id
	@Column(name = "id", nullable = false, updatable = false)
	private Long id;

	@Column(name = "description", nullable = false, updatable = false)
	private String description;

	@Transient
	public boolean isPayment() {
		return OperationType.PAYMENT.name().equals( description );
	}

}
