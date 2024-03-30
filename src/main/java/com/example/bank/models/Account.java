package com.example.bank.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Entity
@Table(name = "account")
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    private Long id;

    @Column(name = "cccd_number",length = 150)
    private String cccdNumber;

    @Column(name = "account_name",length = 150)
    private String accountName;

    @Column(name = "phone",length = 150)
    private String phone;

    @Column(name = "account_number",length = 150)
    private String accountNumber;

    @Column(name = "address",length = 150)
    private String address;

    @Column(name = "password_hash",length = 210,nullable = false)
    private String passwordHash;

    @Column(name = "registered_at",nullable = false)
    private Instant registered_at;

    @OneToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "cardId", nullable = false)
    private Card card;


    public Account(String cccdNumber, String accountName, String phone, String accountNumber, String address, String passwordHash) {
        this.cccdNumber = cccdNumber;
        this.accountName = accountName;
        this.phone = phone;
        this.accountNumber = accountNumber;
        this.address = address;
        this.passwordHash = passwordHash;
    }
}
