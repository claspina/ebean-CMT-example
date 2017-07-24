-- apply changes
create table person_entity (
  id                            serial not null,
  name                          varchar(255),
  last_name                     varchar(255),
  age                           integer,
  constraint pk_person_entity primary key (id)
);

