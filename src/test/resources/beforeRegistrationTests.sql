delete from service_order_services;
delete from service_order;
delete from user_role;
delete from usr;

insert into usr values
(1, false, 'testNA', 'testNA', 'testNA@mail.com', '89081231234', '6904b362-3934-47a5-bf0c-c89e55523128'),
(2, true, 'testActive', 'testActive', 'testActive@mail.com', '89081231234', null);

insert into user_role values
(1, 'USER'),
(2, 'USER');

insert into service_order values
(3, '2022-05-25 09:00', '2022-05-26 12:00', true, 2),
(4, '2022-05-27 18:00', '2022-05-28 14:00', false, 2);


insert into service_order_services values
(3, 1),
(4, 6);

alter sequence hibernate_sequence restart with 10;