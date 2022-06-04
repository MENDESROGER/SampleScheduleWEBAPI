create table availability(
  id bigint not null auto_increment,
  room bigint,
  status varchar(0),
  observation text,  
  availabletimestart datetime,
  availabletimeend datetime,
  
  primary key (id)
  );
