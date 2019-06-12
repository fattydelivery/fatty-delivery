drop database fattydelivery;
create database fattydelivery;

use fattydelivery;

create table CustomerTable(
username varchar(20) primary key not null,
password varchar(20) not null,
gender char(6),
phone varchar(20) not null,
email varchar(30),
dob date,
addr varchar(50) not null,
regtime datetime not null
);

create table BussinessTable(
username varchar(20) primary key not null,
password varchar(20) not null,
gender char(6),
phone varchar(20) not null,
email varchar(30),
dob date,
addr varchar(50) not null,
regtime datetime not null
);

create table CommodityTable(
id varchar(50) primary key not null,
cost double not null,
bussinessid varchar(20) not null,
img longblob,
imgext varchar(10),
foreign key(bussinessid) references BussinessTable(username)
);

create table OrderTable(
addr varchar(50) not null,
amount double not null,
bussinessid varchar(20) not null,
customerid varchar(20) not null,
commodityid varchar(400) not null,
datetime datetime not null,
status varchar(20) not null,
phone varchar(20) not null,
orderid bigint primary key not null,
foreign key(bussinessid) references BussinessTable(username),
foreign key(customerid) references CustomerTable(username)
);
