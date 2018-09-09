use toeic_online_learning;

create table user(
	userid int primary key auto_increment,
    name varchar(255),
    password varchar(255),
    fullname varchar(300),
    createddate timestamp
);

create table role(
	roleid tinyint primary key,
    name varchar(100)
);

alter table user add constraint fk_user_role foreign key(roleid) references role(roleid);