create table payment
(
    id              uuid not null primary key,
    currency        varchar(5),
    amount          bigint,
    status          varchar(255),
    created_at      timestamp,
    updated_at      timestamp
);
