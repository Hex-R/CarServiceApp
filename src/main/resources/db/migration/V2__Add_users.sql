insert into usr (id, active, username, password, email, phone_number, activation_code)
values (1, true, 'test', 'test', 'test@mail.com', '89181231234', null),
       (2, true, 'test2', 'test2', 'test2@mail.com', '89031231234', null),
       (3, false, 'testNA', 'testNA', 'testNA@mail.com', '89081231234', '6904b362-3934-47a5-bf0c-c89e55523128');

insert into user_role (user_id, roles)
values (1, 'USER'),
       (2, 'USER'),
       (3, 'USER');