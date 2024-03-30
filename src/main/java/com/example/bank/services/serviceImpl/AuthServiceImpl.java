package com.example.bank.services.serviceImpl;

import com.example.bank.helper.AutherHelper;
import com.example.bank.models.Account;
import com.example.bank.repositories.AccountRepository;
import com.example.bank.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AccountRepository accountRepository;
    @Override
    public boolean accountLogin(String phone, String password) {
        Optional<Account> account = accountRepository.findAccountByPhone(phone);
        if (account.isPresent() && AutherHelper.verifyPassword(password, account.get().getPasswordHash())) {
            return true;
        }
        return false;
    }

    @Override
    public boolean accountRegister(Account account) throws Exception {
        if (accountRepository.existsAccountByPhone(account.getPhone())) {
            throw new Exception("Phone already exists");
        }
        String passWordHash = AutherHelper.hashPassword(account.getPasswordHash());
        account.setPasswordHash(passWordHash);
        account.setRegistered_at(Instant.now());
        accountRepository.save(account);
        return true;
    }
}
