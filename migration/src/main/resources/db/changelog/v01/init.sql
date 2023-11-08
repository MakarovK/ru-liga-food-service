create table if not exists couriers
(
    id bigserial primary key,
    phone varchar(12) not null,
    status varchar(15) not null,
    coordinates geography(Point, 4326)
);

create table if not exists customers
(
    id bigserial primary key,
    phone varchar(12) not null,
    email varchar(50) not null,
    address varchar(50) not null,
    coordinates geography(Point, 4326)
);

create table if not exists restaurants
(
    id      bigserial primary key,
    address varchar(100) not null,
    status  varchar(15) not null,
    coordinates geography(Point, 4326)
);
select version();

create table if not exists restaurant_menu_items
(
    id            bigserial primary key,
    restaurant_id bigint      not null,
    name          varchar(30) not null,
    price         integer     not null,
    image         varchar(70) not null,
    description   varchar(150),
    foreign key (restaurant_id) references restaurants (id)
);


create table if not exists orders
(
    id            uuid primary key default gen_random_uuid(),
    customer_id   bigint      not null,
    restaurant_id bigint      not null,
    status        varchar(30) not null default 'active',
    courier_id    bigint,
    timestamp     timestamp   not null default now(),
    foreign key (courier_id) references couriers (id),
    foreign key (customer_id) references customers (id)
);

create table if not exists order_items
(
    id            bigserial primary key,
    order_id      uuid not null,
    restaurant_menu_item_id bigint  not null unique,
    price         integer not null,
    quantity      integer not null default 1,
    foreign key (restaurant_menu_item_id) references restaurant_menu_items (id),
    foreign key (order_id) references orders (id)
);

create sequence if not exists hibernate_sequence start 3;


create schema auth;

create table if not exists authorities (
     username varchar(255) not null references users,
     authority varchar(255) not null,
     enabled boolean default true not null
);

create table if not exists users (
     username varchar(255) not null,
     password varchar(255) not null,
     enabled boolean default true not null,
     primary key (username)
);

create table if not exists oauth2_registered_client (
     id varchar(100) not null primary key,
     client_id varchar(255) not null,
     client_id_issued_at timestamp default current_timestamp not null,
     client_secret varchar(255) default null::character varying,
     client_secret_expires_at timestamp,
     client_name varchar(255) not null,
     client_authentication_methods varchar(1000) not null,
     authorization_grant_types varchar(1000) not null,
     redirect_uris varchar(1000) default null::character varying,
     scopes varchar(1000) not null,
     client_settings varchar(1000) not null,
     token_settings varchar(1000) not null
);



comment on table couriers is 'Курьеры';
comment on column couriers.id is 'ID курьера';
comment on column couriers.phone is 'Номер телефона курьера';
comment on column couriers.status is 'ID курьера';
comment on column couriers.coordinates is 'Координаты курьера (Ширина, долгота с 4326 ESPG кодом)';

comment on table customers is 'Клиенты';
comment on column customers.id is 'ID клиента';
comment on column customers.phone is 'Номер телефона клиента';
comment on column customers.email is 'Email клиента';
comment on column customers.address is 'Адрес клиента';

comment on table restaurants is 'Рестораны';
comment on column restaurants.id is 'ID ресторана';
comment on column restaurants.address is 'Адрес ресторана';
comment on column restaurants.status is 'Статус ресторана';

comment on table restaurant_menu_items is 'Меню ресторана';
comment on column restaurant_menu_items.id is 'ID позиции в ресторане';
comment on column restaurant_menu_items.restaurant_id is 'ID ресторана';
comment on column restaurant_menu_items.name is 'Название позиции в ресторане';
comment on column restaurant_menu_items.price is 'Цена позиции в ресторане в копейках';
comment on column restaurant_menu_items.image is 'Ссылка на изображение для позиции в ресторане';
comment on column restaurant_menu_items.description is 'Привлекательное описание позиции в ресторане';

comment on table orders is 'Заказы';
comment on column orders.customer_id is 'ID клиента';
comment on column orders.restaurant_id is 'ID ресторана';
comment on column orders.status is 'Статус заказа';
comment on column orders.courier_id is 'ID курьера';
comment on column orders.timestamp is 'Время заказа';

comment on table order_items is 'Позиция заказа';
comment on column order_items.id is 'ID позиции заказа';
comment on column order_items.restaurant_menu_item_id is 'ID позиции из меню ресторана';
comment on column order_items.price is 'ID позиции заказа';
comment on column order_items.quantity is 'Количество позиций заказов';