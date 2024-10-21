-- barcode table
create table BARCODE(
    id int auto_increment primary key,
    barcode varchar(100) not null
);

create table CART(
    id int auto_increment primary key,
    created_at timestamp
);

create table BARCODE_CART(
    id int auto_increment primary key,
    barcode int,
    cart int
);

create table DESCRIPTION(
   id int auto_increment primary key,
   barcode int,
   locale varchar(10),
   "TEXT" varchar(255)
);

-- spring security tables
create table if not exists users (
	username varchar_ignorecase(50) not null primary key,
	password varchar_ignorecase(500) not null,
	enabled boolean not null
);

create table if not exists authorities (
	username varchar_ignorecase(50) not null,
	authority varchar_ignorecase(50) not null,
	constraint fk_authorities_users foreign key(username) references users(username)
);
create unique index ix_auth_username on authorities (username,authority);

insert into users values ('admin','$2a$10$j37/78fF9Eld9UeoTSbafe0TlGFCx3/flDwoNVe6ByH8bG9T2yhs.', true);
INSERT INTO authorities (username, authority) VALUES ('admin', 'ROLE_USER');
INSERT INTO authorities (username, authority) VALUES ('admin', 'ROLE_ADMIN');