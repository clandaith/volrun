create table demos(
	id SERIAL not null primary key ,
	user_id int not null ,
	store_id int not null,
	date_entered  TIMESTAMP NOT NULL,
	date_of_demo  TIMESTAMP NOT NULL,
	start_time   TIMESTAMP NOT NULL,
	end_time   TIMESTAMP NOT NULL,
	completed   boolean NOT NULL default 'false',
	pre_notes VARCHAR(4000)  NOT NULL ,
	post_notes VARCHAR(4000)  NOT NULL ,
	number_of_demos int not null,
	number_of_people int not null,
	store_response VARCHAR(250)  NOT NULL
);

create index demos_index_user_id on demos(user_id);
create index demos_index_store_id on demos(store_id);
create index demos_index_date_of_demo on demos(date_of_demo);
create index demos_index_completed on demos(completed);

-- Create Foreign Key: demos_user_id -> users.id
ALTER TABLE demos ADD CONSTRAINT FK_demos_users_id FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE;
-- Create Foreign Key: demos.store_id -> stores.id
ALTER TABLE demos ADD CONSTRAINT FK_demos_stores_id FOREIGN KEY (store_id) REFERENCES stores(id) ON DELETE CASCADE;


