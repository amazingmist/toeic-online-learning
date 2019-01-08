use toeic_online_learning;

create table user(
	  userid int primary key auto_increment,
    name varchar(255) UNIQUE,
    password varchar(255),
    fullname varchar(300),
    createddate timestamp,
    roleid tinyint
);

create table role(
	  roleid tinyint primary key,
    name varchar(100)
);

create table listenguideline(
    listenguidelineid int primary key auto_increment,
    title varchar(512),
    image varchar(255),
    content text,
    createddate timestamp,
    modifieddate timestamp
);

create table comment(
    commentid bigint primary key auto_increment,
    content text,
    createddate timestamp,
    userid int,
    listenguidelineid int
);
