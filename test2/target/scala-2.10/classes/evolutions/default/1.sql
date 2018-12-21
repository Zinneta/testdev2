# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table artist (
  id                        bigint auto_increment not null,
  name                      varchar(255),
  sex                       integer,
  age                       integer,
  officeid                  integer,
  officename                varchar(255),
  typeid                    integer,
  wordprice                 integer,
  rate                      double,
  created                   datetime not null,
  modify                    datetime not null,
  constraint pk_artist primary key (id))
;

create table message (
  id                        bigint auto_increment not null,
  name                      varchar(255),
  message                   varchar(255),
  postdate                  datetime not null,
  constraint pk_message primary key (id))
;

create table muser (
  id                        bigint auto_increment not null,
  name                      varchar(255),
  password                  varchar(255),
  mail                      varchar(255),
  created                   datetime not null,
  modify                    datetime not null,
  constraint pk_muser primary key (id))
;

create table office_model (
  id                        bigint auto_increment not null,
  name                      varchar(255),
  password                  varchar(255),
  mail                      varchar(255),
  url                       varchar(255),
  created                   datetime not null,
  modify                    datetime not null,
  constraint pk_office_model primary key (id))
;




# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table artist;

drop table message;

drop table muser;

drop table office_model;

SET FOREIGN_KEY_CHECKS=1;

