create table tournaments(
	id SERIAL not null primary key ,
	user_id int not null ,
	store_id int not null,
	date_entered  TIMESTAMP NOT NULL,
	date_of_tournament  DATE NOT NULL,
	start_time   TIME NOT NULL,
	end_time   TIME NOT NULL,
	completed   boolean NOT NULL default 'false',
	pre_notes VARCHAR(4000)  NOT NULL ,
	post_notes VARCHAR(4000) NOT NULL ,
	number_of_people int not null,
	store_response VARCHAR(250) NOT NULL
);

create index tournaments_index_user_id on tournaments(user_id);
create index tournaments_index_store_id on tournaments(store_id);
create index tournaments_index_date_of_demo on tournaments(date_of_tournament);
create index tournaments_index_completed on tournaments(completed);

-- Create Foreign Key: tournaments.user_id -> users.id
ALTER TABLE tournaments ADD CONSTRAINT FK_tournaments_users_id FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE;
-- Create Foreign Key: tournaments.store_id -> stores.id
ALTER TABLE tournaments ADD CONSTRAINT FK_tournaments_stores_id FOREIGN KEY (store_id) REFERENCES stores(id) ON DELETE CASCADE;



