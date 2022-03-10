create sequence hibernate_sequence start 1 increment 1;

create table user_role
(
    user_id int8 not null,
    roles   varchar(255)
);

create table usr
(
    id              int8 not null,
    active          boolean,
    username        varchar(255),
    password        varchar(255),
    email           varchar(255),
    phone_number    varchar(255),
    activation_code varchar(255),
    primary key (id)
);

create table car_service
(
    id          int8 not null,
    name        varchar(255),
    description varchar(2048),
    price       int4 not null,
    primary key (id)
);

alter table if exists user_role
    add constraint user_role_user_fk
    foreign key (user_id) references usr