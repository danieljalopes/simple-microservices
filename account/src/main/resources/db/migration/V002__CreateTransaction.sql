create sequence transactions_id_seq;

create table transactions
(
    id          bigint       not null default nextval('transactions_id_seq'),
    type        varchar(2)   not null check (type in ('C', 'D')),
    created_at  timestamp(6) not null,
    value       bigint       not null,
    account_id  bigint       not null,
    description varchar(40)  not null,
    primary key (id)
);

create index idx_transactions_created_at
    on transactions (created_at);

alter table if exists transactions
    add constraint fk_transactions_accounts
        foreign key (account_id)
            references accounts (id);