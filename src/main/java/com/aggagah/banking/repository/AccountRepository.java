package com.aggagah.banking.repository;

import com.aggagah.banking.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
