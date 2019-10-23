create table pirates(
	pirate_number SERIAL primary key,
	pirate_name VARCHAR(30) not null check(length(pirate_name)>0),
	secret_code VARCHAR(20) not null check(length(secret_code)>5)
);

create table chests(
	chest_number SERIAL primary key,
	pirate_number SERIAL references pirates(pirate_number),
	chest_type VARCHAR(10) not null,
	booty_amount numeric not null check(booty_amount >= 0)
);

create table pirates_sharing_chests(
	pirate_number SERIAL references pirates(pirate_number),
	shared_number SERIAL references shared_chests(shared_number),
	primary key(pirate_number, shared_number)
);

create table shared_chests(
	shared_number SERIAL primary key,
	chest_type VARCHAR(10) not null,
	booty_amount numeric not null check(booty_amount >= 0)
);
alter table shared_chests alter column chest_type type VARCHAR(25);

create role pirate_king with password 'imtherichest';
grant select, update, insert on all tables in schema "PirateBank" to pirate_king;
grant all privileges on schema "PirateBank" to pirate_king;
grant all privileges on all sequences in schema "pirate_bank" to pirate_king;
alter schema "pirate_bank" owner to pirate_king;

insert into pirates (pirate_name, secret_code)
values ('Whitebeard', 'graybeard');

insert into chests (pirate_number, chest_type, booty_amount)
values (1, 'Checking', 200), (2, 'Checking', 50);

select * from chests where pirate_number = 1;

select * from shared_chests;

insert into shared_chests (chest_type, booty_amount)
values ('Joint Savings', 300);

delete from shared_chests where shared_number = 6;

insert into pirates_sharing_chests (pirate_number, shared_number)
values (2, 2);

select shared_chests.shared_number, pirate_number, booty_amount, chest_type from shared_chests left join  pirates_sharing_chests 
on pirates_sharing_chests.shared_number = shared_chests.shared_number where shared_chests.shared_number = 1;

SELECT sc.shared_number, psc.pirate_number, booty_amount, chest_type FROM shared_chests as sc left join pirates_sharing_chests as psc
on sc.shared_number = psc.shared_number WHERE pirate_number=1;


select * from chests;
update chests set booty_amount = booty_amount + -100 where pirate_number = 1;

insert into pirates_sharing_chests (pirate_number, shared_number) values (9, 10);

select max(shared_number) from shared_chests;

delete from pirates_sharing_chests where shared_number <3;
delete from shared_chests where shared_number < 3;

select * from chests where chest_number = 1 and booty_amount = 7400;