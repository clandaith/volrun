create table stores(
	id SERIAL not null primary key ,
	store_name  VARCHAR(250) unique NOT NULL ,
	address1 VARCHAR(250) NOT NULL ,
	address2 VARCHAR(250) NOT NULL ,
	city VARCHAR(250) NOT NULL ,
	state VARCHAR(250) NOT NULL ,
	zip VARCHAR(250) NOT NULL ,
	country varchar(250) not null,
	phonenumber  BIGINT NOT NULL ,
	date_entered  TIMESTAMP NOT NULL
);

create index stores_store_name on stores(store_name);
