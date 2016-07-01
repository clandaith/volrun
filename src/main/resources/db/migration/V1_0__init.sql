create SEQUENCE  hibernate_sequence;

-- Create Table: game_companies
--------------------------------------------------------------------------------
CREATE TABLE game_companies
(
	id SERIAL not null primary key
	,name VARCHAR(250) unique NOT NULL 
	,address1 VARCHAR(250) NOT NULL 
	,address2 VARCHAR(250)  NULL 
	,city VARCHAR(250) NOT NULL 
	,state VARCHAR(250) NOT NULL 
	,zip VARCHAR(250) NOT NULL 
	,country VARCHAR(250) NOT NULL 
	,phone_number BIGINT NOT NULL 
	,website VARCHAR(500) NOT NULL 
	,date_added TIMESTAMP NOT NULL
	,date_updated TIMESTAMP NOT NULL
	,contact_name VARCHAR(250) NOT NULL 
	,contact_phone_number BIGINT NOT NULL 
	,contact_email_address VARCHAR(250) NOT NULL 
);


-- Create Table: game_systems
--------------------------------------------------------------------------------
CREATE TABLE game_systems
(
	id SERIAL not null primary key
	,game_company_id INTEGER NOT NULL 
	,name VARCHAR(250) unique NOT NULL 
	,website VARCHAR(500) NOT NULL 
	,date_added TIMESTAMP NOT NULL
	,date_updated TIMESTAMP NOT NULL
	,description VARCHAR(4096)  NOT NULL 
);


-- Create Table: users
--------------------------------------------------------------------------------
CREATE TABLE users
(
	id SERIAL not null primary key
	,username VARCHAR(250) unique NOT NULL 
	,password VARCHAR(250) NOT NULL 
	,first_name VARCHAR(250)  NOT NULL 
	,last_name VARCHAR(250)  NOT NULL 
	,address1 VARCHAR(250)  NULL 
	,address2 VARCHAR(250)  NULL 
	,city VARCHAR(250)  NULL 
	,state VARCHAR(250)  NULL 
	,zip VARCHAR(250)  NULL 
	,country VARCHAR(250)  NULL 
	,phone_number BIGINT unique NULL 
	,date_added TIMESTAMP NOT NULL
	,date_updated TIMESTAMP NOT NULL
	,email_address VARCHAR(250) unique  NOT NULL 
	,description VARCHAR(4096)  NULL 
	,enabled boolean NOT NULL default 'TRUE' 
);
create index users_index_username on users(username);

-- Create Table: user_roles
--------------------------------------------------------------------------------
CREATE TABLE user_roles (
 	id SERIAL not null primary key
	,username VARCHAR(250) NOT NULL
	,role VARCHAR(45) NOT NULL
	,CONSTRAINT user_roles_unique_values UNIQUE (username, role)
);
create index user_roles_index_username on user_roles(username);

--------------------------------------------------------------------------------
  
-- Create Foreign Key: game_systems.game_company_id -> game_companies.id
ALTER TABLE game_systems ADD CONSTRAINT FK_game_system_game_companies_id_game_companies_id FOREIGN KEY (game_company_id) REFERENCES game_companies(id) ON DELETE CASCADE;

-- Create Foreign Key: user_roles.user_id -> users.id
ALTER TABLE user_roles ADD CONSTRAINT FK_user_roles_username_id FOREIGN KEY (username) REFERENCES users(username) ON DELETE CASCADE;

-- Default Data

--++++++++++++++++++++++++++++++++++++++++++
-- Users
insert into users (username, password, first_name, last_name, address1, address2, city, state, zip, phone_number, country, email_address, description, date_added, date_updated) values 
('clandaith', '$2a$04$IsxBaKz3K3VRgRucm3LzKO2DZodMSsidGOmIEQDRrQlreh/vA4UQC', 'Troy', 'Davidson', '1874 S 900 E', '', 'Bountiful', 'UT', '84010', 8016631460, 'USA', 'clandaith@gmail.com', 'desc', now(), now());

insert into users (username, password, first_name, last_name, address1, address2, city, state, zip, phone_number, country, email_address, description, date_added, date_updated) values 
('test', '$2a$04$bXho5/ltm.qCttW6F54nROn1zWv4MjoLqWekaWl9U74IaQE4NJR2K', 'Test', 'User', '123 Main St', 'Apt 1', 'NSL', 'UT', '84511', 8015558888, 'USA', 'test@clandaith.com', 'other desc', now(), now());

--++++++++++++++++++++++++++++++++++++++++++
-- User Roles
INSERT INTO user_roles (username, role) VALUES ('test', 'ROLE_USER');
INSERT INTO user_roles (username, role) VALUES ('clandaith', 'ROLE_ADMIN');
INSERT INTO user_roles (username, role) VALUES ('clandaith', 'ROLE_USER');

--++++++++++++++++++++++++++++++++++++++++++
-- Game Companies
insert into game_companies (name, address1, address2, city, state, zip, country, phone_number, website,contact_name, 
contact_phone_number, contact_email_address, date_added, date_updated ) values
('Test Company', '123 Any St', 'Suite A', 'Any Town', 'Any State', 'A4R 22Z', 'UK', 8015551212, 'www.clandaith.com', 'Test Contact',
 8015551212, 'contactemail@clandaith.com', now(), now());

--++++++++++++++++++++++++++++++++++++++++++
-- Game Systems
insert into game_systems (game_company_id, name, website, date_added, date_updated, description) values 
((select id from game_companies where name = 'Test Company'), 'Test Game', 'some website', now(), now(), 'Some description');

--++++++++++++++++++++++++++++++++++++++++++
