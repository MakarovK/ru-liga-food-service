create table if not exists Couriers
(
    id bigint primary key,
    phone varchar(12) not null,
    status varchar(15) not null default 'active',
    coordinates geography(Point, 4326)
);

create table if not exists Customers
(
    id bigint primary key,
    phone varchar(12) not null,
    email varchar(50) not null,
    address varchar(50) not null
);

create table if not exists Restaurants
(
    id      bigint primary key,
    address varchar(50) not null,
    status  varchar(15) not null default 'active'
);

create table if not exists Restaurant_menu_items
(
    id            bigint primary key,
    restaurant_id bigint      not null,
    name          varchar(30) not null,
    price         integer     not null,
    image         varchar(70) not null,
    description   varchar(150),
    foreign key (restaurant_id) references Restaurants (id)
);


create table if not exists Orders
(
    id            bigint primary key,
    customer_id   bigint      not null,
    restaurant_id bigint      not null,
    status        varchar(15) not null default 'active',
    courier_id    bigint,
    timestamp     timestamp   not null default now(),
    foreign key (courier_id) references Couriers (id),
    foreign key (customer_id) references Customers (id)
);

create table if not exists Order_items
(
    id            bigint primary key,
    order_id      bigint  not null,
    restaurant_menu_item_id bigint  not null unique,
    price         integer not null,
    quantity      integer not null default 1,
    foreign key (restaurant_menu_item_id) references Restaurant_menu_items (id),
    foreign key (order_id) references Orders (id)
);

comment on table Couriers is 'Курьеры';
comment on column Couriers.id is 'ID курьера';
comment on column Couriers.phone is 'Номер телефона курьера';
comment on column Couriers.status is 'ID курьера';
comment on column Couriers.coordinates is 'Координаты курьера (Ширина, долгота с 4326 ESPG кодом)';

comment on table Customers is 'Клиенты';
comment on column Customers.id is 'ID клиента';
comment on column Customers.phone is 'Номер телефона клиента';
comment on column Customers.email is 'Email клиента';
comment on column Customers.address is 'Адрес клиента';

comment on table Restaurants is 'Рестораны';
comment on column Restaurants.id is 'ID ресторана';
comment on column Restaurants.address is 'Адрес ресторана';
comment on column Restaurants.status is 'Статус ресторана';

comment on table Restaurant_menu_items is 'Меню ресторана';
comment on column Restaurant_menu_items.id is 'ID позиции в ресторане';
comment on column Restaurant_menu_items.restaurant_id is 'ID ресторана';
comment on column Restaurant_menu_items.name is 'Название позиции в ресторане';
comment on column Restaurant_menu_items.price is 'Цена позиции в ресторане в копейках';
comment on column Restaurant_menu_items.image is 'Ссылка на изображение для позиции в ресторане';
comment on column Restaurant_menu_items.description is 'Привлекательное описание позиции в ресторане';

comment on table Orders is 'Заказы';
comment on column Orders.customer_id is 'ID клиента';
comment on column Orders.restaurant_id is 'ID ресторана';
comment on column Orders.status is 'Статус заказа';
comment on column Orders.courier_id is 'ID курьера';
comment on column Orders.timestamp is 'Время заказа';

comment on table Order_items is 'Позиция заказа';
comment on column Order_items.id is 'ID позиции заказа';
comment on column Order_items.restaurant_menu_item_id is 'ID позиции из меню ресторана';
comment on column Order_items.price is 'ID позиции заказа';
comment on column Order_items.quantity is 'Количество позиций заказов';
