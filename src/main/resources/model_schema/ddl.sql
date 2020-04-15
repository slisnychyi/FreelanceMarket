create table developers
(
    id   integer PRIMARY KEY,
    name varchar(100) UNIQUE NOT NULL
);

create table categories
(
    id   integer PRIMARY KEY,
    name varchar(100) UNIQUE NOT NULL
);

create table developer_categories
(
    developer_id integer NOT NULL,
    category_id  integer NOT NULL
);

alter table developer_categories
    add constraint pkDeveloperCategory PRIMARY KEY (developer_id, category_id);
alter table developer_categories
    add constraint fkDeveloperCategoryDeveloperId FOREIGN KEY (developer_id) REFERENCES developers (id);
alter table developer_categories
    add constraint fkDeveloperCategoryCatgeryId FOREIGN KEY (category_id) REFERENCES categories (id);

create table clients
(
    id   serial PRIMARY KEY,
    name varchar(100) UNIQUE NOT NULL
);

create table tasks
(
    id          serial PRIMARY KEY,
    client_id   integer REFERENCES clients (id) NOT NULL,
    name        varchar(100)                    NOT NULL,
    description text,
    price       numeric DEFAULT 0.0
);

create table income
(
    id           serial PRIMARY KEY,
    developer_id integer REFERENCES developers (id),
    amount       numeric   NOT NULL,
    created_date timestamp NOT NULL DEFAULT current_timestamp
);
