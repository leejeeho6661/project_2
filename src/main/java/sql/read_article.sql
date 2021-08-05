create table article_read(
    userId varchar2(40) not null,
    articleNum number not null,
    constraint read_primarykey primary key(userId,articleNum)
);