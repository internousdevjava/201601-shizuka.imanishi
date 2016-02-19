create database if not exists test;
use test;

drop table if exists test;

create table test(
id varchar(20) not null primary key ,
password varchar(20) not null
);

insert into test(id,password)values("test","test");
insert into test(id,password)values("user1","123");
