drop table if exists shark CASCADE; 
create table shark (
	id bigint generated by default as identity, 
	conservation_status varchar(255) not null, 
	habitat varchar(255), 
	latin_name varchar(255), 
	species varchar(255) not null, 
	primary key (id)
)