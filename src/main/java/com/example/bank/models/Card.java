package com.example.bank.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "card")
@NoArgsConstructor
@AllArgsConstructor
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    private Long id;

    @Column(name = "account_number",length = 150)
    private String cardNumber;

    @Column(name = "balance",length = 150)
    private Double balance;

    @OneToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "accountId", nullable = false)
    private Account account;
}
