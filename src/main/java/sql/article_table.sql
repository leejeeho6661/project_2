CREATE SEQUENCE article_seq
START WITH 1
INCREMENT BY 1
MAXVALUE 99999
NOCYCLE;

create table article(
boardWriteNum number DEFAULT 0 primary key, 
boardWriteCategory varchar2(10) not null,
boardWriteTitle varchar2(100) not null,
boardWritePicture varchar2(100),
boardWriteWriter varchar2(10) not null,
boardWriteArticle clob,
boardWriteLike NUMBER(5) default '0',
boardWriteHit number(5) default '0',
boardInsertDate Date default sysdate);





===== 변경 사항 ====

CREATE SEQUENCE board_seq
START WITH 1
INCREMENT BY 1
MAXVALUE 99999
NOCYCLE;
 
 CREATE SEQUENCE article_seq
START WITH 1
INCREMENT BY 1
MAXVALUE 99999
NOCYCLE;
 delete sequence board_seq;
CREATE TABLE board (
    board_num NUMBER DEFAULT 0,
    board_title VARCHAR2(100) NOT NULL,
    board_date DATE DEFAULT SYSDATE,
    board_file VARCHAR2(100),
    board_content VARCHAR2(2000) NOT NULL,
    board_name VARCHAR2(30) NOT NULL,
    board_readcount NUMBER DEFAULT 0,
    board_part NUMBER NOT NULL,
    PRIMARY KEY(board_num)
);
SELECT COUNT(*) FROM article;
select * from article;
select article_seq.nextval from dual;

SELECT * FROM (
		SELECT 
			m.*,
			FLOOR((ROWNUM - 1)/10 + 1) page
		FROM (
				SELECT * FROM article
                where boardwritecategory='it'
				ORDER BY boardWriteNum DESC
			 ) m
		)
		WHERE page = 1;
        