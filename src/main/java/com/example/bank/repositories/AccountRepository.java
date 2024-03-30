package com.example.bank.repositories;

import com.example.bank.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account,Long> {
    boolean existsAccountByPhone(String phone);
    Optional<Account> findUserByAccountName(String accountName);
    Optional<Account> findAccountByPhone(String phone);
}
