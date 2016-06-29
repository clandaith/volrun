create SEQUENCE  hibernate_sequence;

-- Create Table: game_plays
--------------------------------------------------------------------------------
CREATE TABLE game_plays
(
	id SERIAL not null primary key
	,user_id INTEGER NOT NULL 
	,game_system_id INTEGER NOT NULL 
	,date_played TIMESTAMP NOT NULL 
	,opponent_id INTEGER  NULL 
	,winner boolean NOT NULL 
);
create index game_plays_index_user_id on game_plays(user_id);
create index game_plays_index_game_system_id on game_plays(game_system_id);
create index game_plays_index_opponent_id on game_plays(opponent_id);


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
	,phone_number BIGINT  NULL 
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


-- Create Foreign Key: game_plays.user_id -> users.id
ALTER TABLE game_plays ADD CONSTRAINT FK_game_plays_user_id_users_id FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE;


-- Create Foreign Key: game_plays.opponent_id -> users.id
ALTER TABLE game_plays ADD CONSTRAINT FK_game_plays_opponent_id_users_id FOREIGN KEY (opponent_id) REFERENCES users(id) ON DELETE CASCADE;


-- Create Foreign Key: game_plays.game_system_id -> game_systems.id
ALTER TABLE game_plays ADD CONSTRAINT FK_game_plays_game_systems_id_game_systems_id FOREIGN KEY (game_system_id) REFERENCES game_systems(id) ON DELETE CASCADE;


-- Create Foreign Key: user_roles.user_id -> users.id
ALTER TABLE user_roles ADD CONSTRAINT FK_user_roles_username_id FOREIGN KEY (username) REFERENCES users(username) ON DELETE CASCADE;

-- Default Data

--++++++++++++++++++++++++++++++++++++++++++
-- Users
--foobar
insert into users (username, password, first_name, last_name, address1, address2, city, state, zip, phone_number, country, email_address, description, date_added, date_updated) values 
('clandaith', '$2a$04$TscCrxAG7wWoohfQSGiQDeLJ2oVcdRDweQwsz/.zDPJgbdFS.2eiq', 'Troy', 'Davidson', '1874 S 900 E', '', 'Bountiful', 'UT', '84010', 8016631460, 'USA', 'clandaith@gmail.com', 'desc', now(), now());

--blork
insert into users (username, password, first_name, last_name, address1, address2, city, state, zip, phone_number, country, email_address, description, date_added, date_updated) values 
('test', '$2a$04$nvyE1PYF2JnLbpk8WqsYQOEpNZHaDolwMaPvDEdrQDiaw5jO7HLEK', 'Test', 'User', '123 Main St', 'Apt 1', 'NSL', 'UT', '84511', 8015558888, 'USA', 'test@clandaith.com', 'other desc', now(), now());

--++++++++++++++++++++++++++++++++++++++++++
-- User Roles
INSERT INTO user_roles (username, role) VALUES ('test', 'ROLE_USER');
INSERT INTO user_roles (username, role) VALUES ('clandaith', 'ROLE_ADMIN');

--++++++++++++++++++++++++++++++++++++++++++
-- Game Companies
insert into game_companies (name, address1, address2, city, state, zip, country, phone_number, website,contact_name, 
contact_phone_number, contact_email_address, date_added, date_updated ) values
('Test Company', '123 Any St', 'Suite A', 'Any Town', 'Any State', 'A4R 22Z', 'UK', 8015551212, 'www.clandaith.com', 'Test Contact',
 8015551212, 'contactemail@clandaith.com', now(), now());

insert into game_companies (name, address1, address2, city, state, zip, country, phone_number, website,contact_name, 
contact_phone_number, contact_email_address, date_added, date_updated ) values
('Other Company', '555 asdfa', '', 'Somewhere', 'Blah', '99999', 'CA', 8015559999, 'foobar', 'Another Contact',
 8015559999, 'blah', now(), now());

--++++++++++++++++++++++++++++++++++++++++++
-- Game Systems
insert into game_systems (game_company_id, name, website, date_added, date_updated, description) values 
((select id from game_companies where name = 'Test Company'), 'Test Game', 'some website', now(), now(), 'Some description');

insert into game_systems (game_company_id, name, website, date_added, date_updated, description) values 
((select id from game_companies where name = 'Test Company'), 'Another Game', 'some website 2', now(), now(), 'Some description 2');

insert into game_systems (game_company_id, name, website, date_added, date_updated, description) values 
((select id from game_companies where name = 'Other Company'), 'Awesome Game', 'some website 3', now(), now(), 'Some description 3');

--++++++++++++++++++++++++++++++++++++++++++
-- Game Plays
insert into game_plays (user_id, game_system_id, date_played, opponent_id, winner) values 
((select id from users where username = 'clandaith'), (select id from game_systems where name = 'Test Game'), now(), null, 'true') ;

insert into game_plays (user_id, game_system_id, date_played, opponent_id, winner) values 
((select id from users where username = 'clandaith'), (select id from game_systems where name = 'Awesome Game'), now(), null, 'true') ;

insert into game_plays (user_id, game_system_id, date_played, opponent_id, winner) values 
((select id from users where username = 'test'), (select id from game_systems where name = 'Another Game'), now(), null, 'true') ;

insert into game_plays (user_id, game_system_id, date_played, opponent_id, winner) values 
((select id from users where username = 'test'), (select id from game_systems where name = 'Another Game'), now(), null, 'true') ;

insert into game_plays (user_id, game_system_id, date_played, opponent_id, winner) values 
((select id from users where username = 'test'), (select id from game_systems where name = 'Another Game'), now(), null, 'true') ;

insert into game_plays (user_id, game_system_id, date_played, opponent_id, winner) values 
((select id from users where username = 'test'), (select id from game_systems where name = 'Test Game'), now(), (select id from users where username = 'clandaith'), 'true') ;

insert into game_plays (user_id, game_system_id, date_played, opponent_id, winner) values 
((select id from users where username = 'test'), (select id from game_systems where name = 'Another Game'), now(), null, 'true') ;

insert into game_plays (user_id, game_system_id, date_played, opponent_id, winner) values 
((select id from users where username = 'test'), (select id from game_systems where name = 'Another Game'), now(), null, 'true') ;

insert into game_plays (user_id, game_system_id, date_played, opponent_id, winner) values 
((select id from users where username = 'clandaith'), (select id from game_systems where name = 'Awesome Game'), now(), (select id from users where username = 'test'), 'true') ;

insert into game_plays (user_id, game_system_id, date_played, opponent_id, winner) values 
((select id from users where username = 'clandaith'), (select id from game_systems where name = 'Awesome Game'), now(), (select id from users where username = 'test'), 'true') ;

insert into game_plays (user_id, game_system_id, date_played, opponent_id, winner) values 
((select id from users where username = 'clandaith'), (select id from game_systems where name = 'Another Game'), now(), (select id from users where username = 'test'), 'true') ;

insert into game_plays (user_id, game_system_id, date_played, opponent_id, winner) values 
((select id from users where username = 'clandaith'), (select id from game_systems where name = 'Another Game'), now(), (select id from users where username = 'test'), 'true') ;

insert into game_plays (user_id, game_system_id, date_played, opponent_id, winner) values 
((select id from users where username = 'clandaith'), (select id from game_systems where name = 'Another Game'), now(), (select id from users where username = 'test'), 'true') ;

insert into game_plays (user_id, game_system_id, date_played, opponent_id, winner) values 
((select id from users where username = 'clandaith'), (select id from game_systems where name = 'Another Game'), now(), (select id from users where username = 'test'), 'true') ;

insert into game_plays (user_id, game_system_id, date_played, opponent_id, winner) values 
((select id from users where username = 'clandaith'), (select id from game_systems where name = 'Another Game'), now(), (select id from users where username = 'test'), 'true') ;

--++++++++++++++++++++++++++++++++++++++++++
