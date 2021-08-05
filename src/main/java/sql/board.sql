CREATE SEQUENCE board_seq
START WITH 1
INCREMENT BY 1
MAXVALUE 99999
NOCYCLE;
 
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