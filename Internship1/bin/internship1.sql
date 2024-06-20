create database photo_shop
use photo_shop
create table stock(
unit_price numeric(10,0) primary key
)
create table customer(
cust_id char(5) check (cust_id like 'C[0-9][0-9][0-9][0-9]') primary key,
cust_name varchar(30)
);
create table bills(
cust_id char(5) foreign key references customer(cust_id) on Delete Cascade,
bill_id char(3),
cust_order varchar(50),
unit_price numeric(10,0) foreign key references stock(unit_price),
price as (unit_price*quantity) ,
quantity int check (quantity>0),
);
create table income(
cust_id char(5) foreign key references customer(cust_id) on Delete Cascade,
total_price numeric(10,0)
)
create table number(
numb int,
b_numb int,
);
insert into stock(unit_price) values (1000), (500), (300), (5000), (3000), (2000)
select * from stock
select * from customer
select * from bills
select * from income
select * from number
delete from customer where cust_id like 'C%'
update number set b_numb =1
update number set numb = 1
insert into number(numb, b_numb) values (1,1);
drop table bills
drop table number
drop table customer
drop table stock
drop table income