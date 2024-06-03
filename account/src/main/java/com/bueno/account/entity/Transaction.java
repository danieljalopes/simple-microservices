package com.bueno.account.entity;

import com.bueno.account.entity.enums.TransactionType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transactions_seq")
    @SequenceGenerator(name = "transactions_seq", sequenceName = "transactions_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false, updatable = false, unique = true)
    private Long id;
    @Column(name = "value", nullable = false)
    private Long value;
    @Column(name = "type", length = 2, nullable = false)
    @Enumerated(EnumType.STRING)
    private TransactionType type;
    @Column(name = "description", length = 40, nullable = false)
    private String description;
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @PrePersist
    public final void preUpdate() {
        createdAt = LocalDateTime.now();
    }

}
