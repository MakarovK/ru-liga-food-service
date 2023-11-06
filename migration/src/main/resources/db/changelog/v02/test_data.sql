insert into couriers(id, phone, status, coordinates)
values
    (1,'88005553535', 'ACTIVE', 'point(56.302417 44.029228)'),
    (2,'+7328839930', 'ACTIVE', 'point(56.322225 43.881076)');
insert into customers(id, phone, email, address)
values
    (1, '+73342019232', 'maka73-73@mail.ru', 'г. Уфа, ул. Пушкина, дом Кукушкина'),
    (2, '+75332343232', 'princess21@mail.ru', 'г. Уфа, ул. Носова, дом Альбиносова'),
    (3, '+79543545344', 'tratata@mail.ru', 'г. Уфа, ул. Кимоносова, дом Утконосова');
insert into restaurants(id, address, status, coordinates)
values
    (1, 'ул. Фильченкова, 10, Нижний Новгород, Нижегородская обл., 603002', 'ACCEPTED', 'point(56.32058942989184 43.94633071837609)'),
    (2, 'улица, Московское ш., 12, Нижний Новгород, Нижегородская обл., 603010', 'REFUNDED', 'point(56.31888109935787 43.92577421166709)');
insert into restaurant_menu_items(id, restaurant_id, name, price, image, description)
values
    (1, 1, 'Бургер', 35000, 'Бургер.jpg', 'Вкуснейший бургер с великолепным мясом из Кореи'),
    (2, 1, 'Пицца', 80000, 'Пицца.jpg', 'Аппетитнейшая пицца с ананасами');
insert into orders(id, customer_id,restaurant_id, status, courier_id, timestamp)
values
    (1, 1, 1, 'CREATED', 1, now()),
    (2, 2, 2, 'CREATED', 2, now());
insert into order_items(id, order_id, restaurant_menu_item_id, price, quantity)
values
    (1, 1, 1, 70000, 2),
    (2, 2, 2, 160000, 2);
