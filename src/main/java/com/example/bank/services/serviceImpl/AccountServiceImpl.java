package com.example.bank.services.serviceImpl;

import com.example.bank.models.Account;
import com.example.bank.repositories.AccountRepository;
import com.example.bank.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Optional<Account> findUserByAccountName(String accountName) {
        return accountRepository.findUserByAccountName(accountName);
    }

    @Override
    public Optional<Account> findAccountByPhone(String phone) {
        return accountRepository.findAccountByPhone(phone);
    }
}
