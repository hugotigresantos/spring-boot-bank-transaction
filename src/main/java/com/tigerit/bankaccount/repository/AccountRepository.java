package com.tigerit.bankaccount.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tigerit.bankaccount.entity.AccountEntity;

public interface AccountRepository extends JpaRepository<AccountEntity, Long> {

	Optional<AccountEntity> findByDocumentNumber(String documentNumber);

}
