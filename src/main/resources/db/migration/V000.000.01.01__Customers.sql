create table customers (
  id      bigserial primary key,
  version bigint       not null,
  name    varchar(100) not null
);
