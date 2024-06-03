package com.bueno.account.entity;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "accounts_seq")
    @SequenceGenerator(name = "accounts_seq", sequenceName = "accounts_id_seq", allocationSize = 1)
    @Column(name = "id", length = 40, nullable = false, updatable = false, unique = true)
    private Long id;
    @Column(name = "name", length = 100, nullable = false)
    private String name;
    @Column(name = "limite", nullable = false)
    private Long limite;
    @Column(name = "saldo", nullable = false)
    private Long saldo;
    @Version
    @Column(name = "version", nullable = false)
    private Long version;
}
