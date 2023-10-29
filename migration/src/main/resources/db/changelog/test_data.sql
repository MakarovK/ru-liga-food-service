insert into couriers(id, phone, status, coordinates)
values
    (1,'88005553535', 'DENIED', 'point(56.302417 44.029228)'),
    (2,'+7328839930', 'PICKING', 'point(56.322225 43.881076)');
insert into customers(id, phone, email, address)
values
    (1, '+73342019232', 'maka73-73@mail.ru', 'г. Уфа, ул. Пушкина, дом Кукушкина'),
    (2, '+75332343232', 'princess21@mail.ru', 'г. Уфа, ул. Носова, дом Альбиносова'),
    (3, '+79543545344', 'tratata@mail.ru', 'г. Уфа, ул. Кимоносова, дом Утконосова');
insert into restaurants(id, address, status)
values
    (1, 'Дом Заварушкина, рядом с домом Кукушкина', 'ACCEPTED'),
    (2, 'Дом Кукушкинка, рядом с домом Заварушкина', 'REFUNDED');
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
