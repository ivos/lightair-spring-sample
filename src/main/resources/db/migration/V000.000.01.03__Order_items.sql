create table order_items (
  id         bigserial primary key,
  order_id   bigint         not null,
  product    varchar(100)   not null,
  unit_price decimal(15, 5) not null,
  quantity   decimal(15, 5) not null
);

alter table order_items
  add constraint cc_order_items_product check (length(product) >= 1);

alter table order_items
  add constraint cc_order_items_unit_price check (unit_price >= 0);

alter table order_items
  add constraint cc_order_items_quantity check (quantity > 0);

alter table order_items
  add constraint fk_order_items_order foreign key (order_id) references orders on delete cascade;
