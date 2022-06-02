create table drink
(
    id     serial PRIMARY KEY,
    name   character varying NOT NULL,
    amount integer           NOT NULL default 1,
    price  integer                    default 100
);

insert into drink(name)
values ('espresso'),
       ('americano'),
       ('cappuccino');

insert into drink(name, amount)
values ('latte', 10);

create table bill
(
    id           serial PRIMARY KEY,
    user_id  integer NOT NULL,
    drink_id integer NOT NULL,
    amount   integer NOT NULL,
    cost     integer NOT NULL,
    paid     bool    NOT NULL default false,
    FOREIGN KEY (drink_id) REFERENCES drink (id)
);


create table fill
(
    id           serial PRIMARY KEY,
    user_id   integer NOT NULL,
    drink_id  integer NOT NULL,
    amount    integer NOT NULL,
    FOREIGN KEY (drink_id) REFERENCES drink (id)
);
