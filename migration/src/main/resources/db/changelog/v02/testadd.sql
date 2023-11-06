create table if not exists test_table
(
    int_field bigserial primary key,
    test_field varchar(255) not null,
    test_field2 varchar(10) not null
);