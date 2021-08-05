CREATE TABLE ARTICLE_COMMENT(
cno int not null primary key,
ano int not null,
ccontent clob not null,
writer VARCHAR2(40) not null,
cdate date default sysdate
);

CREATE SEQUENCE comment_seq
START WITH 1
INCREMENT BY 1
MAXVALUE 99999
NOCYCLE;