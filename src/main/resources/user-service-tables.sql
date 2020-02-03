-- ADMIN TABLE

-- Drop table

-- DROP TABLE public.admins;

CREATE TABLE public.admins (
	admin_id serial NOT NULL,
	user_name varchar(255) NULL,
	CONSTRAINT admins_pkey PRIMARY KEY (admin_id)
);

-- BATCH TABLE

-- Drop table

-- DROP TABLE public.batches;

CREATE TABLE public.batches (
	batch_number int4 NOT NULL,
	batch_location varchar(255) NULL,
	CONSTRAINT batches_pkey PRIMARY KEY (batch_number)
);

-- CAR TABLE

-- Drop table

-- DROP TABLE public.cars;

CREATE TABLE public.cars (
	car_id serial NOT NULL,
	color varchar(255) NULL,
	make varchar(255) NULL,
	model varchar(255) NULL,
	seats int4 NOT NULL,
	car_year int4 NULL,
	user_id int4 NULL,
	CONSTRAINT cars_pkey PRIMARY KEY (car_id),
	CONSTRAINT cars_unique UNIQUE (user_id)
);

ALTER TABLE public.cars ADD CONSTRAINT cars_fkey FOREIGN KEY (user_id) REFERENCES users(user_id);

-- USER TABLE

-- Drop table

-- DROP TABLE public.users;

CREATE TABLE public.users (
	user_id serial NOT NULL,
	email varchar(255) NULL,
	first_name varchar(255) NULL,
	is_accepting_rides bool NULL,
	is_active bool NULL,
	is_driver bool NULL,
	last_name varchar(255) NULL,
	phone_number varchar(255) NULL,
	user_name varchar(255) NULL,
	batch_number int4 NULL,
	CONSTRAINT users_pkey PRIMARY KEY (user_id),
	CONSTRAINT users_fkey FOREIGN KEY (batch_number) REFERENCES batches(batch_number)
);