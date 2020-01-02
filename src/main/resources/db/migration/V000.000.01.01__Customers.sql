create table customers (
  id                bigserial primary key,
  version           bigint       not null,
  name              varchar(100) not null,
  tax_number        varchar(14),
  maturity_interval integer      not null,
  mobile            varchar(30),
  email             varchar(50),
  web               varchar(50),
  updated           timestamp    not null
);

alter table customers
  add constraint uc_customers_tax_no unique (tax_number);

alter table customers
  add constraint cc_customers_tax_no check (length(tax_number) >= 4);

alter table customers
  add constraint cc_customers_maturity check (maturity_interval >= 0);

alter table customers
  add constraint cc_customers_mobile_email check (mobile is not null or email is not null);
