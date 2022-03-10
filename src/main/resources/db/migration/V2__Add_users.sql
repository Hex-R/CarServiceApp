insert into usr (id, activation_code, active, email, password, phone_number, username)
values (1, null, true, 'admin@mail.com', 'admin', '89081231234', 'admin'),
       (2, null, true, 'test@mail.com', 'test', '89181231234', 'test'),
       (3, null, true, 'test2@mail.com', 'test2', '89031231234', 'test2');



insert into user_role (user_id, roles)
values (1, 'USER'), (1, 'ADMIN'),
       (2, 'USER'),
       (3, 'USER');