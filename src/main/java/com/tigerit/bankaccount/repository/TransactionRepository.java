package com.tigerit.bankaccount.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tigerit.bankaccount.entity.AccountEntity;
import com.tigerit.bankaccount.entity.TransactionEntity;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {
	Optional<TransactionEntity> findByAccount(AccountEntity accountEntity);
}
