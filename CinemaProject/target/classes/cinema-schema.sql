drop table if exists cinema CASCADE;
create table cinema (id integer generated by default as identity, cinema varchar(255) not null, cost double, film varchar(255), primary key (id));