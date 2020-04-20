create table order_numbers (
  year          integer primary key,
  version       bigint  not null,
  last_sequence integer not null
);

alter table order_numbers
  add constraint cc_order_numbers_year check (year > 1900 and year < 3000);

alter table order_numbers
  add constraint cc_order_numbers_sequence check (last_sequence > 0);
