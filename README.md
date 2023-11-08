Для работы программы используется 4 микросервиса Delivery-service, Kitchen-service, Notification-service, Order-service.
---
*Программа преставляет собой имитацию поведения ресторана.
Для создания заказа (после запуска всех 4х микросервисов) используется URL http://localhost:8084/customer/2/create
Этот URL создаёт заказ и возвращает нам UUID этого заказа, после оплаты заказа (http://localhost:8084/customer/{UUID}/payment) заказ передаётся на кухню 
(кухня высчитывается как ближайшая к пользователю), где уже идёт дальнейшая обработка принятие заказа (http://localhost:8082/kitchen/restaurant/{UUID}/accept)
и отклонение заказа (http://localhost:8082/kitchen/restaurant/{UUID}/deny), во втором случае заказ у нас становится дальше не работоспособным
и мы больше не сможем с ним грамотно взаимодействовать, если же заказ принят, то дальше после готовки кухни
(http://localhost:8082/kitchen/restaurant/{UUID}/complete) отправляется синхронное сообщение через RabbitMq к сервису курьеров, у которого будет выбор принять
или отклонить заказ (http://localhost:8081/couriers/2/accept/{UUID}), (http://localhost:8081/couriers/2/deny/{UUID}) соответственно.
База данных была использована PostgreSQL 16.0 (Debian 16.0-1.pgdg110+1) on x86_64-pc-linux-gnu, compiled by gcc (Debian 10.2.1-6) 10.2.1 20210110, 64-bit с добавлением PostGis

