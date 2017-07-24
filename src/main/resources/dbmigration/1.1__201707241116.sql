-- apply changes
create table person (
  id                            bigserial not null,
  name                          varchar(255),
  last_name                     varchar(255),
  age                           integer,
  constraint pk_person primary key (id)
);

