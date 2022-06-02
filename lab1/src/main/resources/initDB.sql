create table user_
(
    id       serial PRIMARY KEY,
    name     character varying NOT NULL,
    surname  character varying NOT NULL,
    login    character varying NOT NULL,
    password character varying NOT NULL,
    isAdmin  integer           NOT NULL
);

insert into user_(name, surname, login, password, isAdmin)
values ('Eugene', 'Samoilych', 'login', 'password', 1),
       ('NotEugene', 'NotSamoilych', 'log', 'pass', 0);

insert into user_(name, surname, login, password, isAdmin)
values ('Yevhenii', 'Samoilych', 'qwerty', 'qwerty', 0);


create table drink
(
    id           serial PRIMARY KEY,
    name         character varying NOT NULL,
    amount       bool              NOT NULL default true,
    cost_per_day integer                    default 100
);

insert into drink(name)
values ('espresso'),
       ('americano'),
       ('cappuccino');

create table bill
(
    user_id  integer NOT NULL,
    drink_id integer NOT NULL,
    amount   integer NOT NULL,
    cost     integer NOT NULL,
    paid     bool    NOT NULL default false,
    FOREIGN KEY (user_id) REFERENCES user_ (id),
    FOREIGN KEY (drink_id) REFERENCES drink (id)
);

create table fill
(
    user_id   integer NOT NULL,
    drink_id  integer NOT NULL,
    amount    integer NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user_ (id),
    FOREIGN KEY (drink_id) REFERENCES drink (id)
);
