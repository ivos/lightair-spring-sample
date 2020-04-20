create table orders (
  id           bigserial primary key,
  version      bigint      not null,
  order_number varchar(30) not null,
  customer_id  bigint      not null,
  due_date     date        not null,
  status       varchar(32) not null,
  comment_     text,
  created      timestamp   not null,
  updated      timestamp   not null
);

alter table orders
  add constraint cc_orders_order_number check (length(order_number) >= 1);

alter table orders
  add constraint cc_orders_comment_ check (comment_ is null or length(comment_) >= 1);

alter table orders
  add constraint fk_orders_customer foreign key (customer_id) references customers;

alter table orders
  add constraint cc_orders_status check (status in ('created', 'shipped', 'invoiced'));
