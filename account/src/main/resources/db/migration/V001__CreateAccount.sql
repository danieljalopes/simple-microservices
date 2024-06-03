create sequence accounts_id_seq;

create table accounts
(
    id      bigint       not null default nextval('accounts_id_seq'),
    limite  bigint       not null,
    saldo   bigint       not null,
    name    varchar(100) not null,
    version bigint                default 0 not null,
    primary key (id)
);

insert into accounts (name, saldo, limite)
values ('Conta Corrente', 0, 1000);

insert into accounts (name, saldo, limite)
values ('Conta Poupança', 0, 10000);