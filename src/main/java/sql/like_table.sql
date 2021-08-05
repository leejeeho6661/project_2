create table article_like(
    userId varchar2(40) not null,
    articleNum number not null,
    userIp varchar2(50) default null,
    constraint like_primarykey primary key(userId,articleNum)
);