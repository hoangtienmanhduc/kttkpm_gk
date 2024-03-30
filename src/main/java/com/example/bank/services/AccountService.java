package com.example.bank.services;

import com.example.bank.models.Account;

import java.util.Optional;

public interface AccountService {
    Optional<Account> findUserByAccountName(String accountName);
    Optional<Account> findAccountByPhone(String phone);
}
