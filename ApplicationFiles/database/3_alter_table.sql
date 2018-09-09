-- User - Role
alter table user add constraint fk_user_role foreign key(roleid) references role(roleid);


-- User - Comment
alter table comment add constraint fk_comment_user foreign key (userid) references user(userid);

-- ListenGuideLine - Comment
alter table comment add constraint fk_listenguideline_user foreign key (listenguidelineid) references listenguideline(listenguidelineid);