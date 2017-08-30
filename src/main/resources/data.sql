drop table offer if exists;

create table offer(offer_id integer not null auto_increment, description varchar(255), currency varchar(255), price DECIMAL(40,2), primary key (offer_id));