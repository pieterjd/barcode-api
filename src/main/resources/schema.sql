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