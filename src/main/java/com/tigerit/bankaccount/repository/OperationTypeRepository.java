package com.tigerit.bankaccount.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tigerit.bankaccount.entity.OperationTypeEntity;

public interface OperationTypeRepository extends JpaRepository<OperationTypeEntity, Long> {

}
