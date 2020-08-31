create table city (
    ibge_code varchar(255) not null,
    name varchar(30),
    state varchar(2),
    primary key (ibge_code)
);

create table contact (
    id bigserial not null,
    contact varchar(255),
    type varchar(255),
    customer_id int8,
    primary key (id)
);

create table customer (
    id bigserial not null,
    address_complement varchar(60),
    district varchar(60),
    address_number varchar(20),
    postal_code varchar(255) not null,
    street varchar(100),
    birth_date date,
    cpf varchar(255),
    gender varchar(255) not null,
    name varchar(255),
    city_ibge_code varchar(255) not null,
    primary key (id)
);

alter table customer
    add constraint uk_customer_cpf
    unique (cpf);

alter table contact
    add constraint fk_contact_customer
    foreign key (customer_id) references customer;

alter table customer
    add constraint fk_customer_city
    foreign key (city_ibge_code) references city;