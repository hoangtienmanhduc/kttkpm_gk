package com.example.bank.services;

import com.example.bank.models.Account;

public interface AuthService {
    boolean accountLogin(String phone, String password);
    boolean accountRegister(Account account) throws Exception;
}
