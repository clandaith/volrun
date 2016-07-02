create table files(
	id SERIAL not null primary key ,
	file_name  VARCHAR(250) unique NOT NULL ,
	description  VARCHAR(250) NOT NULL ,	
	date_entered  TIMESTAMP NOT NULL
);

