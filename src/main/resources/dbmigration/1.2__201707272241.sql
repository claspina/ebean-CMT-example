-- apply changes
create table suser (
  id                            bigserial not null,
  username                      varchar(255),
  password                      varchar(255),
  constraint pk_suser primary key (id)
);

