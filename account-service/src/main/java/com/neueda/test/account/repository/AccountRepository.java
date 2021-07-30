package com.neueda.test.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.neueda.test.account.entity.AccountDetails;

@Repository
public interface AccountRepository extends JpaRepository<AccountDetails, Long> {

}
