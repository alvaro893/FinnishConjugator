-- This file create all database definitions

-- Create a databaseverb
create database if not exists conjugator;
use conjugator;

-- Create a user if not exits for the created database.
grant all on conjugator.* to 'conjugator'@'localhost' identified by 'conjugator';

-- table for verbs
create table if not exists verb(
	id int primary key not null auto_increment,
	type int,
    name varchar(100),
    description text
);